package com.wower.selfcareapp.feature_grounding.preesentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.feature_grounding.data.groundingExercises
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import com.wower.selfcareapp.ui.theme.SelfCareColor

@Composable
fun GroundingExerciseScreen() {
    Box(
        modifier = Modifier.background(SelfCareColor.LightBlue)
            .fillMaxSize()
            .padding(16.dp)
    ) {
       ExerciseDetail(exercise = groundingExercises.random())

        Button(
            onClick = { /* GO BACK HOME*/ },
            colors = ButtonDefaults.buttonColors(containerColor = SelfCareColor.DarkBlue),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Done",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SelfCareColor.LightBlue
            )
        }
    }
}

@Preview
@Composable
fun GroundingExerciseScreenPreview() {
    SelfCareAppTheme {
        GroundingExerciseScreen()
    }
}