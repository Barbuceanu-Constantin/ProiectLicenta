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
            category = budget.categoryId,
            filledText = budget.name,
            valueSum = budget.upperThreshold.toString(),
            openWarningDialog = _stateFlow.value.openWarningDialog,
            idWarningString = _stateFlow.value.idWarningString,
            budget = budget,
            alertDialog = _stateFlow.value.alertDialog,
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
        )
    }
    fun onUpdateDate1(date1: Date) {
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
        )
    }
    fun onUpdateDate2(date2: Date) {
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
        )
    }
    fun onUpdateCategory(category: Int) {
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
        )
    }
    fun nullCheckFields(): Boolean {
        return _stateFlow.value.category != 0 && _stateFlow.value.filledText != ""
                && _stateFlow.value.valueSum != ""
    }
    suspend fun insertBudget(budget: Budgets) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.insertBudget(budget)
        }
    }
    fun updateBudgetInDb(budget: Budgets) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.updateBudget(budget)
        }
    }
    fun updateExpensesList() {
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
            expenseCategoriesList = expensesCategoriesList,
            categoryName = _stateFlow.value.categoryName,)

        viewModelScope.launch(Dispatchers.IO) {
            expensesCategoriesList = budgetTrackerRepository.getSpendingCategories()
            _stateFlow.value = _stateFlow.value.copy(expenseCategoriesList = expensesCategoriesList)
        }
    }

    fun onUpdateCategoryNameSimple(name: String, main: String) {
        var categoryId: Int
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = name,
        )
        viewModelScope.launch(Dispatchers.IO) {
            categoryId = budgetTrackerRepository.getCategoryId(name, main)
            _stateFlow.value = _stateFlow.value.copy(
                category = categoryId
            )
        }
    }

    fun onUpdateCategoryName(id: Int) {
        var categoryName = ""
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
            expenseCategoriesList = _stateFlow.value.expenseCategoriesList,
            categoryName = _stateFlow.value.categoryName,
        )
        viewModelScope.launch(Dispatchers.IO) {
            categoryName = budgetTrackerRepository.getCategoryName(id)
            _stateFlow.value = _stateFlow.value.copy(
                categoryName = categoryName
            )
        }
    }
}