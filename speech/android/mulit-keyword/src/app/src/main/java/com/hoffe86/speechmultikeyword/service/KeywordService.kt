package com.hoffe86.speechmultikeyword.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.hoffe86.speechmultikeyword.BuildConfig
import com.hoffe86.speechmultikeyword.R
import com.hoffe86.speechmultikeyword.kws.Detection
import com.hoffe86.speechmultikeyword.kws.EngineType
import com.hoffe86.speechmultikeyword.kws.Keyword
import com.hoffe86.speechmultikeyword.kws.KeywordEngine
import com.hoffe86.speechmultikeyword.kws.KwsConfig
import com.hoffe86.speechmultikeyword.kws.KwsConfigLoader
import com.hoffe86.speechmultikeyword.kws.ListeningMode
import com.hoffe86.speechmultikeyword.kws.ListeningStateMachine
import com.hoffe86.speechmultikeyword.kws.ProcessMetrics
import com.hoffe86.speechmultikeyword.kws.Transition
import com.hoffe86.speechmultikeyword.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Foreground service (type microphone) that owns the keyword engine and the listening state
 * machine. The activity binds to it and observes [state]. All state changes run on the main thread.
 */
class KeywordService : LifecycleService() {

    inner class LocalBinder : Binder() {
        val service: KeywordService get() = this@KeywordService
    }

