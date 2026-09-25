package com.hoffe86.speechmultikeyword.kws

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LatencyTest {
    @Test
    fun `converts 100ns ticks to ms`() {
        assertEquals(1_500L, ticksToMs(15_000_000L))
    }

    @Test
    fun `uses arm time when offset is relative to the current arm`() {
        // armed at 10_000, keyword ended 800 ms into the arm, result at 11_000 -> 200 ms
        assertEquals(200L, estimateLatencyMs(nowMs = 11_000, armedAtMs = 10_000, sessionStartMs = 0, keywordEndOffsetMs = 800))
    }

    @Test
    fun `falls back to session start when offset is cumulative`() {
        // offset larger than time since arm -> relative to session start (0): 11_000 - 10_700 = 300
        assertEquals(300L, estimateLatencyMs(nowMs = 11_000, armedAtMs = 10_000, sessionStartMs = 0, keywordEndOffsetMs = 10_700))
    }

    @Test
    fun `returns null when no reference is plausible`() {
        assertNull(estimateLatencyMs(nowMs = 11_000, armedAtMs = 10_000, sessionStartMs = 9_000, keywordEndOffsetMs = 50_000))
    }

    @Test
    fun `cpu percent handles zero wall time`() {
        assertEquals(0.0, ProcessMetrics.cpuPercent(10, 0), 0.0)
        assertEquals(50.0, ProcessMetrics.cpuPercent(500, 1_000), 0.0)
    }
}
