package com.wower.selfcareapp.feature_grounding.preesentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_grounding.data.GroundingExercise
import com.wower.selfcareapp.feature_grounding.domain.use_case.GetRandomGroundingExercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroundingExerciseViewModel @Inject constructor(
    private val getRandomGroundingExercise: GetRandomGroundingExercise
): ViewModel() {
    private val _exercise = mutableStateOf<GroundingExercise>(GroundingExercise(0, "", emptyList()))
    val exercise = _exercise

    init {
        viewModelScope.launch {
            _exercise.value = getRandomGroundingExercise() ?: GroundingExercise(0, "", emptyList())
        }
    }
}