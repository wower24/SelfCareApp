package com.wower.selfcareapp.feature_breathing.presentation.box_breathing.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.feature_breathing.domain.model.BreathingPhase
import com.wower.selfcareapp.ui.theme.SelfCareColor
import kotlin.math.min

@Composable
fun BreathingSquare(
    phase: BreathingPhase,
    progress: Float
) {
    val transition = updateTransition(targetState = phase, label = "squareTransition")

    val pathProgress = transition.animateFloat(
        label = "pathProgress",
        transitionSpec = {
            tween(durationMillis = 4000, easing = LinearEasing)
        }
    ) { currentPhase ->
        when (currentPhase) {
            BreathingPhase.Inhale -> 0f
            BreathingPhase.HoldAfterInhale -> 0.25f
            BreathingPhase.Exhale -> 0.5f
            BreathingPhase.HoldAfterExhale -> 0.75f
        }
    }
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val size = min(size.width, size.height)
            val squareSize = Size(size, size)

            val topLeft = Offset(
                (this.size.width - size) / 2,
                (this.size.height - size) / 2
            )
            val topRight = topLeft.copy(x = topLeft.x + size)
            val bottomRight = topRight.copy(y = topRight.y + size)
            val bottomLeft = topLeft.copy(y = topLeft.y + size)

            val currentProgress = pathProgress.value + (progress / 4)

            drawSquareSides(
                progress = currentProgress,
                topLeft = topLeft,
                topRight = topRight,
                bottomRight = bottomRight,
                bottomLeft = bottomLeft
            )
        }
}

private fun DrawScope.drawSquareSides(
    progress: Float,
    topLeft: Offset,
    topRight: Offset,
    bottomRight: Offset,
    bottomLeft: Offset
) {
    val pathPortion = progress * 4

    when {
        pathPortion <= 1 -> {
            //Top Line
        }
        pathPortion  <= 2 -> {
            //Right Line
        }
        pathPortion <= 3 -> {
            //Bottom Line
        }
        pathPortion <= 4 -> {
            //EndLine
        }
    }
}