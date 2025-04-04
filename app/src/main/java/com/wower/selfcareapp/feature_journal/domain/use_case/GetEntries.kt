package com.wower.selfcareapp.feature_journal.domain.use_case

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntries(
    private val repository: JournalEntryRepository
) {
    operator fun invoke(): Flow<List<JournalEntry>> {
        return repository.getAllEntries()
    }
}