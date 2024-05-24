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
class DemoScreenViewModel @Inject constructor(
    val budgetTrackerRepository: BudgetTrackerRepository
): ViewModel() {
    private val _stateFlow = MutableStateFlow(DemoScreenUIState())

    val stateFlow: StateFlow<DemoScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onDeleteTables() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.deleteAllMainCategories()
            budgetTrackerRepository.deleteAllBudgets()
            budgetTrackerRepository.deleteAllTransactions()
            budgetTrackerRepository.deleteAllCategories()
            budgetTrackerRepository.deletePrimaryKeyIndexCategories()
            budgetTrackerRepository.resetDb()
        }
    }

    fun updateTablesForDemo() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.prepopulateDbForDemo()
        }
    }
}