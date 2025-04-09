package com.wower.selfcareapp.feature_journal.presentation.journal_entry

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_journal.domain.model.InvalidEntryException
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.EntryUseCases
import com.wower.selfcareapp.feature_journal.domain.use_case.GetRandomPrompt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalEntryViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases,
    private val getRandomPrompt: GetRandomPrompt,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _entryPrompt = mutableStateOf("Prompt should go here")
    val entryPrompt = _entryPrompt

    private val _entryContent = mutableStateOf(
        EntryTextFieldState(
            hint = "Enter your thoughts on that here..."
        )
    )
    val entryContent = _entryContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentEntryId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    entryUseCases.getEntryById(noteId)?.also { entry ->
                        currentEntryId = entry.id
                        _entryPrompt.value = entry.prompt
                        _entryContent.value = entryContent.value.copy(
                            text = entry.content,
                            isHintVisible = false
                        )
                    }
                }
            } else {
                viewModelScope.launch {
                    _entryPrompt.value = getRandomPrompt()?.prompt ?: "How was your day?"
                }
            }
        }
    }

    fun onEvent(event: JournalEntryEvent) {
        when(event) {
            is JournalEntryEvent.EnteredContent -> {
                _entryContent.value = entryContent.value.copy(
                    text = event.value
                )
            }
            is JournalEntryEvent.ChangeContentFocus -> {
                _entryContent.value = entryContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                        entryContent.value.text.isBlank()
                )
            }
            is JournalEntryEvent.SaveEntry -> {
                viewModelScope.launch {
                    try{
                        entryUseCases.addEntry(
                            JournalEntry(
                                prompt = entryPrompt.value.toString(),
                                content = entryContent.value.text,
                                date = System.currentTimeMillis(),
                                id = currentEntryId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveEntry)
                    } catch(e: InvalidEntryException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save entry"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveEntry: UiEvent()
    }
}