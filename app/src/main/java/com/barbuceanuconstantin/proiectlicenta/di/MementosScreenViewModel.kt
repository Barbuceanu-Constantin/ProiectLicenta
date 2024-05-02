package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MementosScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(MementosScreenUIState(true))

    val stateFlow: StateFlow<MementosScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChanged(nothing: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(nothing = nothing)
        }
    }
}