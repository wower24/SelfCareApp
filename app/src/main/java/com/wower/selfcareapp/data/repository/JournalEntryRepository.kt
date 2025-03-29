package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.JournalEntryDao
import com.wower.selfcareapp.data.local.model.JournalEntry
import javax.inject.Inject

class JournalEntryRepository @Inject constructor(private val journalEntryDao: JournalEntryDao) {
    suspend fun insert(journalEntry: JournalEntry) = journalEntryDao.insert(journalEntry)
    suspend fun delete(entryId: Int) = journalEntryDao.delete(entryId)
    fun getAllEntries() = journalEntryDao.getAllEntries()
    fun getEntryById(entryId: Int) = journalEntryDao.getEntryById(entryId)
    fun getEntriesByPrompt(prompt: String) = journalEntryDao.getEntriesByPrompt(prompt)
}