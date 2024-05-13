package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
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
class EditBudgetScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(EditBudgetScreenUIState())
    val stateFlow: StateFlow<EditBudgetScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onAddBudget(budget: Budgets) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = budget.startDate,
            date2 = budget.endDate,
            category = budget.categoryName,
            filledText = budget.name,
            valueSum = budget.upperThreshold.toString(),
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = budget,
        )
    }
    fun onUpdateDate1(date1: String) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateDate2(date2: String) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateCategory(category: String) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateFilledText(filledText: String) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateValueSum(valueSum: String) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateOpenWarningDialog(openWarningDialog: Boolean, idWarningString: Int) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = openWarningDialog,
            idWarningString = idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo
        )
    }
    fun onUpdateReadyToGo(readyToGo: Boolean) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = readyToGo
        )
    }
    suspend fun insertBudget(budget: Budgets) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.insertBudget(budget)
        }
    }
    suspend fun updateBudgetInDb(budget: Budgets) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.updateBudget(budget)
        }
    }
}