package com.hoffe86.speechmultikeyword.kws

/** The wake words armed in parallel by this demo. */
enum class Keyword(val phrase: String) {
    HEY_JARVIS("Hey Jarvis"),
    HOLD_ON("Hold on");

    companion object {
        val phrases: List<String> get() = entries.map { it.phrase }

        /**
         * Maps the SDK's `result.text` to a [Keyword]. Whether `text` carries the matched
         * user-defined wake word on the `fromConfig` path is not documented, so matching is
         * deliberately tolerant (case, punctuation, whitespace) and returns null when unsure.
         */
        fun match(text: String?): Keyword? {
            val normalized = normalize(text ?: return null)
            if (normalized.isEmpty()) return null
            val compact = normalized.replace(" ", "")
            entries.firstOrNull { normalize(it.phrase).replace(" ", "") == compact }?.let { return it }
            val hits = entries.filter { normalized.contains(normalize(it.phrase)) }
            return hits.singleOrNull()
        }

        internal fun normalize(text: String): String =
            text.lowercase().replace(Regex("[^\\p{L}\\p{Nd}]+"), " ").trim()
    }
}
