package com.wower.selfcareapp.feature_affirmation.presentation.affirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.util.Screen

@Composable
fun AffirmationScreen(
    navController: NavController,
    viewModel: AffirmationViewModel = hiltViewModel()
) {
    val affirmationState = viewModel.affirmation.value

    Box(
    modifier = Modifier
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            text = affirmationState.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)
        )

        Button(
            onClick = {
                navController.navigate(Screen.JournalScreen.route)
            },
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}