package com.hoffe86.speechmultikeyword.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hoffe86.speechmultikeyword.BuildConfig
import com.hoffe86.speechmultikeyword.kws.EngineType
import com.hoffe86.speechmultikeyword.service.KeywordService
import com.hoffe86.speechmultikeyword.service.UiState
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    private val service = MutableStateFlow<KeywordService?>(null)
    private val fallbackState = MutableStateFlow(UiState(engineType = EngineType.valueOf(BuildConfig.KWS_ENGINE)))

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            service.value = (binder as KeywordService.LocalBinder).service
        }

        override fun onServiceDisconnected(name: ComponentName) {
            service.value = null
        }
    }

    private var startAfterPermission = false

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (!startAfterPermission) return@registerForActivityResult
        startAfterPermission = false
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            startKeywordService()
        } else {
            Toast.makeText(this, "Microphone permission is required for keyword spotting", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bound by service.collectAsStateWithLifecycle()
            val state by (bound?.state ?: fallbackState).collectAsState()
            SpeechTheme {
                MainScreen(
                    state = state,
                    onStart = ::onStartClicked,
                    onStop = { bound?.stopEngine() },
                    onSimulate = { keyword -> bound?.simulate(keyword) },
                    showDebug = BuildConfig.DEBUG,
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(Intent(this, KeywordService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        unbindService(connection)
        service.value = null
        super.onStop()
    }

    private fun onStartClicked() {
        val missing = listOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS)
            .filter { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
        startAfterPermission = Manifest.permission.RECORD_AUDIO in missing
        if (missing.isNotEmpty()) permissionLauncher.launch(missing.toTypedArray())
        // The notification permission is optional: the foreground service runs without it.
        if (!startAfterPermission) startKeywordService()
    }

    private fun startKeywordService() {
        startForegroundService(KeywordService.startIntent(this))
    }
}
