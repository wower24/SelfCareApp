package com.wower.selfcareapp.presentation.journal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.common.ScreenViewState
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.use_cases.journal_entry.GetAllEntriesUseCase
import com.wower.selfcareapp.domain.use_cases.journal_entry.RemoveJournalEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val getAllEntriesUseCase: GetAllEntriesUseCase,
    private val removeJournalEntryUseCase: RemoveJournalEntryUseCase
): ViewModel() {
    var _state: MutableStateFlow<JournalState> = MutableStateFlow(JournalState())

    val state: StateFlow<JournalState> = _state.asStateFlow()

    init {
        getAllEntries()
    }

    private fun getAllEntries() {
        getAllEntriesUseCase.invoke()
            .onEach {
            _state.value = _state.value.copy(entries = ScreenViewState.Success(it))
        }
            .catch {
                _state.value = _state.value.copy(entries = ScreenViewState.Error(it.message.toString()))
            }
            .launchIn(viewModelScope)
    }

    fun deleteEntry(entryId: Int) = viewModelScope.launch {
        removeJournalEntryUseCase(entryId)
    }
}

data class JournalState(
    val entries: ScreenViewState<List<JournalEntry>> = ScreenViewState.Loading
)