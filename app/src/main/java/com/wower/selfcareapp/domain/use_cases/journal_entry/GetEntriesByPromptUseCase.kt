package com.wower.selfcareapp.domain.use_cases.journal_entry

import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntriesByPromptUseCase @Inject constructor(
    val journalEntryRepository: JournalEntryRepository
) {
    operator fun invoke(prompt: String): Flow<List<JournalEntry>> = journalEntryRepository.getEntriesByPrompt(prompt)
}