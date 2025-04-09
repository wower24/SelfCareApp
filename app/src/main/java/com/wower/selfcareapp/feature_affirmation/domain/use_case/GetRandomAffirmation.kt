package com.wower.selfcareapp.feature_affirmation.domain.use_case

import com.wower.selfcareapp.feature_affirmation.domain.model.Affirmation
import com.wower.selfcareapp.feature_affirmation.domain.repository.AffirmationRepository

class GetRandomAffirmation(
    private val repository: AffirmationRepository
) {
    operator suspend fun invoke() : Affirmation? {
        var affirmation = repository.getRandomAffirmation()
        if (affirmation == null) {
            repository.resetAffirmations()
            affirmation = repository.getRandomAffirmation()
        }
        repository.markAffirmationAsUsed(affirmation?.id ?: 0)
        return affirmation
    }
}