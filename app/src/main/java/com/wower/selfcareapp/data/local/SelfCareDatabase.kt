package com.wower.selfcareapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wower.selfcareapp.data.local.converters.DateConverter
import com.wower.selfcareapp.data.local.model.JournalEntry

@TypeConverters(value = [DateConverter::class])

@Database(
    entities = [JournalEntry::class],
    version = 1,
    exportSchema = false
)
abstract class SelfCareDatabase : RoomDatabase() {
    abstract val journalEntryDao: JournalEntryDao
   }