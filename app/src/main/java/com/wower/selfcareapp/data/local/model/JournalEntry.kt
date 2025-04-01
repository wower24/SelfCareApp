package com.wower.selfcareapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "journal_entries")
data class JournalEntry (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var prompt: String = "",
    var content: String = "",
    var date: Date = Date()
)