package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.feature_breathing.presentation.box_breathing.components.BreathingSquare
import com.wower.selfcareapp.ui.theme.SelfCareColor
import com.wower.selfcareapp.util.Screen

@Composable
fun BoxBreathingScreen(
    navController: NavController,
    viewModel: BoxBreathingViewModel = hiltViewModel()
) {
    val state: State<BreathingUIState> = viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(220.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (state.value.isRunning) {
                    BreathingSquare(viewModel)

                    //TIMER
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = state.value.phase.name
                                .replace("AfterInhale", "")
                                .replace("AfterExhale", ""),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                } else {
                    Text(
                        text = "Choose duration",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
            //BUTTONS
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel.start(1)
                    }
                ) {
                    Text(
                        text = "1min"
                    )
                }

                Button(
                    onClick = {
                        viewModel.start(2)
                    }
                ) {
                    Text(
                        text = "2min"
                    )
                }

                Button(
                    onClick = {
                        viewModel.start(4)
                    }
                ) {
                    Text(
                        text = "4min"
                    )
                }
            }
        }

        Button(
            onClick = { navController.navigate(Screen.JournalScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onTertiary),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Done",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}