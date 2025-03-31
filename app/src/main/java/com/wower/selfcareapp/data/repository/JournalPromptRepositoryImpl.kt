package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.JournalPromptDao
import com.wower.selfcareapp.data.local.model.JournalPrompt
import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import javax.inject.Inject

class JournalPromptRepositoryImpl
@Inject constructor(private val journalPromptDao: JournalPromptDao): JournalPromptRepository {
    override suspend fun insert(journalPrompt: JournalPrompt) = journalPromptDao.insert(journalPrompt)
    override fun getRandomPrompt() = journalPromptDao.getRandomPrompt()
    override suspend fun markPromptAsUsed(promptId: Int) = journalPromptDao.markPromptAsUsed(promptId)
    override suspend fun resetPrompts() = journalPromptDao.resetPrompts()
}