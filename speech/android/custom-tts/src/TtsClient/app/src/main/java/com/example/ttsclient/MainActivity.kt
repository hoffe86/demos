package com.example.ttsclient

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ttsclient.databinding.ActivityMainBinding
import java.util.Locale

import java.util.LinkedList

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    // View Binding instance for easy access to UI elements
    private lateinit var binding: ActivityMainBinding

    // TextToSpeech instance
    private var tts: TextToSpeech? = null

    // Currently selected TTS engine package name
    private var selectedTtsEnginePackage: String? = null

    // Language management
    private var languageLocales: List<Locale> = emptyList() // List of available locales
    private var selectedLocale: Locale? = null // Currently selected locale

    // Phrase management and RecyclerView
    private lateinit var phraseLogAdapter: PhraseLogAdapter // Declare the adapter
    private var currentPhraseList: MutableList<PhraseItem> =
        mutableListOf() // The list currently displayed
    private var availablePhraseTypesDisplayNames: List<String> =
        emptyList() // Store display names for phrase types

    // Speech Synthesis Logic
    private var isSynthesizing: Boolean = false // Tracks if batch synthesis is active
    private var currentUtteranceQueue: LinkedList<PhraseItem> =
        LinkedList() // Queue for batch processing
    private var currentBatchStartIndex: Int = 0 // Start index of the current synthesis batch
    private var currentBatchEndIndex: Int = 0   // End index of the current synthesis batch

    // Logging and timing synthesis operations
    private val synthesisStartTimes = mutableMapOf<String, Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTtsEngineSelection()
        setupDisconnectButton()
        setupLanguageSelection() // Language selection setup
        setupPhraseSetSelection() // Setup phrase set selection listener
        setupRecyclerView() // Initialize RecyclerView
        setupSynthesisButtons() // Setup Start/Stop button listeners

        // Initial status update
        updateTtsStatus("Not initialized", android.R.color.holo_red_dark)
        updateButtonStates() // Initial UI state
    }

    // Initializes the TTS engine selection spinner and its listener
    private fun setupTtsEngineSelection() {
        val ttsEngines = tts?.engines ?: TextToSpeech(this, null).engines // Get available engines
        val engineNames = ttsEngines.map { it.label } // Display human-readable labels

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, engineNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTtsEngine.adapter = adapter

        binding.spinnerTtsEngine.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedEngineInfo = ttsEngines[position]
                    selectedTtsEnginePackage = selectedEngineInfo.name // Store package name

                    // Initialize TTS with the selected engine
                    initializeTtsEngine(selectedEngineInfo.name)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Dummy
                }
            }

        // Initially select the default system engine if available, or the first one
        if (ttsEngines.isNotEmpty()) {
            val defaultEngine =
                TextToSpeech(this, null).defaultEngine // Get default engine's package name
            val defaultEngineIndex = ttsEngines.indexOfFirst { it.name == defaultEngine }
            if (defaultEngineIndex != -1) {
                binding.spinnerTtsEngine.setSelection(defaultEngineIndex)
            } else {
                binding.spinnerTtsEngine.setSelection(0) // Select the first engine if default not found
            }
        }
    }

    // Sets up the disconnect button click listener
    private fun setupDisconnectButton() {
        binding.btnDisconnectTts.setOnClickListener {
            disconnectTtsEngine()
        }
    }

    // Initializes or re-initializes the TextToSpeech instance with a specific engine
    private fun initializeTtsEngine(enginePackage: String) {
        // Disconnect any existing TTS instance first
        disconnectTtsEngine()

        updateTtsStatus(
            "Initializing $enginePackage...",
            com.google.android.material.R.color.design_default_color_primary_dark
        )
        Log.d(TAG, "Attempting to initialize TTS engine: $enginePackage")

        tts = TextToSpeech(this, this, enginePackage) // 'this' for OnInitListener
        // The OnInitListener will be called once the initialization is complete
    }

    // Disconnects and shuts down the current TextToSpeech instance
    private fun disconnectTtsEngine() {
        tts?.stop()
        tts?.shutdown()
        tts = null // Clear the instance
        selectedTtsEnginePackage = null
        selectedLocale = null
        stopSynthesis(manualStop = false) // Call stopSynthesis to clean up state
        currentUtteranceQueue.clear() // Clear any pending utterances

        updateTtsStatus(
            "Disconnected",
            com.google.android.material.R.color.design_default_color_primary_dark
        )

        // Disable relevant UI elements after disconnect
        binding.btnDisconnectTts.isEnabled = false
        binding.spinnerLanguage.isEnabled = false
        binding.spinnerPhraseSet.isEnabled = false // Disable phrase set spinner
        binding.etStartIndex.isEnabled = false
        binding.etEndIndex.isEnabled = false
        binding.btnStartSynthesis.isEnabled = false
        binding.btnStopSynthesis.isEnabled = false

        // Clear language status
        binding.tvSelectedLanguage.text = "Selected Language: N/A"

        // Clear displayed phrases
        currentPhraseList.clear()
        phraseLogAdapter.submitList(currentPhraseList)
        updateButtonStates() // Update UI state after disconnect

        Log.d(TAG, "TTS engine disconnected.")
    }

    // Callback method for TextToSpeech initialization status
    // @param status The initialization status: SUCCESS or ERROR
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "TTS engine initialized successfully: ${tts?.defaultEngine}")
            updateTtsStatus(
                "Initialized: ${selectedTtsEnginePackage ?: "Default"}",
                android.R.color.holo_green_dark
            )

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    utteranceId?.let {
                        val startTimeNanos = System.nanoTime()
                        synthesisStartTimes[it] = startTimeNanos
                        Log.d(TAG, "Synthesis started for $it at $startTimeNanos ns")
                        runOnUiThread {
                            // Update the PhraseItem status in the adapter
                            phraseLogAdapter.updatePhraseStatus(
                                it,
                                "In Progress",
                                startTime = startTimeNanos
                            )
                            // Scroll to the current item being spoken
                            val index = currentPhraseList.indexOfFirst { item -> item.id == it }
                            if (index != -1) {
                                binding.recyclerViewPhrases.smoothScrollToPosition(index)
                            }
                        }
                    }
                }

                override fun onDone(utteranceId: String?) {
                    utteranceId?.let {
                        val startTimeNanos = synthesisStartTimes[it] ?: 0L
                        val endTimeNanos = System.nanoTime()
                        val durationMillis = (endTimeNanos - startTimeNanos) / 1_000_000.0

                        Log.d(
                            TAG,
                            "Synthesis done for $it. Duration: ${
                                String.format(
                                    "%.2f",
                                    durationMillis
                                )
                            } ms"
                        )
                        synthesisStartTimes.remove(it)
                        runOnUiThread {
                            // Update the PhraseItem status and timings in the adapter
                            phraseLogAdapter.updatePhraseStatus(
                                it,
                                "Done",
                                endTime = endTimeNanos,
                                durationMs = durationMillis
                            )
                            // Speak the next phrase in the batch
                            speakNextPhrase()
                        }
                    }
                }

                @Deprecated("Deprecated in API level 22")
                override fun onError(utteranceId: String?) {
                    utteranceId?.let {
                        Log.e(TAG, "Error during synthesis for $it")
                        synthesisStartTimes.remove(it)
                        runOnUiThread {
                            phraseLogAdapter.updatePhraseStatus(
                                it,
                                "Error"
                            ) // Update status to error
                            Toast.makeText(
                                this@MainActivity,
                                "Error speaking: ${it.take(20)}",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Stop the batch synthesis if error
                            stopSynthesis(manualStop = false)
                        }
                    }
                }

                override fun onStop(utteranceId: String?, interrupted: Boolean) {
                    utteranceId?.let {
                        Log.w(TAG, "Synthesis stopped for $it, interrupted: $interrupted")
                        synthesisStartTimes.remove(it)
                        runOnUiThread {
                            phraseLogAdapter.updatePhraseStatus(
                                it,
                                "Stopped"
                            ) // Update status to stopped
                            // If a manual stop occurred, stopSynthesis() is already handling state
                            // If it was interrupted by another app or system, we should clean up
                            if (isSynthesizing) { // Check if synthesis was still considered active
                                stopSynthesis(manualStop = false)
                            }
                            Toast.makeText(
                                this@MainActivity,
                                "Stopped: ${it.take(20)}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            binding.btnDisconnectTts.isEnabled = true
            binding.spinnerLanguage.isEnabled = true

            updateButtonStates() // Update UI state after init
            populateLanguageSpinner()

        } else {
            Log.e(TAG, "TTS engine initialization failed with status: $status")
            updateTtsStatus("Initialization failed", android.R.color.holo_red_dark)
            Toast.makeText(this, "TTS initialization failed: $status", Toast.LENGTH_LONG).show()

            updateButtonStates() // Update UI state after init failure
        }
    }

    // Populates the language spinner with locales supported by the active TTS engine
    private fun populateLanguageSpinner() {
        val ttsInstance = tts ?: return // Ensure TTS is initialized

        // Get available locales from the TTS engine
        // Filter for languages that are actually supported (LANG_AVAILABLE or LANG_COUNTRY_AVAILABLE)
        languageLocales = ttsInstance.availableLanguages.filter { locale ->
            val result = ttsInstance.isLanguageAvailable(locale)
            result == TextToSpeech.LANG_AVAILABLE || result == TextToSpeech.LANG_COUNTRY_AVAILABLE
        }.sortedBy { it.displayName } // Sort alphabetically for easier selection

        val languageDisplayNames = languageLocales.map { locale ->
            locale.displayName // e.g., "English (United States)", "Deutsch (Deutschland)"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languageDisplayNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLanguage.adapter = adapter

        if (languageLocales.isNotEmpty()) {
            // Attempt to select the device's default locale if available
            val defaultLocale = Locale.getDefault()
            val defaultLocaleIndex = languageLocales.indexOfFirst { it == defaultLocale }
            if (defaultLocaleIndex != -1) {
                binding.spinnerLanguage.setSelection(defaultLocaleIndex)
            } else {
                binding.spinnerLanguage.setSelection(0) // Select the first available language
            }
        } else {
            // No languages supported by this engine
            binding.tvSelectedLanguage.text =
                "Selected Language: No languages available for this engine"
            Toast.makeText(this, "No languages available for this TTS engine", Toast.LENGTH_LONG)
                .show()
            binding.spinnerLanguage.isEnabled = false // Disable spinner if no languages
            // Disable other UI elements that depend on language selection
            binding.spinnerPhraseSet.isEnabled = false
            binding.etStartIndex.isEnabled = false
            binding.etEndIndex.isEnabled = false
            binding.btnStartSynthesis.isEnabled = false
            binding.btnStopSynthesis.isEnabled = false
        }
    }

    // Sets up the language selection spinner's listener
    private fun setupLanguageSelection() {
        binding.spinnerLanguage.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (languageLocales.isNotEmpty()) {
                        val locale = languageLocales[position]
                        val result =
                            tts?.setLanguage(locale) // Try to set the language on TTS engine

                        if (result == TextToSpeech.LANG_AVAILABLE || result == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            selectedLocale = locale
                            binding.tvSelectedLanguage.text =
                                "Selected Language: ${locale.displayName}"
                            Toast.makeText(
                                this@MainActivity,
                                "Language set to: ${locale.displayName}",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d(TAG, "Language set to: ${locale.displayName}")

                            // Enable phrase set selection and synthesis buttons
                            binding.spinnerPhraseSet.isEnabled = true
                            binding.etStartIndex.isEnabled = true
                            binding.etEndIndex.isEnabled = true
                            // Populate and enable phrase set spinner
                            populatePhraseSetSpinner(locale) // Call to populate the phrase set spinner

                            binding.btnStartSynthesis.isEnabled = true
                            binding.btnStopSynthesis.isEnabled =
                                true // Will be enabled/disabled by synthesis logic later

                        } else {
                            selectedLocale = null // Clear selected locale if setting failed
                            binding.tvSelectedLanguage.text =
                                "Selected Language: Not supported for this engine"
                            Toast.makeText(
                                this@MainActivity,
                                "Language ${locale.displayName} not supported by this engine",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.e(
                                TAG,
                                "Language ${locale.displayName} not supported by this engine, result: $result"
                            )

                            // Disable subsequent UI elements if language cannot be set
                            binding.spinnerPhraseSet.isEnabled = false
                            binding.etStartIndex.isEnabled = false
                            binding.etEndIndex.isEnabled = false
                            binding.btnStartSynthesis.isEnabled = false
                            binding.btnStopSynthesis.isEnabled = false
                            currentPhraseList.clear() // Clear displayed phrases
                            phraseLogAdapter.submitList(currentPhraseList)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Dummy
                }
            }
    }

    // Populates the phrase set spinner with available types for the given locale
    private fun populatePhraseSetSpinner(locale: Locale) {
        availablePhraseTypesDisplayNames = PhraseData.getPhraseTypeDisplayNames(locale)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            availablePhraseTypesDisplayNames
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPhraseSet.adapter = adapter

        if (availablePhraseTypesDisplayNames.isNotEmpty()) {
            binding.spinnerPhraseSet.setSelection(0) // Select the first phrase set by default
            // The onItemSelected listener for spinnerPhraseSet will be triggered here
            // and will load the phrases into the RecyclerView
        } else {
            Log.e(TAG, "No phrase sets found for ${locale.displayName}")
            Toast.makeText(
                this,
                "No phrase sets found for ${locale.displayName}.",
                Toast.LENGTH_SHORT
            ).show()
            binding.spinnerPhraseSet.isEnabled = false
            binding.etStartIndex.isEnabled = false
            binding.etEndIndex.isEnabled = false
            binding.btnStartSynthesis.isEnabled = false
            binding.btnStopSynthesis.isEnabled = false
            currentPhraseList.clear()
            phraseLogAdapter.submitList(currentPhraseList)
        }
    }

    // Sets up the phrase set selection spinner's listener
    private fun setupPhraseSetSelection() {
        binding.spinnerPhraseSet.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (selectedLocale != null && availablePhraseTypesDisplayNames.isNotEmpty()) {
                        val selectedPhraseTypeDisplayName =
                            availablePhraseTypesDisplayNames[position]
                        val selectedPhraseType =
                            PhraseData.getPhraseTypeFromDisplayName(selectedPhraseTypeDisplayName)

                        if (selectedPhraseType != null) {
                            Log.d(
                                TAG,
                                "Selected phrase type: $selectedPhraseType for locale: ${selectedLocale!!.displayName}"
                            )
                            loadAndDisplayPhrases(
                                selectedLocale!!,
                                selectedPhraseType
                            ) // Load phrases into RecyclerView
                        } else {
                            Log.e(
                                TAG,
                                "Could not resolve PhraseType from display name: $selectedPhraseTypeDisplayName"
                            )
                            Toast.makeText(
                                this@MainActivity,
                                "Error: Invalid phrase type selected",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Dummy
                }
            }
    }

    // Initializes the RecyclerView with its adapter and layout manager
    private fun setupRecyclerView() {
        phraseLogAdapter = PhraseLogAdapter()
        binding.recyclerViewPhrases.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = phraseLogAdapter
        }
    }

    // Load and display phrases in RecyclerView based on selections
    private fun loadAndDisplayPhrases(locale: Locale, phraseType: PhraseType) {
        currentPhraseList = PhraseData.getPhrases(locale, phraseType).toMutableList()
        phraseLogAdapter.submitList(currentPhraseList)

        // Reset range indices
        binding.etStartIndex.setText("0")
        val endIndex = (currentPhraseList.size - 1).coerceAtLeast(0) // Ensure not negative
        binding.etEndIndex.setText(endIndex.toString())

        updateButtonStates() // Update UI state after loading phrases
    }

    // Sets up click listeners for Start/Stop Synthesis buttons
    private fun setupSynthesisButtons() {
        binding.btnStartSynthesis.setOnClickListener {
            startSynthesis()
        }
        binding.btnStopSynthesis.setOnClickListener {
            stopSynthesis(manualStop = true) // User initiated stop
        }
    }

     // Initiates the batch synthesis process
    private fun startSynthesis() {
        if (tts == null) {
            Toast.makeText(this, "TTS engine not initialized", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedLocale == null) {
            Toast.makeText(this, "No language selected", Toast.LENGTH_SHORT).show()
            return
        }
        if (currentPhraseList.isEmpty()) {
            Toast.makeText(this, "No phrases to synthesize", Toast.LENGTH_SHORT).show()
            return
        }

        val startIndex = binding.etStartIndex.text.toString().toIntOrNull() ?: 0
        val endIndex =
            binding.etEndIndex.text.toString().toIntOrNull() ?: (currentPhraseList.size - 1)

        if (startIndex < 0 || startIndex >= currentPhraseList.size || endIndex < 0 || endIndex >= currentPhraseList.size || startIndex > endIndex) {
            Toast.makeText(this, "Invalid phrase range", Toast.LENGTH_LONG).show()
            return
        }

        currentBatchStartIndex = startIndex
        currentBatchEndIndex = endIndex
        isSynthesizing = true
        currentUtteranceQueue.clear() // Clear any previous queue

        // Prepare the queue with selected phrases and reset their status
        for (i in currentBatchStartIndex..currentBatchEndIndex) {
            val phrase = currentPhraseList[i]
            // Reset status for the current batch
            phraseLogAdapter.updatePhraseStatus(
                phrase.id,
                "Pending",
                startTime = null,
                endTime = null,
                durationMs = null
            )
            currentUtteranceQueue.offer(phrase)
        }

        updateButtonStates() // Update UI state (enable Stop, disable Start)
        speakNextPhrase() // Start speaking the first phrase
    }

    // Stops the current batch synthesis
    // @param manualStop True if the stop was initiated by the user
    private fun stopSynthesis(manualStop: Boolean) {
        if (tts != null) {
            tts?.stop() // Stop any ongoing speech
        }
        isSynthesizing = false
        currentUtteranceQueue.clear() // Clear any remaining phrases in the queue

        // Update status for any phrases that were 'In Progress' or still 'Pending' in the batch
        if (manualStop) { // Only update if it was a manual stop
            for (i in currentBatchStartIndex..currentBatchEndIndex) {
                if (i < currentPhraseList.size) { // Ensure index is valid
                    val phrase = currentPhraseList[i]
                    if (phrase.status == "In Progress" || phrase.status == "Pending") {
                        phraseLogAdapter.updatePhraseStatus(phrase.id, "Stopped")
                    }
                }
            }
        }
        updateButtonStates() // Update UI state (enable Start, disable Stop)
        Log.d(TAG, "Synthesis stopped. Manual stop: $manualStop")
    }

    // Speaks the next phrase in the queue
    private fun speakNextPhrase() {
        if (!isSynthesizing || currentUtteranceQueue.isEmpty()) {
            // If synthesis is not active or queue is empty, batch is complete or stopped.
            isSynthesizing = false // Ensure state is reset
            updateButtonStates() // Re-enable start button
            if (currentUtteranceQueue.isEmpty() && currentBatchStartIndex <= currentBatchEndIndex) {
                // Only show completion message if it completed naturally
                Log.d(TAG, "Batch synthesis completed")
                Toast.makeText(this, "Batch synthesis completed!", Toast.LENGTH_SHORT).show()
            }
            return
        }

        val phraseToSpeak =
            currentUtteranceQueue.poll() // Get and remove the next phrase from the head of the queue

        if (phraseToSpeak != null) {
            val params = Bundle()
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, phraseToSpeak.id)

            val result: Int = if (phraseToSpeak.type == PhraseType.SSML) {
                // Use speak for SSML (it handles the <speak> tag)
                tts?.speak(phraseToSpeak.text, TextToSpeech.QUEUE_ADD, params, phraseToSpeak.id)
                    ?: TextToSpeech.ERROR
            } else {
                // Use speak for plain text
                tts?.speak(phraseToSpeak.text, TextToSpeech.QUEUE_ADD, params, phraseToSpeak.id)
                    ?: TextToSpeech.ERROR
            }

            if (result == TextToSpeech.ERROR) {
                Log.e(TAG, "Error synthesizing phrase: ${phraseToSpeak.text}")
                runOnUiThread {
                    phraseLogAdapter.updatePhraseStatus(phraseToSpeak.id, "Error")
                    // If one phrase errors, we might want to stop the whole batch or just skip it
                    // For now, let's stop the batch
                    stopSynthesis(manualStop = false)
                }
            }
        }
    }

    // Manages the enabled/disabled state of various UI elements
    private fun updateButtonStates() {
        // TTS Engine Selection
        binding.btnDisconnectTts.isEnabled =
            tts != null && tts?.isSpeaking == false // Enable disconnect only if not speaking

        // Language Selection
        binding.spinnerLanguage.isEnabled = tts != null && !isSynthesizing

        // Phrase Set Selection
        binding.spinnerPhraseSet.isEnabled = selectedLocale != null && !isSynthesizing

        // Phrase Range
        val hasPhrases = currentPhraseList.isNotEmpty()
        binding.etStartIndex.isEnabled = hasPhrases && !isSynthesizing
        binding.etEndIndex.isEnabled = hasPhrases && !isSynthesizing

        // Synthesis Controls
        binding.btnStartSynthesis.isEnabled =
            tts != null && selectedLocale != null && hasPhrases && !isSynthesizing
        binding.btnStopSynthesis.isEnabled =
            tts != null && isSynthesizing // Only enabled when synthesis is active
    }

    private fun updateTtsStatus(statusText: String, colorResId: Int) {
        binding.tvTtsStatus.text = statusText
        binding.tvTtsStatus.setTextColor(resources.getColor(colorResId, theme))
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "TTSClientApp"
    }
}
