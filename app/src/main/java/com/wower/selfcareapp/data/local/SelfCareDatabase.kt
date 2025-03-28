package com.wower.selfcareapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.data.local.model.JournalPrompt

@Database(
    entities = [JournalEntry::class, JournalPrompt::class, Affirmation::class],
    version = 1,
    exportSchema = false
)
abstract class SelfCareDatabase : RoomDatabase() {
    abstract val affirmationDao: AffirmationDao
    abstract val journalEntryDao: JournalEntryDao
    abstract val journalPromptDao: JournalPromptDao
}