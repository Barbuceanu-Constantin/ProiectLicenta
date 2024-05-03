package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PrincipalScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(PrincipalScreenUIState())

    val stateFlow: StateFlow<PrincipalScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChanged(selectedIndex: Int) {
        _stateFlow.value = PrincipalScreenUIState(selectedIndex)
    }
}