package com.wower.selfcareapp.feature_affirmation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "affirmations")
data class Affirmations(
    val affirmation: String,
    val isNotUsed: Boolean = true,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)