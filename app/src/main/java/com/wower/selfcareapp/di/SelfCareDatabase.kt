package com.wower.selfcareapp.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wower.selfcareapp.feature_affirmation.data.data_source.AffirmationDao
import com.wower.selfcareapp.feature_affirmation.domain.model.Affirmation
import com.wower.selfcareapp.feature_journal.data.data_source.JournalEntryDao
import com.wower.selfcareapp.feature_journal.data.data_source.JournalPromptDao
import com.wower.selfcareapp.feature_journal.domain.model.JournalEntry
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

@Database(
    entities = [JournalEntry::class,
               JournalPrompt::class,
               Affirmation::class],
    version = 2,
    exportSchema = false
)
abstract class SelfCareDatabase: RoomDatabase() {
    abstract val journalEntryDao: JournalEntryDao
    abstract val journalPromptDao: JournalPromptDao
    abstract val affirmationDao: AffirmationDao

    companion object {
        const val DATABASE_NAME = "self_care_db"
    }
}