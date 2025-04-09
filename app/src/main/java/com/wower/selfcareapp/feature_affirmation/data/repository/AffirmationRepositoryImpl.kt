package com.wower.selfcareapp.feature_affirmation.data.repository

import com.wower.selfcareapp.feature_affirmation.data.data_source.AffirmationDao
import com.wower.selfcareapp.feature_affirmation.domain.model.Affirmation
import com.wower.selfcareapp.feature_affirmation.domain.repository.AffirmationRepository

class AffirmationRepositoryImpl(
    private val dao: AffirmationDao
): AffirmationRepository {
    override suspend fun insertAffirmation(affirmation: Affirmation) {
        dao.insertAffirmation(affirmation)
    }

    override suspend fun getRandomAffirmation(): Affirmation? {
        return dao.getRandomAffirmation()
    }

    override suspend fun markAffirmationAsUsed(affirmationId: Int) {
        dao.markAffirmationAsUsed(affirmationId)
    }

    override suspend fun resetAffirmations() {
        dao.resetAffirmations()
    }
}