package com.hoffe86.speechmultikeyword.kws

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KwsConfigTest {
    private fun config(
        engine: EngineType,
        name: String? = "kw-model",
        license: String? = "super-secret-license",
        tables: Map<Keyword, String> = emptyMap(),
    ) = KwsConfig(engine, "/models", name, license, tables, 15_000)

    @Test
    fun `valid from config has no problems`() {
        assertEquals(emptyList<String>(), config(EngineType.FROM_CONFIG).validate { true })
    }

    @Test
    fun `from config requires name license and existing model dir`() {
        val problems = config(EngineType.FROM_CONFIG, name = null, license = " ").validate { false }
        assertEquals(3, problems.size)
    }

    @Test
    fun `multi table requires both tables`() {
        val problems = config(EngineType.MULTI_TABLE, tables = mapOf(Keyword.HOLD_ON to "hold_on.table"))
            .validate { true }
        assertEquals(listOf("tableFiles.heyJarvis is required for MULTI_TABLE"), problems)
    }

    @Test
    fun `relative table paths resolve against model dir and absolute are kept`() {
        val cfg = config(
            EngineType.MULTI_TABLE,
            tables = mapOf(Keyword.HEY_JARVIS to "hj.table", Keyword.HOLD_ON to "/abs/c.table"),
        )
        assertEquals("/models/hj.table", cfg.resolveTable(Keyword.HEY_JARVIS))
        assertEquals("/abs/c.table", cfg.resolveTable(Keyword.HOLD_ON))
    }

    @Test
    fun `toString never contains the license`() {
        val text = config(EngineType.FROM_CONFIG).toString()
        assertFalse(text.contains("super-secret-license"))
        assertTrue(text.contains("<redacted>"))
    }
}
