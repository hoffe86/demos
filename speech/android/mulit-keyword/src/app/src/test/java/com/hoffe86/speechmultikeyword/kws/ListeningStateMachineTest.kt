package com.hoffe86.speechmultikeyword.kws

import org.junit.Assert.assertEquals
import org.junit.Test

class ListeningStateMachineTest {
    private val sm = ListeningStateMachine(timeoutMs = 1_000)

    @Test
    fun `hey jarvis starts listening`() {
        assertEquals(Transition.STARTED_LISTENING, sm.onKeyword(Keyword.HEY_JARVIS, nowMs = 100))
        assertEquals(ListeningMode.Listening(sinceMs = 100, untilMs = 1_100), sm.mode)
    }

    @Test
    fun `hold on ends listening`() {
        sm.onKeyword(Keyword.HEY_JARVIS, nowMs = 0)
        assertEquals(Transition.STOPPED_BY_KEYWORD, sm.onKeyword(Keyword.HOLD_ON, nowMs = 500))
        assertEquals(ListeningMode.Idle, sm.mode)
    }

    @Test
    fun `hold on while idle is ignored`() {
        assertEquals(Transition.IGNORED, sm.onKeyword(Keyword.HOLD_ON, nowMs = 0))
        assertEquals(ListeningMode.Idle, sm.mode)
    }

    @Test
    fun `hey jarvis while listening extends the timeout and keeps start time`() {
        sm.onKeyword(Keyword.HEY_JARVIS, nowMs = 0)
        assertEquals(Transition.EXTENDED, sm.onKeyword(Keyword.HEY_JARVIS, nowMs = 800))
        assertEquals(ListeningMode.Listening(sinceMs = 0, untilMs = 1_800), sm.mode)
    }

    @Test
    fun `listening times out back to idle`() {
        sm.onKeyword(Keyword.HEY_JARVIS, nowMs = 0)
        assertEquals(Transition.NONE, sm.onTick(nowMs = 999))
        assertEquals(Transition.TIMED_OUT, sm.onTick(nowMs = 1_000))
        assertEquals(ListeningMode.Idle, sm.mode)
    }

    @Test
    fun `tick while idle does nothing`() {
        assertEquals(Transition.NONE, sm.onTick(nowMs = 10_000))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `rejects non positive timeout`() {
        ListeningStateMachine(timeoutMs = 0)
    }
}
