package com.wower.selfcareapp.feature_grounding.domain.use_case

import com.wower.selfcareapp.feature_grounding.data.GroundingExercise
import com.wower.selfcareapp.feature_grounding.data.groundingExercises

class GetRandomGroundingExercise {
    operator fun invoke() : GroundingExercise? {
        return groundingExercises.random()
    }
}