package com.hoffe86.speechmultikeyword.kws

import com.microsoft.cognitiveservices.speech.KeywordRecognitionModel
import com.microsoft.cognitiveservices.speech.KeywordRecognizer
import com.microsoft.cognitiveservices.speech.audio.AudioConfig

/**
 * Fallback path: one [KeywordRecognizer] per keyword, each with its own Speech Studio `.table`
 * model, all sharing one default-microphone [AudioConfig]. The keyword is known from which
 * recognizer fired. CPU and memory scale with the number of keywords.
 */
class MultiTableEngine(private val config: KwsConfig) : SdkKeywordEngine() {
    override val type = EngineType.MULTI_TABLE

    override fun createRecognizers() {
        val audio = AudioConfig.fromDefaultMicrophoneInput().track()
        Keyword.entries.forEach { keyword ->
            val path = requireNotNull(config.resolveTable(keyword)) { "table file missing for $keyword" }
            val model = KeywordRecognitionModel.fromFile(path).track()
            val recognizer = KeywordRecognizer(audio).track()
            recognizers += RearmingRecognizer("table-${KwsConfig.jsonKey(keyword)}", recognizer, model, keyword)
        }
    }
}
