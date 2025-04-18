package com.wower.selfcareapp.feature_breathing.presentation.box_breathing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import com.wower.selfcareapp.ui.theme.SelfCareColor

@Composable
fun BoxBreathingScreen(
    viewModel: BoxBreathingViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize()
                .background(SelfCareColor.LightGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //TIMER
            Text(
                text = "${state.value.phase}",
                style = MaterialTheme.typography.titleMedium,
                color = SelfCareColor.LightPink,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "${state.value.timeRemainingInPhase}",
                style = MaterialTheme.typography.titleLarge,
                color = SelfCareColor.DarkPink
            )

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
}