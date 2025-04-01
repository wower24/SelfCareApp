package com.wower.selfcareapp.domain.repositories

import com.wower.selfcareapp.data.local.model.JournalEntry
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface JournalEntryRepository {
    suspend fun insert(journalEntry: JournalEntry)
    suspend fun delete(entryId: Int)
    fun getAllEntries(): Flow<List<JournalEntry>>
    fun getEntryById(entryId: Int): Flow<JournalEntry?>
    fun getEntriesByPrompt(prompt: String): Flow<List<JournalEntry>>
    fun getEntryByDate(date: Date): JournalEntry?
}