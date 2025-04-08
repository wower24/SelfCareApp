package com.wower.selfcareapp.feature_journal.data.repository

import com.wower.selfcareapp.feature_journal.domain.repository.JournalPromptRepository

class JournalPromptRepositoryImpl(
    private var prompts: MutableList<Pair<String, Int>>
) : JournalPromptRepository {
    override suspend fun getRandomPrompt(): String? {
        return prompts.randomOrNull()?.first
    }

    override suspend fun markPromptAsUsed(promptId: Int) {
        prompts[promptId] = prompts[promptId].copy(second = -1)
    }

    override suspend fun resetPrompts() {
        prompts.forEachIndexed { index, pair ->
            prompts[index] = pair.copy(second = index)
        }
    }
}