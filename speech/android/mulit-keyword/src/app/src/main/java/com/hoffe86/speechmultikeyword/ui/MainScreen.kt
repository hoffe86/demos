package com.hoffe86.speechmultikeyword.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoffe86.speechmultikeyword.kws.Detection
import com.hoffe86.speechmultikeyword.kws.EngineType
import com.hoffe86.speechmultikeyword.kws.Keyword
import com.hoffe86.speechmultikeyword.kws.ListeningMode
import com.hoffe86.speechmultikeyword.service.EngineStatus
import com.hoffe86.speechmultikeyword.service.LogEntry
import com.hoffe86.speechmultikeyword.service.UiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val TouchTarget = 76.dp

@Composable
fun MainScreen(
    state: UiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onSimulate: (Keyword) -> Unit,
    showDebug: Boolean,
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Row(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Column(modifier = Modifier.weight(1.2f).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                ModeCard(state, Modifier.weight(1f))
                ArmedKeywords(state)
                Controls(state, onStart, onStop, onSimulate, showDebug)
            }
            Column(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                MetricsPanel(state)
                DetectionLog(state.log, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ModeCard(state: UiState, modifier: Modifier = Modifier) {
    val listening = state.mode as? ListeningMode.Listening
    val background by animateColorAsState(if (listening != null) ListeningColor.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surface)
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = background),
        shape = RoundedCornerShape(28.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MicPulse(active = listening != null)
            Spacer(Modifier.height(24.dp))
            Text(
                text = if (listening != null) "LISTENING…" else "IDLE",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = if (listening != null) ListeningColor else MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(12.dp))
            val hint = when {
                state.status != EngineStatus.RUNNING && listening == null -> "Start keyword spotting to begin"
                listening != null -> {
                    val remaining = ((listening.untilMs - state.nowMs).coerceAtLeast(0) + 999) / 1000
                    "Say \"Hold on\" to end listening  ·  auto-off in ${remaining}s"
                }
                else -> "Say \"Hey Jarvis\""
            }
            Text(hint, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            state.lastDetection?.let { LastDetection(it) }
        }
    }
}

@Composable
private fun MicPulse(active: Boolean) {
    val transition = rememberInfiniteTransition(label = "pulse")
    val pulse by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "scale",
    )
    val color = if (active) ListeningColor else IdleColor
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(160.dp)) {
        if (active) Box(Modifier.size(120.dp).scale(pulse).background(color.copy(alpha = 0.3f), CircleShape))
        Box(Modifier.size(110.dp).background(color, CircleShape), contentAlignment = Alignment.Center) {
            Text("🎤", fontSize = 52.sp)
        }
    }
}

@Composable
private fun LastDetection(d: Detection) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = "Last: ${d.keyword?.phrase ?: "UNKNOWN"}  (text='${d.rawText}', ${d.reason}${if (d.simulated) "" else ", ${d.source}"})",
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
    )
}

@Composable
private fun ArmedKeywords(state: UiState) {
    val armed = state.status == EngineStatus.RUNNING
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("Armed in parallel:", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
        Keyword.entries.forEach { keyword ->
            val lastHit = state.lastDetection?.keyword == keyword
            val dot = if (armed) ListeningColor else IdleColor
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .border(2.dp, if (lastHit) MaterialTheme.colorScheme.primary else Color.Transparent, RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(28.dp))
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.size(14.dp).background(dot, CircleShape))
                Spacer(Modifier.size(10.dp))
                Text("\"${keyword.phrase}\"", fontSize = 22.sp, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
private fun Controls(
    state: UiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onSimulate: (Keyword) -> Unit,
    showDebug: Boolean,
) {
    val running = state.status == EngineStatus.RUNNING || state.status == EngineStatus.LOADING
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            if (running) {
                Button(
                    onClick = onStop,
                    modifier = Modifier.height(TouchTarget),
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorColor),
                ) { Text("Stop keyword spotting", fontSize = 22.sp) }
            } else {
                Button(onClick = onStart, modifier = Modifier.height(TouchTarget)) {
                    Text("Start keyword spotting", fontSize = 22.sp)
                }
            }
            Text(
                "${state.engineType} · ${state.status}",
                fontSize = 18.sp,
                color = if (state.status == EngineStatus.ERROR) ErrorColor else MaterialTheme.colorScheme.onSurface,
            )
        }
        state.error?.let { Text(it, color = ErrorColor, fontSize = 16.sp, maxLines = 4) }
        if (showDebug) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Keyword.entries.forEach { keyword ->
                    OutlinedButton(onClick = { onSimulate(keyword) }, modifier = Modifier.height(TouchTarget)) {
                        Text("Simulate \"${keyword.phrase}\"", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricsPanel(state: UiState) {
    val m = state.metrics
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Metrics", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            MetricRow("Engine", state.engineType.name)
            MetricRow("Model load", m.loadMs?.let { "$it ms" } ?: "–")
            MetricRow("Detections", m.detections.toString())
            MetricRow("Latency (est.)", m.lastLatencyEstimateMs?.let { "$it ms" } ?: "–")
            MetricRow("Arm → result", m.lastArmToResultMs?.let { "$it ms" } ?: "–")
            MetricRow("Memory (PSS)", m.pssKb?.let { "%.1f MB".format(it / 1024.0) } ?: "–")
            MetricRow("CPU (1 core)", m.cpuPercentOfOneCore?.let { "%.1f %%".format(it) } ?: "–")
        }
    }
}

@Composable
private fun MetricRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Text(value, fontSize = 18.sp, fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun DetectionLog(log: List<LogEntry>, modifier: Modifier = Modifier) {
    val format = SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT)
    Card(modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.padding(20.dp)) {
            Text("Event log", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(log, key = { it.id }) { entry ->
                    Text(
                        "${format.format(Date(entry.wallTimeMs))}  ${entry.message}",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        color = if (entry.isError) ErrorColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 1280, heightDp = 720)
@Composable
private fun MainScreenPreview() {
    SpeechTheme {
        MainScreen(
            state = UiState(
                engineType = EngineType.FROM_CONFIG,
                status = EngineStatus.RUNNING,
                mode = ListeningMode.Listening(sinceMs = 0, untilMs = 12_000),
                nowMs = 3_000,
                log = listOf(LogEntry(1, 0, "Hey Jarvis ← text='Hey Jarvis' reason=RecognizedKeyword → STARTED_LISTENING")),
            ),
            onStart = {}, onStop = {}, onSimulate = {}, showDebug = true,
        )
    }
}
