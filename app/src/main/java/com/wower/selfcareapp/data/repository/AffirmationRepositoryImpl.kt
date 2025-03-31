package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.AffirmationDao
import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import javax.inject.Inject

class AffirmationRepositoryImpl @Inject constructor(private val affirmationDao: AffirmationDao): AffirmationRepository {
    override suspend fun insert(affirmation: Affirmation) = affirmationDao.insert(affirmation)
    override fun getRandomAffirmation() = affirmationDao.getRandomAffirmation()
    override suspend fun markAffirmationAsUsed(affirmationId: Int) = affirmationDao.markAffirmationAsUsed(affirmationId)
    override suspend fun resetAffirmations() = affirmationDao.resetAffirmations()
}