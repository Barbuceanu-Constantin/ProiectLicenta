package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(GraphsScreenUIState())

    val stateFlow: StateFlow<GraphsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedGraphInterval(name : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = name,
            //type trebuie resetat. Deci nu il pun aici.
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
            debtSum = _stateFlow.value.debtSum,
            month = _stateFlow.value.month
        )
    }

    fun onStateChangedMonth(month : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = _stateFlow.value.graphChoice,
            chartType = _stateFlow.value.chartType,
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum,
            month = month
        )
    }

    fun updateMetricsGlobal() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = GraphsScreenUIState(
                graphChoice = _stateFlow.value.graphChoice,
                chartType = _stateFlow.value.chartType,
                revenuesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Active"),
                expensesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Pasive"),
                debtSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Datorii"),
                month = _stateFlow.value.month
            )
        }
    }
}