package com.wower.selfcareapp.feature_journal.domain.repository

import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

interface JournalPromptRepository {
    fun insertPrompt(prompt: JournalPrompt)
    suspend fun getRandomPrompt(): JournalPrompt?
    suspend fun markPromptAsUsed(promptId: Int)
    suspend fun resetPrompts()
}