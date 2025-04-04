package com.wower.selfcareapp.presentation.journal_entry

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun JournalEntryScreen(
    modifier: Modifier = Modifier,
    entryId: Int,
    assistedFactory: JournalEntryViewModelAssistedFactory,
    navigateUp: () -> Unit
) {
    var context: Context = LocalContext.current

    val viewModel = viewModel(
        modelClass = JournalEntryViewModel::class.java,
        factory = JournalEntryViewModelFactory(
            entryId = entryId,
            assistedFactory = assistedFactory
        )
    )

    val state = viewModel.state

    JournalEntryScreen(
        modifier = modifier,
        prompt = state.prompt,
        content = state.content,
        isFormNotBlank = viewModel.isFormNotBlank,
        onContentChange = viewModel::onContentChange,
        onButtonClick = {
            if (!state.hasEntryForToday) {
                viewModel.addEntry()
            } else {
                Toast.makeText(
                    context,
                    "You have already added an entry for today.",
                    Toast.LENGTH_LONG
                ).show()
            }
        },
        onNavigate = navigateUp
    )

}

@Composable
private fun JournalEntryScreen(
    modifier: Modifier,
    prompt: String,
    content: String,
    isFormNotBlank: Boolean,
    onContentChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TopSection(
            modifier = modifier,
            prompt = prompt,
            onNavigate = onNavigate
        )

        Spacer(modifier = Modifier.Companion.size(12.dp))

        AnimatedVisibility(isFormNotBlank) {
            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add Entry"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.Companion.size(12.dp))

        EntryTextField(
            modifier = modifier.weight(1f),
            value = content,
            onValueChange = onContentChange
        )
    }
}

@Composable
private fun TopSection(
    modifier: Modifier,
    prompt: String,
    onNavigate: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onNavigate) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go Back"
            )
        }

        Text(
            modifier = modifier.weight(1f),
            text = prompt,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun EntryTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "Enter your thoughts on that here...",
                modifier = modifier.fillMaxWidth()
                )
        }
    )
}
