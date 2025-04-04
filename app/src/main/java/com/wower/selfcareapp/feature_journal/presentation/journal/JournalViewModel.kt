package com.wower.selfcareapp.feature_journal.presentation.journal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.EntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(JournalState())
    val state = _state

    private var recentlyDeletedEntry: JournalEntry? = null

    init {
        getEntries()
    }

    fun onEvent(event: JournalEvent) {
        when(event) {
            is JournalEvent.DeleteEntry -> {
                viewModelScope.launch {
                    entryUseCases.deleteEntry(event.entry)
                    recentlyDeletedEntry = event.entry
                }
            }
            is JournalEvent.RestoreEntry -> {
                viewModelScope.launch {
                    entryUseCases.addEntry(recentlyDeletedEntry ?: return@launch)
                    recentlyDeletedEntry = null
                }
            }
        }
    }

    fun getEntries() {
        entryUseCases.getEntries().onEach { entries ->
            _state.value = state.value.copy(
                entries = entries
            )
        }
            .launchIn(viewModelScope)
    }
}