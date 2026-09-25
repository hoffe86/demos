package com.hoffe86.speechmultikeyword.kws

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class KeywordTest {
    @Test
    fun `matches exact phrases`() {
        assertEquals(Keyword.HEY_JARVIS, Keyword.match("Hey Jarvis"))
        assertEquals(Keyword.HOLD_ON, Keyword.match("Hold on"))
    }

    @Test
    fun `ignores case punctuation and whitespace`() {
        assertEquals(Keyword.HEY_JARVIS, Keyword.match("  hey, JARVIS! "))
        assertEquals(Keyword.HEY_JARVIS, Keyword.match("HeyJarvis"))
        assertEquals(Keyword.HOLD_ON, Keyword.match("holdon"))
        assertEquals(Keyword.HOLD_ON, Keyword.match("HOLD ON."))
    }

    @Test
    fun `matches phrase contained in longer text`() {
        assertEquals(Keyword.HOLD_ON, Keyword.match("please hold on"))
    }

    @Test
    fun `returns null for unknown blank null or ambiguous text`() {
        assertNull(Keyword.match(null))
        assertNull(Keyword.match(""))
        assertNull(Keyword.match("   "))
        assertNull(Keyword.match("hello computer"))
        assertNull(Keyword.match("hey jarvis hold on"))
    }
}
