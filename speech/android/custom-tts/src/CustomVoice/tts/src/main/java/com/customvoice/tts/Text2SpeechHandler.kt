package com.customvoice.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class Text2SpeechHandler {

    private lateinit var tts: TextToSpeech

    constructor()
    constructor(context: Context) {
        initializeTTS(context)
    }

    fun initializeTTS(context: Context) {
        // Explicitly bind to our custom TTS engine (CustomTtsService), which is packaged
        // inside this app under the application id. Without specifying the engine
        // package here, TextToSpeech falls back to the system default engine and
        // CustomTtsService/embedded speech is never invoked, so no audio is produced.
        tts = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    // CustomTtsService only advertises German ("deu") as an available language.
                    val result = tts.setLanguage(Locale.GERMANY)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle the error
                    }
                } else {
                    // Initialization failed
                }
            }
        }, context.packageName)
    }

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }

}