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
    private val _stateFlow = MutableStateFlow(TransactionsScreenUIState())
    val stateFlow: StateFlow<TransactionsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.value = TransactionsScreenUIState(showA, showP, showD)
    }

    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.value = TransactionsScreenUIState(stateFlow.value.showA, stateFlow.value.showP, stateFlow.value.showD, buttons)
    }
}