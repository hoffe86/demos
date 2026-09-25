package com.hoffe86.speechmultikeyword.kws

sealed interface ListeningMode {
    data object Idle : ListeningMode
    data class Listening(val sinceMs: Long, val untilMs: Long) : ListeningMode
}

enum class Transition { STARTED_LISTENING, EXTENDED, STOPPED_BY_KEYWORD, TIMED_OUT, IGNORED, NONE }

/**
 * "Hey Jarvis" enters the simulated listening mode, "Hold on" leaves it. The mode also
 * ends by itself after [timeoutMs] so the demo never gets stuck listening.
 */
class ListeningStateMachine(private val timeoutMs: Long) {
    init {
        require(timeoutMs > 0) { "timeoutMs must be > 0" }
    }

    var mode: ListeningMode = ListeningMode.Idle
        private set

    fun onKeyword(keyword: Keyword, nowMs: Long): Transition {
        val current = mode
        return when (keyword) {
            Keyword.HEY_JARVIS -> {
                if (current is ListeningMode.Listening) {
                    mode = current.copy(untilMs = nowMs + timeoutMs)
                    Transition.EXTENDED
                } else {
                    mode = ListeningMode.Listening(sinceMs = nowMs, untilMs = nowMs + timeoutMs)
                    Transition.STARTED_LISTENING
                }
            }
            Keyword.HOLD_ON -> {
                if (current is ListeningMode.Listening) {
                    mode = ListeningMode.Idle
                    Transition.STOPPED_BY_KEYWORD
                } else {
                    Transition.IGNORED
                }
            }
        }
    }

    fun onTick(nowMs: Long): Transition {
        val current = mode
        if (current is ListeningMode.Listening && nowMs >= current.untilMs) {
            mode = ListeningMode.Idle
            return Transition.TIMED_OUT
        }
        return Transition.NONE
    }

    fun reset() {
        mode = ListeningMode.Idle
    }
}
