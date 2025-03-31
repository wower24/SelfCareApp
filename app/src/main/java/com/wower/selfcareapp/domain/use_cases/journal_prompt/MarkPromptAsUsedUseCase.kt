package com.wower.selfcareapp.domain.use_cases.journal_prompt

import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import javax.inject.Inject

class MarkPromptAsUsedUseCase @Inject constructor(
    private val journalPromptRepository: JournalPromptRepository
) {
    suspend operator fun invoke(promptId: Int) = journalPromptRepository.markPromptAsUsed(promptId)
}