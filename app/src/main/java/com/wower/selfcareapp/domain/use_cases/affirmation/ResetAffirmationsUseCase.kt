package com.wower.selfcareapp.domain.use_cases.affirmation

import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import javax.inject.Inject

class ResetAffirmationsUseCase @Inject constructor(
    private val affirmationRepository: AffirmationRepository
) {
    suspend operator fun invoke() = affirmationRepository.resetAffirmations()
}