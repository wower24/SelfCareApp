package com.wower.selfcareapp.feature_journal.domain.repository

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import kotlinx.coroutines.flow.Flow

interface JournalEntryRepository {
    fun getAllEntries(): Flow<List<JournalEntry>>

    suspend fun getEntryById(id: Int): JournalEntry?

    suspend fun insertEntry(entry: JournalEntry)

    suspend fun deleteEntry(entry: JournalEntry)
}