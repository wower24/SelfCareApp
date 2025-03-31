package com.wower.selfcareapp.domain.repositories

import com.wower.selfcareapp.data.local.model.JournalPrompt
import kotlinx.coroutines.flow.Flow

interface JournalPromptRepository {
    suspend fun insert(journalPrompt: JournalPrompt)
    fun getRandomPrompt(): Flow<JournalPrompt>
    suspend fun markPromptAsUsed(promptId: Int)
    suspend fun resetPrompts()
}