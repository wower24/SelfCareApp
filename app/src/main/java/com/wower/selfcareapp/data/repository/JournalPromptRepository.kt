package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.JournalPromptDao
import com.wower.selfcareapp.data.local.model.JournalPrompt
import javax.inject.Inject

class JournalPromptRepository @Inject constructor(private val journalPromptDao: JournalPromptDao) {
    suspend fun insert(journalPrompt: JournalPrompt) = journalPromptDao.insert(journalPrompt)
    fun getRandomPrompt() = journalPromptDao.getRandomPrompt()
    suspend fun markPromptAsUsed(promptId: Int) = journalPromptDao.markPromptAsUsed(promptId)
    suspend fun resetPrompts() = journalPromptDao.resetPrompts()
}