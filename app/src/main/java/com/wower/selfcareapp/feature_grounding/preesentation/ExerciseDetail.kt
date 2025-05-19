package com.wower.selfcareapp.feature_grounding.preesentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.feature_grounding.data.GroundingExercise
import com.wower.selfcareapp.feature_grounding.data.groundingExercises
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import com.wower.selfcareapp.ui.theme.SelfCareColor

@Composable
fun ExerciseDetail(exercise: GroundingExercise) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = exercise.title,
            style = MaterialTheme.typography.titleLarge,
            color = SelfCareColor.DarkBlue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        Image(
            painter = painterResource(id = exercise.imageResId),
            contentDescription = exercise.title,
            modifier = Modifier.size(200.dp)
                .padding(50.dp)
        )

        exercise.instructions.forEach { instruction ->
            Text(
                text = instruction,
                style = MaterialTheme.typography.titleMedium,
                color = SelfCareColor.DarkBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ExerciseDetailPreview() {
    SelfCareAppTheme {
        ExerciseDetail(exercise = groundingExercises[0])
    }
}