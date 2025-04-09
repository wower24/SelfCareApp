package com.wower.selfcareapp.feature_affirmation.presentation.affirmation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wower.selfcareapp.feature_affirmation.domain.use_case.GetRandomAffirmation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AffirmationViewModel @Inject constructor (
    private val getRandomAffirmation: GetRandomAffirmation
): ViewModel() {
    private val _affirmation = mutableStateOf("Affirmation should go here")
    val affirmation = _affirmation

    init {
        viewModelScope.launch {
            _affirmation.value = getRandomAffirmation()?.affirmation ?: "You look gorgeous!"
        }
    }
}