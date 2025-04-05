package com.wower.selfcareapp.feature_journal.presentation.journal_entry

data class EntryTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)