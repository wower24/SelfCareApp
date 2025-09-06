package com.wower.selfcareapp.feature_grounding.preesentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.feature_affirmation.presentation.affirmation.AffirmationViewModel
import com.wower.selfcareapp.feature_grounding.data.GroundingExercise
import com.wower.selfcareapp.feature_grounding.data.groundingExercises
import com.wower.selfcareapp.ui.theme.SelfCareColor
import com.wower.selfcareapp.util.Screen

@Composable
fun GroundingExerciseScreen(
    navController: NavController,
    viewModel: GroundingExerciseViewModel = hiltViewModel()
) {
    val exerciseState = viewModel.exercise.value

        Box(
            modifier = Modifier.background(SelfCareColor.LightBlue)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ExerciseDetail(exercise = exerciseState)

            Button(
                onClick = {
                    navController.navigate(Screen.JournalScreen.route);
                },
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