package com.wower.selfcareapp.presentation.journal_entry

import android.app.VoiceInteractor.Prompt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.use_cases.journal_entry.AddJournalEntryUseCase
import com.wower.selfcareapp.domain.use_cases.journal_entry.GetEntryByIdUseCase
import com.wower.selfcareapp.domain.use_cases.journal_prompt.GetRandomPromptUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.Date

class JournalEntryViewModel @AssistedInject constructor(
    private val addJournalEntryUseCase: AddJournalEntryUseCase,
    private val getEntryByIdUseCase: GetEntryByIdUseCase,
    private val getRandomPromptUseCase: GetRandomPromptUseCase,
    @Assisted private val entryId: Int
): ViewModel() {
    var state by mutableStateOf(JournalEntryState())
        private set
    val isFormNotBlank: Boolean
        get() = state.content.isEmpty()

    private val journalEntry: JournalEntry
        get() = state.run {
            JournalEntry(
                id = state.id,
                prompt = state.prompt,
                content = state.content,
                date = state.date
            )
        }

    init {
        initialize()
    }

    private fun loadRandomPrompt() = viewModelScope.launch{
        val prompt: String = getRandomPromptUseCase().collect { it.text }.toString()
        state = state.copy(prompt = prompt)
    }

    private fun initialize() {
        val isUpdatingEntry = entryId != -1
        state = state.copy(isUpdatingEntry = isUpdatingEntry)

        if(isUpdatingEntry) {
            getEntryById()
        } else {
            loadRandomPrompt()
        }
    }

    private fun getEntryById() = viewModelScope.launch {
        getEntryByIdUseCase(entryId).collect { entry ->
            state = state.copy(
                id = entry?.id ?: 0,
                prompt = entry?.prompt ?: "",
                content = entry?.content ?: "",
                date = Date()
            )
        }
    }

    fun onContentChange(content: String) {
        state = state.copy(content = content)
    }

    fun addEntry() = viewModelScope.launch {
        addJournalEntryUseCase(journalEntry = journalEntry)
    }
}

data class JournalEntryState(
    val id: Int = 0,
    val prompt: String = "",
    val content: String = "",
    val date: Date = Date(),
    val isUpdatingEntry: Boolean = false
)

class JournalEntryViewModelFactory(
    private val entryId: Int,
    private val assistedFactory: JournalEntryViewModelAssistedFactory
): ViewModelProvider.Factory {
    override fun<T: ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(entryId) as T
    }
}

@AssistedFactory
interface JournalEntryViewModelAssistedFactory {
    fun create(entryId: Int): JournalEntryViewModel
}