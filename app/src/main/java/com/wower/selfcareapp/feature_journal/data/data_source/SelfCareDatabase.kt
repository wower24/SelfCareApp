package com.wower.selfcareapp.feature_journal.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

@Database(
    entities = [JournalEntry::class,
               JournalPrompt::class],
    version = 2,
    exportSchema = false
)
abstract class SelfCareDatabase: RoomDatabase() {
    abstract val journalEntryDao: JournalEntryDao
    abstract val journalPromptDao: JournalPromptDao

    companion object {
        const val DATABASE_NAME = "self_care_db"
    }
}