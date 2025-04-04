package com.wower.selfcareapp.feature_journal.presentation.journal

import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry

data class JournalState(
    val entries: List<JournalEntry> = emptyList()
)
