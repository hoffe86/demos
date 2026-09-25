package com.hoffe86.speechmultikeyword.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ListeningColor = Color(0xFF22C55E)
val IdleColor = Color(0xFF475569)
val ErrorColor = Color(0xFFEF4444)

private val CarColors = darkColorScheme(
    primary = Color(0xFF38BDF8),
    secondary = ListeningColor,
    background = Color(0xFF0B1120),
    surface = Color(0xFF111827),
    surfaceVariant = Color(0xFF1F2937),
    error = ErrorColor,
)

@Composable
fun SpeechTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CarColors, content = content)
}
