package com.wower.selfcareapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wower.selfcareapp.data.local.converters.DateConverter
import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.data.local.model.JournalPrompt

@TypeConverters(value = [DateConverter::class])

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