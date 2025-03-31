package com.wower.selfcareapp.domain.use_cases.journal_entry

import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import javax.inject.Inject

class AddJournalEntryUseCase @Inject constructor(
    val journalEntryRepository: JournalEntryRepository
) {
    suspend operator fun invoke(journalEntry: JournalEntry) = journalEntryRepository.insert(journalEntry)
}