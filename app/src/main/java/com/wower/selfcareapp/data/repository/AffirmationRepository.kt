package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.data.local.AffirmationDao
import com.wower.selfcareapp.data.local.model.Affirmation
import javax.inject.Inject

class AffirmationRepository @Inject constructor(private val affirmationDao: AffirmationDao) {
    suspend fun insert(affirmation: Affirmation) = affirmationDao.insert(affirmation)
    suspend fun getRandomAffirmation() = affirmationDao.getRandomAffirmation()
    suspend fun markAffirmationAsUsed(affirmationId: Int) = affirmationDao.markAffirmationAsUsed(affirmationId)
    suspend fun resetAffirmations() = affirmationDao.resetAffirmations()
}