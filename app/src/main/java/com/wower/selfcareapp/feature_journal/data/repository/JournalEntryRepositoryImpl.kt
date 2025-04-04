package com.wower.selfcareapp.feature_journal.data.repository

import com.wower.selfcareapp.feature_journal.data.data_source.JournalEntryDao
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository
import kotlinx.coroutines.flow.Flow

class JournalEntryRepositoryImpl(
    private val dao: JournalEntryDao
): JournalEntryRepository {
    override fun getAllEntries(): Flow<List<JournalEntry>> {
        return dao.getAllEntries()
    }

    override suspend fun getEntryById(id: Int): JournalEntry? {
        return dao.getEntryById(id)
    }

    override suspend fun insertEntry(entry: JournalEntry) {
        dao.insertEntry(entry)
    }

    override suspend fun deleteEntry(entry: JournalEntry) {
        dao.deleteEntry(entry)
    }
}