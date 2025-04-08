package com.wower.selfcareapp.feature_journal.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_prompts")
data class JournalPrompt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prompt: String,
    val isNotUsed: Boolean = true
)
