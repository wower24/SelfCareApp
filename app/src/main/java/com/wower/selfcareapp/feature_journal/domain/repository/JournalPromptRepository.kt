package com.wower.selfcareapp.feature_journal.domain.repository

interface JournalPromptRepository {
    suspend fun getRandomPrompt(): String?
    suspend fun markPromptAsUsed(promptId: Int)
    suspend fun resetPrompts()
}