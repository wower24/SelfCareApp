package com.wower.selfcareapp.util

sealed class Screen(val route: String) {
    object JournalScreen: Screen("journal_screen")
    object JournalEntryScreen: Screen("journal_entry_screen")
    object AffirmationScreen: Screen("affirmation_screen")
    object BoxBreathingScreen: Screen("box_breathing_screen")
}