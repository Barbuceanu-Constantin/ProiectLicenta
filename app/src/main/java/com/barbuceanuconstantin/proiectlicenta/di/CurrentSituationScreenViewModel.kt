package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CurrentSituationScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CurrentSituationScreenUIState())

    val stateFlow: StateFlow<CurrentSituationScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChanged(nothing: Boolean) {
        _stateFlow.value = CurrentSituationScreenUIState(nothing)
    }
}