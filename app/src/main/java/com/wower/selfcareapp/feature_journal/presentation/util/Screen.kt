package com.wower.selfcareapp.feature_journal.presentation.util

sealed class Screen(val route: String) {
    object JournalScreen: Screen("journal_screen")
    object JournalEntryScreen: Screen("journal_entry_screen")
}