package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CalendarScreenUIState(date = LocalDate.now().toString(), incomes = false, expenses = false, buttons = false))
    val stateFlow: StateFlow<CalendarScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedDate(date: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                                date = date,
                                incomes = stateFlow.value.incomes,
                                expenses = stateFlow.value.expenses,
                                buttons = stateFlow.value.buttons
            )
        }
    }
    fun onStateChangedIncomesExpenses(incomes: Boolean, expenses: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                                date = stateFlow.value.date,
                                incomes = incomes,
                                expenses = expenses,
                                buttons = stateFlow.value.buttons
            )
        }
    }
    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                                date = stateFlow.value.date,
                                incomes = stateFlow.value.incomes,
                                expenses = stateFlow.value.expenses,
                                buttons = buttons
            )
        }
    }
}