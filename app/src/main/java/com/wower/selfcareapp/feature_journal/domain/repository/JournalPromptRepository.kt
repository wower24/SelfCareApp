package com.wower.selfcareapp.feature_journal.domain.repository

import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

interface JournalPromptRepository {
    suspend fun insertPrompt(prompt: JournalPrompt)
    suspend fun initializePrompts(prompts: List<String>)
    suspend fun insertPrompts(prompts: List<JournalPrompt>)
    suspend fun getRandomPrompt(): JournalPrompt?
    suspend fun markPromptAsUsed(promptId: Int)
    suspend fun resetPrompts()
}