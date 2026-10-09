package com.customvoice.tts

import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ModelSetupTest {
    @get:Rule
    val temporary = TemporaryFolder()

    @Test
    fun rejectsMissingVoiceAndLicense() {
        for ((voice, license) in listOf("" to "license", " " to "license", "voice" to "", "voice" to " ")) {
            assertThrows(IllegalArgumentException::class.java) {
                ModelSetup.validateConfiguration(voice, license)
            }
        }
        ModelSetup.validateConfiguration("voice", "license")
    }

    @Test
    fun rejectsMissingAndEmptyModelFolders() {
        for (files in listOf(null, emptyArray<String>())) {
            assertThrows(IOException::class.java) {
                ModelSetup.copyAssets(files, temporary.newFolder()) {
                    error("Missing assets must not be opened")
                }
            }
        }
    }

    @Test
    fun rejectsEmptyModelFiles() {
        assertThrows(IOException::class.java) {
            ModelSetup.copyAssets(arrayOf("data.bin"), temporary.newFolder()) {
                ByteArrayInputStream(byteArrayOf())
            }
        }
    }

    @Test
    fun propagatesReadFailures() {
        assertThrows(IOException::class.java) {
            ModelSetup.copyAssets(arrayOf("data.bin"), temporary.newFolder()) {
                throw IOException("Cannot read asset")
            }
        }
    }

    @Test
    fun rejectsUnwritableDestination() {
        assertThrows(IOException::class.java) {
            ModelSetup.copyAssets(arrayOf("data.bin"), temporary.newFile()) {
                ByteArrayInputStream(byteArrayOf(1))
            }
        }
    }

    @Test
    fun copiesAllAssetsAndReplacesIncompleteCopies() {
        val directory = temporary.newFolder()
        File(directory, "data.bin").writeBytes(byteArrayOf())
        val contents = byteArrayOf(1, 2, 3)
        assertEquals(directory.absolutePath, ModelSetup.copyAssets(arrayOf("data.bin", "metadata"), directory) {
            ByteArrayInputStream(contents)
        })
        for (name in arrayOf("data.bin", "metadata")) {
            assertArrayEquals(contents, File(directory, name).readBytes())
        }
    }

    @Test
    fun substitutesConfiguredVoiceWithoutChangingStyles() {
        val sample = "<voice name='__MODEL_VOICE__'><express-as style='empathetic'>Text</express-as></voice>"
        assertEquals(
            "<voice name='Custom &amp; &apos;Voice&apos;'><express-as style='empathetic'>Text</express-as></voice>",
            ModelSetup.configureSsml(sample, "Custom & 'Voice'")
        )
    }

    @Test
    fun preservesExplicitExternalVoice() {
        val sample = "<voice name='External'>Text</voice>"
        assertEquals(sample, ModelSetup.configureSsml(sample, "Local"))
    }
}
