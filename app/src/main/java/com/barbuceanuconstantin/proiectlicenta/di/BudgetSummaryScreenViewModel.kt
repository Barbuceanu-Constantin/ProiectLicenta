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
class BudgetSummaryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    //Nu cred ca e necesar sa ii dau parametrii pentru ca oricum ii initializez in data class
    private val _stateFlow = MutableStateFlow(BudgetSummaryScreenUIState())
    val stateFlow: StateFlow<BudgetSummaryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedTimeInterval(daily: Boolean, weekly: Boolean, monthly: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                date = stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = daily,
                weekly = weekly,
                monthly = monthly
            )
        }
    }
    fun onStateChangedDate(date: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                date = date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly
            )
        }
    }
    fun onStateChangedButtons(buttons: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                date = stateFlow.value.date,
                buttons = buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly
            )
        }
    }
    fun onStateChangedMonth(month: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                date = stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = month,
                dateButton = stateFlow.value.dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly
            )
        }
    }
    fun onStateChangedDateButton(dateButton: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                date = stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly
            )
        }
    }
}