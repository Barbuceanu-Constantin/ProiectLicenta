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
import java.util.Date
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
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
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
            readyToGo = readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
        )
    }
    fun onUpdateAlertDialog(alertDialog: Boolean) {
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList
        )
    }
    fun nullCheckFields(): Boolean {
        return _stateFlow.value.category != "" && _stateFlow.value.filledText != ""
                && _stateFlow.value.valueSum != ""
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
    suspend fun updateExpensesList() {
        var expensesCategoriesList: List<Categories> = listOf()
        _stateFlow.value = EditBudgetScreenUIState(
            date1 = _stateFlow.value.date1,
            date2 = _stateFlow.value.date2,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            valueSum = _stateFlow.value.valueSum,
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = _stateFlow.value.budget,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = expensesCategoriesList)

        viewModelScope.launch(Dispatchers.IO) {
            expensesCategoriesList = budgetTrackerRepository.getSpendingCategories()
            _stateFlow.value = _stateFlow.value.copy(expenseCategoriesList = expensesCategoriesList)
        }
    }
}