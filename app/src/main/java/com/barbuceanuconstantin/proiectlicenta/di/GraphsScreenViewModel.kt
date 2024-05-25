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
class GraphsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(GraphsScreenUIState())

    val stateFlow: StateFlow<GraphsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedGraphInterval(name : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = name,
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum
        )
    }

    fun onStateChangedGraphType(type : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = _stateFlow.value.graphChoice,
            chartType = type,
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum
        )
    }
}