package com.hoffe86.speechmultikeyword.service

import com.hoffe86.speechmultikeyword.kws.Detection
import com.hoffe86.speechmultikeyword.kws.EngineType
import com.hoffe86.speechmultikeyword.kws.ListeningMode

enum class EngineStatus { STOPPED, LOADING, RUNNING, ERROR }

data class LogEntry(val id: Long, val wallTimeMs: Long, val message: String, val isError: Boolean = false)

data class EngineMetrics(
    val loadMs: Long? = null,
    val pssKb: Long? = null,
    val cpuPercentOfOneCore: Double? = null,
    val detections: Int = 0,
    val lastLatencyEstimateMs: Long? = null,
    val lastArmToResultMs: Long? = null,
)

data class UiState(
    val engineType: EngineType,
    val status: EngineStatus = EngineStatus.STOPPED,
    val error: String? = null,
    val mode: ListeningMode = ListeningMode.Idle,
    val nowMs: Long = 0,
    val lastDetection: Detection? = null,
    val log: List<LogEntry> = emptyList(),
    val metrics: EngineMetrics = EngineMetrics(),
)
