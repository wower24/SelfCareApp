package com.wower.selfcareapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "journal_entries")
data class JournalEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prompt: String = "",
    val content: String = "",
    val date: LocalDate = LocalDate.now()
)