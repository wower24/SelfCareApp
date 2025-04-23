package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BoxBreathingViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BreathingUIState())
    val uiState: StateFlow<BreathingUIState> = _uiState.asStateFlow()

    private var breathingJob: Job? = null

    fun start(durationInMinutes: Int) {
        val totalCycles = durationInMinutes * 60 / 16
        breathingJob?.cancel()

        breathingJob = viewModelScope.launch {
            repeat(totalCycles) { cycle ->
                BreathingPhase.entries.forEach { phase ->
                    val phaseStart = System.currentTimeMillis()

                    for (second in 4 downTo 1) {
                        val now = System.currentTimeMillis()
                        val elapsed = now - phaseStart
                        val targetDelay = (second * 1000L) - elapsed

                        _uiState.value = _uiState.value.copy(
                            phase = phase,
                            timeRemainingInPhase = second,
                            cyclesRemaining = totalCycles - cycle,
                            isRunning = true
                        )

                        if (targetDelay > 0) delay(targetDelay)
                    }
                }
            }

            reset()
        }
    }

    fun reset() {
        breathingJob?.cancel()
        _uiState.value = BreathingUIState()
    }
}