package com.wower.selfcareapp.feature_breathing.presentation.box_breathing.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import com.wower.selfcareapp.feature_breathing.presentation.box_breathing.BoxBreathingViewModel
import com.wower.selfcareapp.ui.theme.SelfCareColor
import kotlinx.coroutines.delay


//draw square

@Composable
fun BreathingSquare(
    viewModel: BoxBreathingViewModel,
) {

    val context = LocalContext.current
    val progress = remember { Animatable(0f) }
    val lineColor = MaterialTheme.colorScheme.onTertiary


    LaunchedEffect(Unit) {
        while (true) {
            progress.snapTo(0f)
            progress.animateTo(
                targetValue = 4f,
                animationSpec = tween(durationMillis = 16000, easing = LinearEasing)
            )
        }
    }

    val currentPhase = when {
        progress.value <= 1f -> BreathingPhase.Inhale
        progress.value <= 2f -> BreathingPhase.HoldAfterInhale
        progress.value <= 3f -> BreathingPhase.Exhale
        else -> BreathingPhase.HoldAfterExhale
    }

    LaunchedEffect(currentPhase) {
        viewModel.updatePhase(currentPhase)

        if(viewModel.uiState.value.phase == BreathingPhase.Inhale) {
            viewModel.decreaseCycles()
            if(viewModel.uiState.value.cyclesRemaining < 0) {
                viewModel.reset()
            }
        }

        when (currentPhase) {
            BreathingPhase.Inhale, BreathingPhase.Exhale -> {
                repeat(8) {
                    viewModel.vibrate(context, 100L)
                    delay(400L)
                }
            }
            BreathingPhase.HoldAfterInhale, BreathingPhase.HoldAfterExhale -> {
                repeat(4) {
                    viewModel.vibrate(context, 100L)
                    delay(900L)
                }
            }
        }
    }

    Canvas(modifier = Modifier.size(200.dp)) {
        val squareSize = size.minDimension
        val sideLength = squareSize

        drawRect(
            color = lineColor,
            topLeft = Offset.Zero,
            alpha = 0.5f,
            style = Stroke(10f),
            size = size
        )

        val currentPosition = when {
            progress.value <= 1f -> Offset(progress.value * sideLength, 0f)
            progress.value <= 2f -> Offset(sideLength, (progress.value - 1f) * sideLength)
            progress.value <= 3f -> Offset(sideLength - (progress.value - 2f) * sideLength, sideLength)
            else -> Offset(0f, sideLength - (progress.value - 3f) * sideLength)
        }

        drawCircle(
            color = SelfCareColor.DarkPink,
            radius = 20f,
            center = currentPosition
        )
    }
}