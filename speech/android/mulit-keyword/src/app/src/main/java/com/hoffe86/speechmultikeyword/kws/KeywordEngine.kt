package com.hoffe86.speechmultikeyword.kws

data class Detection(
    /** Null when the SDK text could not be mapped to a known keyword. */
    val keyword: Keyword?,
    val rawText: String,
    val reason: String,
    /** Which recognizer produced the hit (one per engine for FROM_CONFIG, one per keyword for MULTI_TABLE). */
    val source: String,
    val offsetMs: Long,
    val durationMs: Long,
    val armToResultMs: Long,
    val latencyEstimateMs: Long?,
    val wallTimeMs: Long,
    val simulated: Boolean = false,
)

interface KeywordEngine : AutoCloseable {
    val type: EngineType

    /** Creates every SDK object and returns the elapsed time in ms. Throws on failure. */
    fun load(): Long

    /** Arms all recognizers; each one re-arms itself after every result until [stop]. */
    fun start(listener: Listener)

    fun stop()

    interface Listener {
        fun onDetection(detection: Detection)
        fun onError(message: String)
    }

    companion object {
        fun create(config: KwsConfig): KeywordEngine = when (config.engine) {
            EngineType.FROM_CONFIG -> FromConfigEngine(config)
            EngineType.MULTI_TABLE -> MultiTableEngine(config)
        }
    }
}
