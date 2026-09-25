package com.hoffe86.speechmultikeyword.kws

import android.os.Debug
import android.os.Process
import android.os.SystemClock

data class MetricsSample(val pssKb: Long, val cpuPercentOfOneCore: Double)

/** Samples process PSS and CPU usage (delta since the previous sample, as % of one core). */
class ProcessMetrics {
    private var lastCpuMs = Process.getElapsedCpuTime()
    private var lastWallMs = SystemClock.elapsedRealtime()

    fun sample(): MetricsSample {
        val cpuMs = Process.getElapsedCpuTime()
        val wallMs = SystemClock.elapsedRealtime()
        val cpuPercent = cpuPercent(cpuMs - lastCpuMs, wallMs - lastWallMs)
        lastCpuMs = cpuMs
        lastWallMs = wallMs
        return MetricsSample(pssKb = Debug.getPss(), cpuPercentOfOneCore = cpuPercent)
    }

    companion object {
        fun cpuPercent(cpuDeltaMs: Long, wallDeltaMs: Long): Double =
            if (wallDeltaMs <= 0) 0.0 else cpuDeltaMs * 100.0 / wallDeltaMs
    }
}
