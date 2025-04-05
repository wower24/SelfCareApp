package com.wower.selfcareapp.feature_journal.presentation.journal_entry

import androidx.compose.ui.focus.FocusState

sealed class JournalEntryEvent {
    data class EnteredContent(val value: String) : JournalEntryEvent()
    data class ChangeContentFocus(val focusState: FocusState) : JournalEntryEvent()
    object SaveEntry : JournalEntryEvent()
}