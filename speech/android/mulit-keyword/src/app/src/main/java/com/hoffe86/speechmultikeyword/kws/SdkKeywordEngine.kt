package com.hoffe86.speechmultikeyword.kws

import android.os.SystemClock
import android.util.Log

/** Tracks every SDK object it creates and closes them in reverse creation order. */
abstract class SdkKeywordEngine : KeywordEngine {
    private val resources = ArrayDeque<AutoCloseable>()
    internal val recognizers = mutableListOf<RearmingRecognizer>()

    protected fun <T : AutoCloseable> T.track(): T = also { resources.addLast(it) }

    /** Creates the SDK objects and registers [RearmingRecognizer]s in [recognizers]. */
    protected abstract fun createRecognizers()

    final override fun load(): Long {
        val t0 = SystemClock.elapsedRealtime()
        try {
            createRecognizers()
        } catch (e: Throwable) {
            close()
            throw e
        }
        return (SystemClock.elapsedRealtime() - t0).also { Log.i(TAG, "$type loaded in ${it}ms") }
    }

    final override fun start(listener: KeywordEngine.Listener) = recognizers.forEach { it.start(listener) }

    final override fun stop() = recognizers.forEach { it.stop() }

    final override fun close() {
        stop()
        recognizers.clear()
        while (resources.isNotEmpty()) {
            val resource = resources.removeLast()
            runCatching { resource.close() }
                .onFailure { Log.w(TAG, "close ${resource.javaClass.simpleName}: ${it.message}") }
        }
        Log.i(TAG, "$type closed")
    }
}
