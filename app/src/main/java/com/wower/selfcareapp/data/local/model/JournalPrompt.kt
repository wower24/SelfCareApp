package com.wower.selfcareapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_prompts")
data class JournalPrompt(
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,
    var text: String = "",
    var isNotUsed: Boolean = true
)