package com.wower.selfcareapp.feature_journal.presentation.journal_entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.feature_journal.presentation.journal_entry.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun JournalEntryScreen(
    navController: NavController,
    viewModel: JournalEntryViewModel = hiltViewModel()
) {
    val promptState = viewModel.entryPrompt.value

    val contentState = viewModel.entryContent.value

    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is JournalEntryViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
                is JournalEntryViewModel.UiEvent.SaveEntry -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(JournalEntryEvent.SaveEntry)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save entry"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                text = promptState,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(JournalEntryEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(JournalEntryEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}