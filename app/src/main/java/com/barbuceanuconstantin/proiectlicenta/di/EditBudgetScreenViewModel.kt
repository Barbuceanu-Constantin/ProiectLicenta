package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            category = budget.category,
            filledText = budget.name,
            valueSum = budget.upperTreshold.toString(),
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
            budget = _stateFlow.value.budget
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
            budget = _stateFlow.value.budget
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
            budget = _stateFlow.value.budget
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
            budget = _stateFlow.value.budget
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
            budget = _stateFlow.value.budget
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
            budget = _stateFlow.value.budget
        )
    }
}