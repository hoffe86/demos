package com.hoffe86.speechmultikeyword.kws

import com.microsoft.cognitiveservices.speech.EmbeddedSpeechConfig
import com.microsoft.cognitiveservices.speech.KeywordRecognitionModel
import com.microsoft.cognitiveservices.speech.KeywordRecognizer
import com.microsoft.cognitiveservices.speech.audio.AudioConfig

/**
 * Default path: one licensed embedded keyword model carrying all user-defined wake words,
 * served by a single [KeywordRecognizer]. The fired keyword is derived from `result.text`.
 */
class FromConfigEngine(private val config: KwsConfig) : SdkKeywordEngine() {
    override val type = EngineType.FROM_CONFIG

    override fun createRecognizers() {
        val speechConfig = EmbeddedSpeechConfig.fromPath(config.modelDir).track()
        speechConfig.setKeywordRecognitionModel(
            requireNotNull(config.keywordModelName) { "keywordModelName missing" },
            requireNotNull(config.keywordModelLicense) { "keywordModelLicense missing" },
        )
        val model = KeywordRecognitionModel.fromConfig(speechConfig, Keyword.phrases).track()
        val audio = AudioConfig.fromDefaultMicrophoneInput().track()
        val recognizer = KeywordRecognizer(audio).track()
        recognizers += RearmingRecognizer("from-config", recognizer, model, fixedKeyword = null)
    }
}
