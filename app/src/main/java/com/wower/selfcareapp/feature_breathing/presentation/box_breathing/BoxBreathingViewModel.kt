package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import androidx.lifecycle.ViewModel
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoxBreathingViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BreathingUIState())
    val uiState: StateFlow<BreathingUIState> = _uiState.asStateFlow()

    fun start(durationInMinutes: Int) {
        _uiState.value = _uiState.value.copy(
            cyclesRemaining = durationInMinutes * 4,
            isRunning = true
        )
    }

    fun updatePhase(phase: BreathingPhase) {
        _uiState.value = _uiState.value.copy(
            phase = phase
        )
    }

    fun decreaseCycles() {
        _uiState.value = _uiState.value.copy(
            cyclesRemaining = _uiState.value.cyclesRemaining - 1
        )
    }

    fun reset() {
        _uiState.value = BreathingUIState()
    }
}