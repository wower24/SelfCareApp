package com.wower.selfcareapp.domain.use_cases.affirmation

import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomAffirmationUseCase @Inject constructor(
    private val affirmationRepository: AffirmationRepository
) {
    suspend operator fun invoke(): Flow<Affirmation> = affirmationRepository.getRandomAffirmation()
}