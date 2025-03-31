package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.JournalEntryDao
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import javax.inject.Inject

class JournalEntryRepositoryImpl
@Inject constructor(private val journalEntryDao: JournalEntryDao): JournalEntryRepository {
    override suspend fun insert(journalEntry: JournalEntry) = journalEntryDao.insert(journalEntry)
    override suspend fun delete(entryId: Int) = journalEntryDao.delete(entryId)
    override fun getAllEntries() = journalEntryDao.getAllEntries()
    override fun getEntryById(entryId: Int) = journalEntryDao.getEntryById(entryId)
    override fun getEntriesByPrompt(prompt: String) = journalEntryDao.getEntriesByPrompt(prompt)
}