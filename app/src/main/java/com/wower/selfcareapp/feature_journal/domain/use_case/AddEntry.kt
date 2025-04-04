package com.wower.selfcareapp.feature_journal.domain.use_case

import com.wower.selfcareapp.feature_journal.domain.model.InvalidEntryException
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository

class AddEntry(
    private val repository: JournalEntryRepository
) {

    @Throws(InvalidEntryException::class)
    suspend operator fun invoke(entry: JournalEntry) {
        if(entry.prompt.isBlank()) {
            throw InvalidEntryException("The prompt of the entry can't be empty.")
        }
        if(entry.content.isBlank()) {
            throw InvalidEntryException("The content of the entry can't be empty.")
        }
        repository.insertEntry(entry)
    }
}