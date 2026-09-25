package com.hoffe86.speechmultikeyword.kws

import java.io.File

enum class EngineType { FROM_CONFIG, MULTI_TABLE }

/**
 * Runtime configuration pushed to the device with adb (never bundled in the APK or committed).
 * [keywordModelLicense] is a secret: it is redacted from [toString] and must never be logged.
 */
class KwsConfig(
    val engine: EngineType,
    val modelDir: String,
    val keywordModelName: String?,
    val keywordModelLicense: String?,
    val tableFiles: Map<Keyword, String>,
    val listeningTimeoutMs: Long,
) {
    fun resolveTable(keyword: Keyword): String? =
        tableFiles[keyword]?.let { if (File(it).isAbsolute) it else File(modelDir, it).path }

    /** Returns human-readable problems; empty means the config is usable for [engine]. */
    fun validate(fileExists: (String) -> Boolean = { File(it).exists() }): List<String> = buildList {
        if (listeningTimeoutMs <= 0) add("listeningTimeoutSeconds must be > 0")
        when (engine) {
            EngineType.FROM_CONFIG -> {
                if (modelDir.isBlank()) add("modelDir is required for FROM_CONFIG")
                else if (!fileExists(modelDir)) add("modelDir does not exist: $modelDir")
                if (keywordModelName.isNullOrBlank()) add("keywordModelName is required for FROM_CONFIG")
                if (keywordModelLicense.isNullOrBlank()) add("keywordModelLicense is required for FROM_CONFIG")
            }
            EngineType.MULTI_TABLE -> Keyword.entries.forEach { keyword ->
                val path = resolveTable(keyword)
                if (path == null) add("tableFiles.${jsonKey(keyword)} is required for MULTI_TABLE")
                else if (!fileExists(path)) add("table file does not exist: $path")
            }
        }
    }

    override fun toString(): String =
        "KwsConfig(engine=$engine, modelDir=$modelDir, keywordModelName=$keywordModelName, " +
            "keywordModelLicense=${if (keywordModelLicense.isNullOrEmpty()) "<missing>" else "<redacted>"}, " +
            "tableFiles=$tableFiles, listeningTimeoutMs=$listeningTimeoutMs)"

    companion object {
        const val FILE_NAME = "kws-config.json"
        const val DEFAULT_TIMEOUT_MS = 15_000L

        fun jsonKey(keyword: Keyword): String = when (keyword) {
            Keyword.HEY_JARVIS -> "heyJarvis"
            Keyword.HOLD_ON -> "holdOn"
        }
    }
}
