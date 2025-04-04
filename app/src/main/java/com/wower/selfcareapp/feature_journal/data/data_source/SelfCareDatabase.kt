package com.wower.selfcareapp.feature_journal.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry

@Database(
    entities = [JournalEntry::class],
    version = 1,
    exportSchema = false
)
abstract class SelfCareDatabase: RoomDatabase() {
    abstract val journalEntryDao: JournalEntryDao
}