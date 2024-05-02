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
class TransactionsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(TransactionsScreenUIState(false, false, false, false))
    val stateFlow: StateFlow<TransactionsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(showA = showA, showP = showP, showD = showD, buttons = false)
        }
    }

    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(  showA = stateFlow.value.showA,
                                showP = stateFlow.value.showP,
                                showD = stateFlow.value.showD,
                                buttons = buttons)
        }
    }
}