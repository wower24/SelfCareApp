package com.wower.selfcareapp.feature_journal.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")
data class JournalEntry (
    val prompt: String,
    val content: String,
    val date: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)