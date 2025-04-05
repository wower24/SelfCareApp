package com.wower.selfcareapp.feature_journal.domain.use_case

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository

class GetEntryById(
    private val repository: JournalEntryRepository
) {
    suspend operator fun invoke(id: Int): JournalEntry? {
        return repository.getEntryById(id)
    }
}