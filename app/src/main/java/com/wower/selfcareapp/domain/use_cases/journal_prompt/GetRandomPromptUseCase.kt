package com.wower.selfcareapp.domain.use_cases.journal_prompt

import com.wower.selfcareapp.data.local.model.JournalPrompt
import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomPromptUseCase @Inject constructor(
    private val journalPromptRepository: JournalPromptRepository
) {
    operator fun invoke(): Flow<JournalPrompt> = journalPromptRepository.getRandomPrompt()
}