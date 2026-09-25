package com.hoffe86.speechmultikeyword.kws

/** Speech SDK offsets and durations are in 100 ns ticks. */
fun ticksToMs(ticks: Long): Long = ticks / 10_000

/**
 * Estimates "end of spoken keyword -> result delivered" latency.
 *
 * The SDK reports the keyword's audio offset, but it is not documented whether the offset is
 * relative to the current `recognizeOnceAsync` call or to the first one on that recognizer.
 * We try the arm time first, then the session start, and return null if neither is plausible.
 */
fun estimateLatencyMs(nowMs: Long, armedAtMs: Long, sessionStartMs: Long, keywordEndOffsetMs: Long): Long? {
    for (reference in longArrayOf(armedAtMs, sessionStartMs)) {
        val latency = nowMs - reference - keywordEndOffsetMs
        if (latency in 0..MAX_PLAUSIBLE_LATENCY_MS) return latency
    }
    return null
}

private const val MAX_PLAUSIBLE_LATENCY_MS = 5_000L
