package com.hoffe86.speechmultikeyword.kws

import android.os.SystemClock
import android.util.Log
import com.microsoft.cognitiveservices.speech.CancellationDetails
import com.microsoft.cognitiveservices.speech.KeywordRecognitionModel
import com.microsoft.cognitiveservices.speech.KeywordRecognizer
import com.microsoft.cognitiveservices.speech.ResultReason
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * `recognizeOnceAsync` is single-shot, so this runs a dedicated thread that arms the recognizer,
 * waits for the result, reports it and immediately arms again until [stop] is called.
 */
internal class RearmingRecognizer(
    private val label: String,
    private val recognizer: KeywordRecognizer,
    private val model: KeywordRecognitionModel,
    /** Set when the recognizer can only ever fire one keyword (MULTI_TABLE). */
    private val fixedKeyword: Keyword?,
) {
    private val executor = Executors.newSingleThreadExecutor { Thread(it, "kws-$label") }

    @Volatile
    private var running = false

    fun start(listener: KeywordEngine.Listener) {
        running = true
        executor.execute { loop(listener) }
    }

    private fun loop(listener: KeywordEngine.Listener) {
        val sessionStart = SystemClock.elapsedRealtime()
        while (running) {
            val armedAt = SystemClock.elapsedRealtime()
            val result = try {
                recognizer.recognizeOnceAsync(model).get()
            } catch (e: Exception) {
                if (running) listener.onError("[$label] recognizeOnceAsync failed: ${e.message}")
                break
            }
            val now = SystemClock.elapsedRealtime()
            result.use { r ->
                when (r.reason) {
                    ResultReason.RecognizedKeyword -> {
                        val offsetMs = ticksToMs(r.offset.toLong())
                        val durationMs = ticksToMs(r.duration.toLong())
                        val detection = Detection(
                            keyword = fixedKeyword ?: Keyword.match(r.text),
                            rawText = r.text.orEmpty(),
                            reason = r.reason.name,
                            source = label,
                            offsetMs = offsetMs,
                            durationMs = durationMs,
                            armToResultMs = now - armedAt,
                            latencyEstimateMs = estimateLatencyMs(now, armedAt, sessionStart, offsetMs + durationMs),
                            wallTimeMs = System.currentTimeMillis(),
                        )
                        Log.i(TAG, "[$label] hit text='${detection.rawText}' reason=${detection.reason} " +
                            "keyword=${detection.keyword} offsetMs=$offsetMs durationMs=$durationMs " +
                            "armToResultMs=${detection.armToResultMs} latencyEstMs=${detection.latencyEstimateMs}")
                        listener.onDetection(detection)
                    }
                    ResultReason.Canceled -> {
                        if (running) {
                            val details = CancellationDetails.fromResult(r)
                            listener.onError("[$label] canceled: ${details.reason} ${details.errorCode} ${details.errorDetails}")
                            running = false
                        }
                    }
                    else -> Log.d(TAG, "[$label] result reason=${r.reason} text='${r.text}'")
                }
            }
        }
        Log.i(TAG, "[$label] loop ended")
    }

    fun stop() {
        if (!running && executor.isShutdown) return
        running = false
        runCatching { recognizer.stopRecognitionAsync().get(STOP_TIMEOUT_S, TimeUnit.SECONDS) }
            .onFailure { Log.w(TAG, "[$label] stopRecognitionAsync: ${it.message}") }
        executor.shutdown()
        if (!executor.awaitTermination(STOP_TIMEOUT_S, TimeUnit.SECONDS)) {
            Log.w(TAG, "[$label] recognizer thread did not finish in ${STOP_TIMEOUT_S}s")
        }
    }

    private companion object {
        const val STOP_TIMEOUT_S = 3L
    }
}

internal const val TAG = "KWS"