    private val binder = LocalBinder()
    private val defaultEngine = EngineType.valueOf(BuildConfig.KWS_ENGINE)
    private val _state = MutableStateFlow(UiState(engineType = defaultEngine))
    val state: StateFlow<UiState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val engineDispatcher = Dispatchers.IO.limitedParallelism(1)
    private var engine: KeywordEngine? = null
    private var stateMachine = ListeningStateMachine(KwsConfig.DEFAULT_TIMEOUT_MS)
    private val metrics = ProcessMetrics()
    private var logId = 0L

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        lifecycleScope.launch { tickLoop() }
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent?.action == ACTION_START) {
            // Must be called for every startForegroundService() call.
            startForeground(NOTIFICATION_ID, buildNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE)
            startEngine()
        }
        return START_NOT_STICKY
    }

    private fun startEngine() {
        val status = _state.value.status
        if (status == EngineStatus.LOADING || status == EngineStatus.RUNNING) return
        _state.update { it.copy(status = EngineStatus.LOADING, error = null) }
        addLog("Loading engine…")

        lifecycleScope.launch(engineDispatcher) {
            try {
                // A recognizer loop may have died with an error; release it before re-creating.
                engine?.close()
                engine = null
                val baseDir = requireNotNull(getExternalFilesDir(null)) { "external files dir unavailable" }
                val config = KwsConfigLoader.load(baseDir, defaultEngine).getOrThrow()
                Log.i(TAG, "config: $config")
                val problems = config.validate()
                require(problems.isEmpty()) { problems.joinToString("\n") }

                val newEngine = KeywordEngine.create(config)
                val loadMs = newEngine.load()
                engine = newEngine
                withContext(Dispatchers.Main) {
                    stateMachine = ListeningStateMachine(config.listeningTimeoutMs)
                    _state.update {
                        it.copy(
                            engineType = config.engine,
                            status = EngineStatus.RUNNING,
                            mode = stateMachine.mode,
                            metrics = it.metrics.copy(loadMs = loadMs),
                        )
                    }
                    addLog("${config.engine} loaded in ${loadMs}ms; armed: ${Keyword.phrases.joinToString()}")
                }
                newEngine.start(engineListener)
            } catch (e: Throwable) {
                Log.e(TAG, "engine start failed", e)
                engine?.close()
                engine = null
                withContext(Dispatchers.Main) {
                    val message = e.message ?: e.javaClass.simpleName
                    _state.update { it.copy(status = EngineStatus.ERROR, error = message) }
                    addLog("Start failed: $message", isError = true)
                    stopForeground(STOP_FOREGROUND_REMOVE)
                    stopSelf()
                }
            }
        }
    }

    fun stopEngine() {
        lifecycleScope.launch(engineDispatcher) {
            engine?.close()
            engine = null
            withContext(Dispatchers.Main) {
                stateMachine.reset()
                _state.update { it.copy(status = EngineStatus.STOPPED, mode = stateMachine.mode) }
                addLog("Engine stopped")
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
    }

    /** Debug aid: feed a keyword through the same path as a real detection, without the mic. */
    fun simulate(keyword: Keyword) {
        handleDetection(
            Detection(
                keyword = keyword, rawText = keyword.phrase, reason = "Simulated", source = "ui",
                offsetMs = 0, durationMs = 0, armToResultMs = 0, latencyEstimateMs = null,
                wallTimeMs = System.currentTimeMillis(), simulated = true,
            ),
        )
    }

    private val engineListener = object : KeywordEngine.Listener {
        override fun onDetection(detection: Detection) {
            lifecycleScope.launch(Dispatchers.Main) { handleDetection(detection) }
        }

        override fun onError(message: String) {
            Log.e(TAG, message)
            lifecycleScope.launch(Dispatchers.Main) {
                _state.update { it.copy(status = EngineStatus.ERROR, error = message) }
                addLog(message, isError = true)
            }
        }
    }

    private fun handleDetection(detection: Detection) {
        val now = SystemClock.elapsedRealtime()
        val keyword = detection.keyword
        val transition = keyword?.let { stateMachine.onKeyword(it, now) } ?: Transition.IGNORED
        _state.update {
            it.copy(
                mode = stateMachine.mode,
                nowMs = now,
                lastDetection = detection,
                metrics = if (detection.simulated) it.metrics else it.metrics.copy(
                    detections = it.metrics.detections + 1,
                    lastLatencyEstimateMs = detection.latencyEstimateMs,
                    lastArmToResultMs = detection.armToResultMs,
                ),
            )
        }
        val label = keyword?.phrase ?: "UNKNOWN"
        addLog(
            "${if (detection.simulated) "SIM " else ""}$label ← text='${detection.rawText}' " +
                "reason=${detection.reason} src=${detection.source} → $transition" +
                (detection.latencyEstimateMs?.let { " (~${it}ms)" } ?: ""),
        )
        Log.i(TAG, "detection keyword=$label text='${detection.rawText}' reason=${detection.reason} transition=$transition")
    }

    private suspend fun tickLoop() {
        var ticks = 0
        while (lifecycleScope.isActive) {
            delay(TICK_MS)
            val now = SystemClock.elapsedRealtime()
            if (stateMachine.onTick(now) == Transition.TIMED_OUT) addLog("Listening timed out → IDLE")
            if (stateMachine.mode is ListeningMode.Listening || _state.value.mode != stateMachine.mode) {
                _state.update { it.copy(mode = stateMachine.mode, nowMs = now) }
            }
            if (++ticks % METRICS_EVERY_TICKS == 0) {
                val sample = withContext(Dispatchers.Default) { metrics.sample() }
                _state.update {
                    it.copy(metrics = it.metrics.copy(pssKb = sample.pssKb, cpuPercentOfOneCore = sample.cpuPercentOfOneCore))
                }
                if (_state.value.status == EngineStatus.RUNNING && ticks % (METRICS_EVERY_TICKS * 5) == 0) {
                    Log.i(TAG, "metrics pssKb=${sample.pssKb} cpu=%.1f%%".format(sample.cpuPercentOfOneCore))
                }
            }
        }
    }

    private fun addLog(message: String, isError: Boolean = false) {
        val entry = LogEntry(++logId, System.currentTimeMillis(), message, isError)
        _state.update { it.copy(log = (listOf(entry) + it.log).take(MAX_LOG)) }
    }

    override fun onDestroy() {
        // Synchronous so native SDK resources are released even if the process is about to die.
        engine?.close()
        engine = null
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, getString(R.string.notification_channel), NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val openApp = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE,
        )
        return Notification.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text, Keyword.phrases.joinToString(" · ")))
            .setContentIntent(openApp)
            .setOngoing(true)
            .build()
    }

    companion object {
        private const val TAG = "KWS"
        private const val ACTION_START = "com.hoffe86.speechmultikeyword.START"
        private const val CHANNEL_ID = "kws"
        private const val NOTIFICATION_ID = 1
        private const val TICK_MS = 250L
        private const val METRICS_EVERY_TICKS = 4
        private const val MAX_LOG = 50

        fun startIntent(context: Context) = Intent(context, KeywordService::class.java).setAction(ACTION_START)
    }
}
