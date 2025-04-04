package com.wower.selfcareapp.feature_journal.domain.use_case

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository

class DeleteEntry(
    private val repository: JournalEntryRepository
) {
    suspend operator fun invoke(entry: JournalEntry) {
        repository.deleteEntry(entry)
    }
}