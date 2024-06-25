package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrincipalScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(PrincipalScreenUIState())

    val stateFlow: StateFlow<PrincipalScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChanged(selectedIndex: Int) {
        _stateFlow.value = PrincipalScreenUIState(
                                                    selectedIndex = selectedIndex,
                                                    revenuesSum = _stateFlow.value.revenuesSum,
                                                    expensesSum = _stateFlow.value.expensesSum,
                                                    debtSum = _stateFlow.value.debtSum
        )
    }

    fun updateMetrics() {
        var revenuesSum: Double = 0.0
        var expensesSum: Double = 0.0
        var debtSum: Double = 0.0
        _stateFlow.value = PrincipalScreenUIState(
            selectedIndex = _stateFlow.value.selectedIndex,
            revenuesSum = revenuesSum,
            expensesSum = expensesSum,
            debtSum = debtSum
        )

        viewModelScope.launch(Dispatchers.IO) {
            revenuesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Active")
            expensesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Pasive")
            debtSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Datorii")
            _stateFlow.value = _stateFlow.value.copy(
                revenuesSum = revenuesSum,
                expensesSum = expensesSum,
                debtSum = debtSum
            )
        }
    }
}