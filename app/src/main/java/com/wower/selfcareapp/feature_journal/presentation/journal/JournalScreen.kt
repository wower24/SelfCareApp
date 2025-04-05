package com.wower.selfcareapp.feature_journal.presentation.journal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wower.selfcareapp.feature_journal.presentation.journal.components.EntryItem
import kotlinx.coroutines.launch

@Composable
fun JournalScreen(
    navController: NavController,
    viewModel: JournalViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create a new Entry"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                text = "Your Journal",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn( modifier = Modifier.fillMaxSize() ) {
                items(state.entries) { entry ->
                    EntryItem(
                        entry = entry,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {

                            },
                        onDeleteClick = {
                            viewModel.onEvent(JournalEvent.DeleteEntry(entry))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Entry deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(JournalEvent.RestoreEntry)
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}