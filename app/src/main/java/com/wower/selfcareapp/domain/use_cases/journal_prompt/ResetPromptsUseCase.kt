package com.wower.selfcareapp.domain.use_cases.journal_prompt

import com.wower.selfcareapp.data.local.model.JournalPrompt
import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import javax.inject.Inject

class ResetPromptsUseCase @Inject constructor(
    private val journalPromptRepository: JournalPromptRepository
) {
    suspend operator fun invoke() = journalPromptRepository.resetPrompts()
}