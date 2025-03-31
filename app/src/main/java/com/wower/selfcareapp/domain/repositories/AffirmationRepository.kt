package com.wower.selfcareapp.domain.repositories

import com.wower.selfcareapp.data.local.model.Affirmation
import kotlinx.coroutines.flow.Flow

interface AffirmationRepository {
    suspend fun insert(affirmation: Affirmation)
    fun getRandomAffirmation(): Flow<Affirmation>
    suspend fun markAffirmationAsUsed(affirmationId: Int)
    suspend fun resetAffirmations()
}