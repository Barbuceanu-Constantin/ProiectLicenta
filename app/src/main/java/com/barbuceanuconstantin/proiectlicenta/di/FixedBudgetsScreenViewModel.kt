package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FixedBudgetsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(FixedBudgetsScreenUIState())

    val stateFlow: StateFlow<FixedBudgetsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.value = FixedBudgetsScreenUIState(
                                                        buttons = buttons,
                                                        budgets = _stateFlow.value.budgets
        )
    }

    fun onStateChangedList() {
        _stateFlow.value = FixedBudgetsScreenUIState(
                                                        buttons = _stateFlow.value.buttons,
                                                        budgets = budgetTrackerRepository.getAllBudgets()
        )
    }

    fun onDeleteByName(name: String) {
        budgetTrackerRepository.deleteBudgetByName(name)
        onStateChangedList()
    }

    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteBudgetById(id)
        onStateChangedList()
    }
}