package com.wower.selfcareapp.feature_journal.data.repository

import com.wower.selfcareapp.feature_journal.data.data_source.JournalPromptDao
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt
import com.wower.selfcareapp.feature_journal.domain.repository.JournalPromptRepository

class JournalPromptRepositoryImpl(
    private val dao: JournalPromptDao
) : JournalPromptRepository {

    override suspend fun insertPrompt(prompt: JournalPrompt) {
        dao.insertPrompt(prompt)
    }

    override suspend fun getRandomPrompt(): JournalPrompt? {
        return dao.getRandomPrompt()
    }

    override suspend fun markPromptAsUsed(promptId: Int) {
        dao.markPromptAsUsed(promptId)
    }

    override suspend fun resetPrompts() {
        dao.resetPrompts()
    }
}