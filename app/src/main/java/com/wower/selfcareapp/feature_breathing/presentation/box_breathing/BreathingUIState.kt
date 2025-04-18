package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase

data class BreathingUIState(
    val phase: BreathingPhase = BreathingPhase.Inhale,
    val timeRemainingInPhase: Int = 4,
    val cyclesRemaining: Int = 4,
    val isRunning: Boolean = false
)
