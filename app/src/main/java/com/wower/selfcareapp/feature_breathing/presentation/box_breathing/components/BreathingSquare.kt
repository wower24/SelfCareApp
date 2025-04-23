package com.wower.selfcareapp.feature_breathing.presentation.box_breathing.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import com.wower.selfcareapp.ui.theme.SelfCareColor


//draw square

@Composable
fun BreathingSquare(
    phase: BreathingPhase
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(phase) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
        )
    }

    Canvas(modifier = Modifier.size(200.dp)) {
        val squareSize = size.minDimension
        val sideLength = squareSize

        val start = Offset.Zero
        val topRight = Offset(sideLength, 0f)
        val bottomRight = Offset(sideLength, sideLength)
        val bottomLeft = Offset(0f, sideLength)

        if(phase == BreathingPhase.Inhale) {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = Offset(progress.value * sideLength, 0f),
                strokeWidth = 10f
            )
        } else if(phase == BreathingPhase.HoldAfterInhale) {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = topRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.LightPink,
                start = topRight,
                end = Offset(sideLength, progress.value * sideLength),
                strokeWidth = 10f
            )
        } else if(phase == BreathingPhase.Exhale) {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = topRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.LightPink,
                start = topRight,
                end = bottomRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.DarkPink,
                start = bottomRight,
                end = Offset((sideLength - progress.value * sideLength), sideLength),
                strokeWidth = 10f
            )
        } else {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = topRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.LightPink,
                start = topRight,
                end = bottomRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.DarkPink,
                start = bottomRight,
                end = bottomLeft,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.LightPink,
                start = bottomLeft,
                end = Offset(0f, sideLength - (progress.value * sideLength)),
                strokeWidth = 10f
            )
        }
    }
}