package com.wower.selfcareapp.feature_breathing.presentation.box_breathing.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun BreathingSquare(progress :Float) {

    Canvas(modifier = Modifier.size(200.dp)) {
        val squareSize = size.minDimension
        val sideLength = squareSize

        val start = Offset.Zero
        val topRight = Offset(sideLength, 0f)
        val bottomRight = Offset(sideLength, sideLength)
        val bottomLeft = Offset(0f, sideLength)

        if(progress <= 1f) {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = Offset(progress * sideLength, 0f),
                strokeWidth = 10f
            )
        } else if(progress <= 2f) {
            drawLine(
                color = SelfCareColor.DarkPink,
                start = start,
                end = topRight,
                strokeWidth = 10f
            )
            drawLine(
                color = SelfCareColor.LightPink,
                start = topRight,
                end = Offset(sideLength, (progress - 1) * sideLength),
                strokeWidth = 10f
            )
        } else if(progress <= 3f) {
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
                end = Offset((sideLength - (progress - 2) * sideLength), sideLength),
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
                end = Offset(0f, sideLength - ((progress - 3) * sideLength)),
                strokeWidth = 10f
            )
        }
    }
}