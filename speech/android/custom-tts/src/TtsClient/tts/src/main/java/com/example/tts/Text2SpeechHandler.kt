package com.example.tts

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
        tts = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    val result = tts.setLanguage(Locale.US)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle the error
                    }
                } else {
                    // Initialization failed
                }
            }
        })
    }

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}