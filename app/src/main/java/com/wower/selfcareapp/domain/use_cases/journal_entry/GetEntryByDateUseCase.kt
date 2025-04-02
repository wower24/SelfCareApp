package com.wower.selfcareapp.domain.use_cases.journal_entry

import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

class GetEntryByDateUseCase @Inject constructor(
    private val journalEntryRepository: JournalEntryRepository
) {
    operator fun invoke(date: LocalDate): Flow<JournalEntry?> = journalEntryRepository.getEntryByDate(date)
}