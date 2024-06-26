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
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MementosScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(MementosScreenUIState())

    val stateFlow: StateFlow<MementosScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedList() {
        _stateFlow.value = MementosScreenUIState(
            budgets = budgetTrackerRepository.getAllBudgets()
        )
    }

    fun getCurrentBudgetFilling(categoryId: Int, startDate: Date, endDate: Date): Double {
        var total = 0.0

        val job = viewModelScope.launch(Dispatchers.IO) {
            total = budgetTrackerRepository.getTransactionsSumByCategoryAndInterval(
                categoryId, startDate, endDate
            )
        }

        runBlocking {
            job.join()
        }

        return total
    }
}