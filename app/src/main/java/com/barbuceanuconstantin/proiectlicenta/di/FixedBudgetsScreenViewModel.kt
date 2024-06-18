package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FixedBudgetsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(FixedBudgetsScreenUIState())

    val stateFlow: StateFlow<FixedBudgetsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.value = FixedBudgetsScreenUIState(
                                                        buttons = buttons,
                                                        budgets = _stateFlow.value.budgets,
                                                        revenues = _stateFlow.value.revenues
        )
    }

    fun onStateChangedList() {
        _stateFlow.value = FixedBudgetsScreenUIState(
                                                        buttons = _stateFlow.value.buttons,
                                                        budgets = budgetTrackerRepository.getAllBudgets(),
                                                        revenues = _stateFlow.value.revenues
        )
    }

    fun getTotalRevenues(startDate: Date, endDate: Date) {
        var list = listOf<CategoryAndTransactions>()

        val job = viewModelScope.launch(Dispatchers.IO) {
            list = budgetTrackerRepository.getRevenueTransactionsByInterval(startDate, endDate)
        }
        runBlocking {
            job.join()
        }

        var sum = 0.0
        list.forEach { categoryAndTransactions ->
            val transactions = categoryAndTransactions.transactions
            transactions.forEach { transaction ->
                sum += transaction.value
            }
        }
        _stateFlow.value = FixedBudgetsScreenUIState(
            buttons = _stateFlow.value.buttons,
            budgets = _stateFlow.value.budgets,
            revenues = sum
        )
    }

    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteBudgetById(id)
        onStateChangedList()
    }
}