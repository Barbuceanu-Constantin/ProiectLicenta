package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(EditCategoryScreenUIState())
    val stateFlow: StateFlow<EditCategoryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChanged(showA: Boolean, showP: Boolean, showD: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = showA,
            showP = showP,
            showD = showD,
            category = _stateFlow.value.category,
            filledText = _stateFlow.value.filledText,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onAddCategory(category: Categories) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = category,
            filledText = _stateFlow.value.filledText,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onStateChangedFilledText(filledText: String) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = filledText,
            category = _stateFlow.value.category,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onUpdateReadyToGo(readyToGo: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = _stateFlow.value.filledText,
            category = _stateFlow.value.category,
            readyToGo = readyToGo ,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onUpdateReadyToUpdate(readyToUpdate: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = _stateFlow.value.filledText,
            category = _stateFlow.value.category,
            readyToGo = _stateFlow.value.readyToGo,
            readyToUpdate = readyToUpdate,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onUpdateAlertDialog(alertDialog: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = _stateFlow.value.filledText,
            category = _stateFlow.value.category,
            readyToGo = _stateFlow.value.readyToGo,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            alertDialog = alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun onUpdateAlertAlreadyExistDialog(alertDialog: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = _stateFlow.value.filledText,
            category = _stateFlow.value.category,
            readyToGo = _stateFlow.value.readyToGo,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = _stateFlow.value.revenueCategories,
            expensesCategories = _stateFlow.value.expensesCategories,
            debtCategories = _stateFlow.value.debtCategories,
            alertAlreadyExistDialog = alertDialog
        )
    }
    fun onUpdateCategoryLists() {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = _stateFlow.value.filledText,
            category = _stateFlow.value.category,
            readyToGo = _stateFlow.value.readyToGo,
            readyToUpdate = _stateFlow.value.readyToUpdate,
            alertDialog = _stateFlow.value.alertDialog,
            revenueCategories = budgetTrackerRepository.getRevenueCategories(),
            expensesCategories = budgetTrackerRepository.getSpendingCategories(),
            debtCategories = budgetTrackerRepository.getDebtCategories(),
            alertAlreadyExistDialog = _stateFlow.value.alertAlreadyExistDialog
        )
    }
    fun nullCheckFields(): Boolean {
        return _stateFlow.value.filledText != ""
    }
    suspend fun insertCategory(category: Categories) {
        viewModelScope.launch(IO) {
            budgetTrackerRepository.insertCategory(category)
        }
    }
    fun updateCategoryInDb(category: Categories) {
        viewModelScope.launch(IO) {
            budgetTrackerRepository.updateCategory(category = category)
        }
    }
}