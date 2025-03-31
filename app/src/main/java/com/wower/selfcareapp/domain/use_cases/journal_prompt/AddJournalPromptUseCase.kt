package com.wower.selfcareapp.domain.use_cases.journal_prompt

import com.wower.selfcareapp.data.local.model.JournalPrompt
import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import javax.inject.Inject

class AddJournalPromptUseCase @Inject constructor(
    private val journalPromptRepository: JournalPromptRepository
) {
    suspend operator fun invoke(journalPrompt: JournalPrompt) = journalPromptRepository.insert(journalPrompt)
}