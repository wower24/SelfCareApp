package com.wower.selfcareapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "affirmations")
data class Affirmation(
    @PrimaryKey(autoGenerate = true)
    val id :Int = -1,
    val text: String = "",
    val isNotUsed: Boolean = true
)