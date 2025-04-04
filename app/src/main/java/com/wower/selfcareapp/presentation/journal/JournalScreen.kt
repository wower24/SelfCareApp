package com.wower.selfcareapp.presentation.journal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.common.ScreenViewState

@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    state: JournalState,
    onDelete: (Int) -> Unit,
    onEntryClick: (Int) -> Unit
) {
    when(state.entries) {
        is ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }
        is ScreenViewState.Success -> {
            val entries = state.entries.data
            JournalDetail(
                entries = entries,
                modifier = modifier,
                onDelete = onDelete,
                onEntryClick = onEntryClick
            )
        }
        is ScreenViewState.Error -> {
            Text(
                text = state.entries.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun JournalDetail(
    entries: List<JournalEntry>,
    modifier: Modifier,
    onDelete:(Int) -> Unit,
    onEntryClick:(Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        itemsIndexed(entries) { index, entry ->
            EntryCard(
                index = index,
                entry = entry,
                onDelete = onDelete,
                onEntryClick = onEntryClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryCard(
    index: Int,
    entry: JournalEntry,
    onDelete: (Int) -> Unit,
    onEntryClick: (Int) -> Unit
) {
    val isIndexEven = index % 2 == 0

    val shape = when {
        isIndexEven -> {
            RoundedCornerShape(
                topStart = 50f,
                bottomEnd = 50f
            )
        } else -> {
            RoundedCornerShape(
                topEnd = 50f,
                bottomStart = 50f
            )
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp),
        shape = shape,
        onClick = { onEntryClick(entry.id) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.prompt,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(onClick = {onDelete(entry.id)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }

            Spacer(Modifier.size(4.dp))

            Text(
                text = entry.content,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}