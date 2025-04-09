package com.wower.selfcareapp.feature_affirmation.domain.repository

import com.wower.selfcareapp.feature_affirmation.domain.model.Affirmation
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

interface AffirmationRepository {
    suspend fun insertAffirmation(affirmation: Affirmation)
    suspend fun getRandomAffirmation(): Affirmation?
    suspend fun markAffirmationAsUsed(affirmationId: Int)
    suspend fun resetAffirmations()
}