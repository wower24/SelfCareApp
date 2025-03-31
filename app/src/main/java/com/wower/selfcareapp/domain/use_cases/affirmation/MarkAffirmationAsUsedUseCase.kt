package com.wower.selfcareapp.domain.use_cases.affirmation

import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import javax.inject.Inject

class MarkAffirmationAsUsedUseCase @Inject constructor(
    private val affirmationRepository: AffirmationRepository
) {
    suspend operator fun invoke(affirmationId: Int) =
        affirmationRepository.markAffirmationAsUsed(affirmationId)
}