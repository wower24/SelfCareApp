package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BoxBreathingViewModel @Inject constructor(): ViewModel() {
    private var exerciseDuration = 60
    private var cycleDuration = 16

    var breathingPhase by mutableStateOf(BreathingPhase.Inhale)
        private set
    var timeRemainingInPhase by mutableStateOf(4)
        private set
    var totalTimeRemaining by mutableStateOf(exerciseDuration)
        private set
    var isRunning by mutableStateOf(false)
        private set

    private var timerJob :Job? = null

    fun start(durationMinutes: Int) {
        exerciseDuration = durationMinutes * 60
        totalTimeRemaining = exerciseDuration
        isRunning = true
        startTimer()
    }

    fun pause() {
        isRunning = false
        timerJob?.cancel()
    }

    fun resume() {
        isRunning = true
        startTimer()
    }

    fun stop() {
        isRunning = false
        timerJob?.cancel()
        resetState()
    }

    private fun startTimer() {
        if (!isRunning) return
        timerJob = viewModelScope.launch {
            while (totalTimeRemaining > 0 && isRunning) {
                delay(1000) // Wait for 1 second
                timeRemainingInPhase--
                if (timeRemainingInPhase == 0) {
                    advanceToNextPhase()
                }
                if (breathingPhase == BreathingPhase.Inhale){
                    totalTimeRemaining--
                }
            }
            isRunning = false
            if (totalTimeRemaining == 0) {
                resetState()
            }
        }
    }

    private fun advanceToNextPhase() {
        breathingPhase = when (breathingPhase) {
            BreathingPhase.Inhale -> BreathingPhase.HoldAfterInhale
            BreathingPhase.HoldAfterInhale -> BreathingPhase.Exhale
            BreathingPhase.Exhale -> BreathingPhase.HoldAfterExhale
            BreathingPhase.HoldAfterExhale -> BreathingPhase.Inhale
        }
        timeRemainingInPhase = 4
    }

    private fun resetState() {
        breathingPhase = BreathingPhase.Inhale
        timeRemainingInPhase = 4
        totalTimeRemaining = exerciseDuration
    }
}