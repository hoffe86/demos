package com.hoffe86.speechmultikeyword.kws

import org.json.JSONObject
import java.io.File

/**
 * Reads `<externalFilesDir>/kws-config.json`:
 * ```
 * {
 *   "engine": "FROM_CONFIG",                 // optional, overrides BuildConfig.KWS_ENGINE
 *   "modelDir": "models",                    // optional, relative to the config file's dir
 *   "keywordModelName": "...",               // FROM_CONFIG only
 *   "keywordModelLicense": "...",            // FROM_CONFIG only, secret
 *   "tableFiles": { "heyJarvis": "hey_jarvis.table", "holdOn": "hold_on.table" }, // MULTI_TABLE
 *   "listeningTimeoutSeconds": 15
 * }
 * ```
 */
object KwsConfigLoader {
    fun load(baseDir: File, defaultEngine: EngineType): Result<KwsConfig> = runCatching {
        val file = File(baseDir, KwsConfig.FILE_NAME)
        require(file.isFile) { "Config not found: ${file.path}. Push it with adb (see README)." }
        val json = JSONObject(file.readText())

        val engine = json.optString("engine").takeIf { it.isNotBlank() }
            ?.let { EngineType.valueOf(it.uppercase()) } ?: defaultEngine
        val modelDirRaw = json.optString("modelDir").ifBlank { "models" }
        val modelDir = File(modelDirRaw).let { if (it.isAbsolute) it else File(baseDir, modelDirRaw) }.path
        val tables = json.optJSONObject("tableFiles")
        val tableFiles = Keyword.entries.mapNotNull { keyword ->
            tables?.optString(KwsConfig.jsonKey(keyword))?.takeIf { it.isNotBlank() }?.let { keyword to it }
        }.toMap()
        val timeoutSeconds = json.optLong("listeningTimeoutSeconds", KwsConfig.DEFAULT_TIMEOUT_MS / 1000)

        KwsConfig(
            engine = engine,
            modelDir = modelDir,
            keywordModelName = json.optString("keywordModelName").ifBlank { null },
            keywordModelLicense = json.optString("keywordModelLicense").ifBlank { null },
            tableFiles = tableFiles,
            listeningTimeoutMs = timeoutSeconds * 1000,
        )
    }
}
