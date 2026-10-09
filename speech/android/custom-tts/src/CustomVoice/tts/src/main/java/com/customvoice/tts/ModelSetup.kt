package com.customvoice.tts

import java.io.File
import java.io.IOException
import java.io.InputStream

internal object ModelSetup {
    fun validateConfiguration(voice: String, license: String) {
        require(voice.isNotBlank()) { "Set voice in tts/model.properties before starting TTS" }
        require(license.isNotBlank()) { "Set license in tts/model.properties before starting TTS" }
    }

    fun copyAssets(files: Array<String>?, destination: File, open: (String) -> InputStream): String {
        if (files.isNullOrEmpty()) {
            throw IOException("Provide licensed model files in tts/src/main/assets/model before starting TTS")
        }
        if (!destination.isDirectory && !destination.mkdirs()) {
            throw IOException("Cannot create model directory")
        }
        for (name in files) {
            val output = File(destination, name)
            // Refresh files so an interrupted copy or a previous model cannot be reused.
            open(name).use { input ->
                output.outputStream().use { input.copyTo(it) }
            }
            if (output.length() == 0L) {
                throw IOException("Model contains an empty file: $name")
            }
        }
        return destination.absolutePath
    }

    fun escapeVoice(voice: String): String = voice
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&apos;")

    fun configureSsml(ssml: String, voice: String): String =
        ssml.replace("__MODEL_VOICE__", escapeVoice(voice))
}
