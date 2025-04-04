package com.wower.selfcareapp.feature_journal.presentation.journal

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry

sealed class JournalEvent {
    data class DeleteEntry(val entry: JournalEntry): JournalEvent()
    object RestoreEntry: JournalEvent()
}