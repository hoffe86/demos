package com.customvoice.tts

import android.content.Context
import android.media.AudioFormat
import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeechService
import android.util.Log
import com.microsoft.cognitiveservices.speech.EmbeddedSpeechConfig
import com.microsoft.cognitiveservices.speech.ResultReason
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails
import com.microsoft.cognitiveservices.speech.SpeechSynthesisOutputFormat
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer
import java.io.File
import java.util.Locale

/**
 * Custom Text-to-Speech (TTS) service implementation.
 */
class CustomTtsService : TextToSpeechService() {

    private var modelDirectory: String? = null
    private var embeddedSpeechConfig: EmbeddedSpeechConfig? = null
    private var synthesizer: SpeechSynthesizer? = null

    private var embeddedSpeechModelLicense: String? = null
    private var embeddedSpeechSynthesisVoiceName: String? = null

    override fun onCreate() {
        super.onCreate()
        // Initialize the TTS engine here if needed
        Log.i("CustomTtsService", "TTS initialized")
        this.modelDirectory = copyAssetFolder(
            "model",
            File(applicationContext.filesDir, "models/model"),
            applicationContext
        )

        if (modelDirectory != null) {
            Log.i("CustomTtsService", "Model directory: $modelDirectory")
            embeddedSpeechConfig = EmbeddedSpeechConfig.fromPath(modelDirectory)
        } else {
            Log.e("CustomTtsService", "Failed to copy model directory")
            throw Exception("Failed to copy model directory")
        }

        embeddedSpeechModelLicense = getString(com.customvoice.tts.R.string.license)
        embeddedSpeechSynthesisVoiceName = getString(com.customvoice.tts.R.string.voice)

        if (!embeddedSpeechSynthesisVoiceName!!.isEmpty() && !embeddedSpeechModelLicense!!.isEmpty()) {
            // Selects the embedded speech synthesis voice to use.
            embeddedSpeechConfig!!.setSpeechSynthesisVoice(
                embeddedSpeechSynthesisVoiceName,
                embeddedSpeechModelLicense
            );

            if (embeddedSpeechSynthesisVoiceName!!.contains("Neural")) {
                // Embedded neural voices only support 24 kHz sample rate.
                embeddedSpeechConfig!!.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Riff24Khz16BitMonoPcm);
            }

            //val audiConfig = AudioConfig.fromStreamInput(mSynthCallback)
            synthesizer = SpeechSynthesizer(embeddedSpeechConfig);

            val task = synthesizer!!.getVoicesAsync().get()
            if (task.reason == ResultReason.VoicesListRetrieved) {
                for (voice in task.voices) {
                    Log.i("CustomTtsService", "Available voice: ${voice.name}")
                }
            } else {
                Log.e("CustomTtsService", "Failed to get voices")
            }
        }
    }

    fun copyAssetFolder(assetFolder: String, destination: File, context: Context): String? {

        try {
            val assetManager = context.assets
            val files = assetManager.list(assetFolder) ?: return null

            if (!destination.exists()) {
                destination.mkdirs()
            }

            for (file in files) {
                val outFile = File(destination, file)
                if (!outFile.exists()) {
                    val inputStream = assetManager.open("$assetFolder/$file")
                    inputStream.use { input ->
                        outFile.outputStream().use { output -> input.copyTo(output) }
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("CustomTtsService", "Error copying asset folder", ex)
        }

        return destination.absolutePath
    }

    override fun onIsLanguageAvailable(
        lang: String?,
        country: String?,
        variant: String?
    ): Int {
        Log.i("CustomTtsService", "onIsLanguageAvailable called")
        if (lang?.compareTo("deu") == 0) {
            Log.i("CustomTtsService", "German language is available")
            return TextToSpeech.LANG_AVAILABLE
        }

        return TextToSpeech.LANG_NOT_SUPPORTED
    }

    override fun onGetLanguage(): Array<out String?>? {
        Log.i("CustomTtsService", "onGetLanguage called")
        return arrayOf(
            "deu", // German language code
        )
    }

    override fun onLoadLanguage(
        lang: String?,
        country: String?,
        variant: String?
    ): Int {
        Log.i("CustomTtsService", "onLoadLanguage called")
        return TextToSpeech.LANG_AVAILABLE
    }

    override fun onStop() {
        Log.i("CustomTtsService", "onStop called")
        synthesizer!!.StopSpeakingAsync()
    }

    override fun onSynthesizeText(
        request: SynthesisRequest?,
        callback: SynthesisCallback?
    ) {
        try {
            Log.i("CustomTtsService", "onSynthesizeText called")

            val text = request!!.charSequenceText.toString()

            if (synthesizer == null) {
                Log.e("CustomTtsService", "Speech synthesizer is not initialized")
                callback!!.error(TextToSpeech.ERROR_SERVICE)
            }

            callback!!.start(24000, AudioFormat.ENCODING_PCM_16BIT, 1)
            val isSsml = (text.trim().startsWith("<speak", ignoreCase = true) || text.trim()
                .startsWith("<?xml")) && text.trim()
                .endsWith("</speak>", ignoreCase = true)


            var task: com.microsoft.cognitiveservices.speech.SpeechSynthesisResult
            if (isSsml) {
                Log.i("CustomTtsService", "Processing SSML text")
                // Ensure SSML has required tags and attributes
                var ssmlText = text
                // Try to parse and check for <voice> and required attributes, else wrap
                if (text.contains("<voice") && text.contains("name=")) {
                    ssmlText = text
                } else {
                    // Extract language and voice name from config or fallback
                    val text = text.replace("<?xml version=\"1.0\"?><speak>", "")
                        .replace("</speak>", "")
                    val lang = "de-DE"
                    val gender = "female"
                    val voiceName = embeddedSpeechSynthesisVoiceName
                        ?: "__MODEL_VOICE__"
                    ssmlText =
                        "<?xml version=\"1.0\"?><speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='$lang'>" +
                                "<voice xml:lang='$lang' xml:gender='$gender' name='$voiceName'>$text</voice></speak>"
                }

                task = synthesizer!!.StartSpeakingSsmlAsync(ssmlText).get()
            } else {
                Log.i("CustomTtsService", "Processing plain text")
                task = synthesizer!!.StartSpeakingTextAsync(text).get()
            }

            if (task.reason == ResultReason.Canceled) {
                val cancellation: SpeechSynthesisCancellationDetails =
                    SpeechSynthesisCancellationDetails.fromResult(task);
                Log.e("CustomTtsService", "Synthesis canceled: ${cancellation.reason}")

                callback.error(TextToSpeech.ERROR_SYNTHESIS)
            } else if (task.reason == ResultReason.SynthesizingAudioStarted) {
                Log.i("CustomTtsService", "Synthesis started")
                callback.start(24000, AudioFormat.ENCODING_PCM_16BIT, 1)
            } else if (task.reason == ResultReason.SynthesizingAudioCompleted) {
                Log.i("CustomTtsService", "Synthesis audio completed")
                callback.audioAvailable(task.audioData, 0, task.audioData.size)
            } else {
                Log.e("CustomTtsService", "Synthesis failed: ${task.reason}")
                callback.error(TextToSpeech.ERROR_SYNTHESIS)
            }

            Log.i("CustomTtsService", "Synthesis completed")

            callback.done()

        } catch (e: Exception) {
            Log.e("CustomTtsService", "Failed to synthesize text to speech", e)
            callback?.error(TextToSpeech.ERROR_SYNTHESIS)
        }
    }
}
