package com.wower.selfcareapp.domain.use_cases.journal_entry

import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import javax.inject.Inject

class RemoveJournalEntryUseCase @Inject constructor(
    private val journalEntryRepository: JournalEntryRepository
) {
    suspend operator fun invoke(entryId: Int) = journalEntryRepository.delete(entryId)
}