package com.wower.selfcareapp.feature_journal.domain.use_case

import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt
import com.wower.selfcareapp.feature_journal.domain.repository.JournalPromptRepository

class GetRandomPrompt(
    private val repository: JournalPromptRepository
) {
    operator suspend fun invoke() : JournalPrompt? {
        var prompt = repository.getRandomPrompt()
        if (prompt == null) {
            repository.resetPrompts()
            prompt = repository.getRandomPrompt()
        }
        repository.markPromptAsUsed(prompt?.id ?: 0)
        return prompt
    }
}