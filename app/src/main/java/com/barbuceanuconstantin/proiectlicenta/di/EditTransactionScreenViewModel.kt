package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTransactionScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(EditTransactionScreenUIState())
    val stateFlow: StateFlow<EditTransactionScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onAddTransaction(transaction: Transactions) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onStateChanged(showA: Boolean, showP: Boolean, showD: Boolean) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = showA,
            showP = showP,
            showD = showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateCategory(category: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateDescription(description: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdatePayee(payee: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateDate(date: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateValueSum(valueSum: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateReadyToGo(readyToGo: Boolean) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    fun onUpdateAlertDialog(alertDialog: Boolean) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = alertDialog,
            listCategoriesExpenses = _stateFlow.value.listCategoriesExpenses,
            listCategoriesRevenue = _stateFlow.value.listCategoriesRevenue,
            listCategoriesDebts = _stateFlow.value.listCategoriesDebts
        )
    }
    suspend fun updateLists() {
        var expensesCategoriesList: List<Categories> = listOf()
        var revenueCategoriesList: List<Categories> = listOf()
        var debtsCategoriesList: List<Categories> = listOf()
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction,
            readyToGo = _stateFlow.value.readyToGo,
            alertDialog = _stateFlow.value.alertDialog,
            listCategoriesExpenses = expensesCategoriesList,
            listCategoriesRevenue = revenueCategoriesList,
            listCategoriesDebts = debtsCategoriesList
        )

        viewModelScope.launch(Dispatchers.IO) {
            revenueCategoriesList = budgetTrackerRepository.getRevenueCategories()
            expensesCategoriesList = budgetTrackerRepository.getSpendingCategories()
            debtsCategoriesList = budgetTrackerRepository.getDebtCategories()
            _stateFlow.value = _stateFlow.value.copy(
                                                        listCategoriesRevenue = revenueCategoriesList,
                                                        listCategoriesExpenses = expensesCategoriesList,
                                                        listCategoriesDebts = debtsCategoriesList
            )
        }
    }
    fun nullCheckFields(): Boolean {
        return _stateFlow.value.category != "" && _stateFlow.value.payee != ""
                && _stateFlow.value.valueSum != "" && _stateFlow.value.valueSum.toDouble() != 0.0
    }
    suspend fun insertTransaction(transaction: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.insertTransaction(transaction)
        }
    }
    suspend fun updateTransactionInDb(transaction: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.updateTransaction(transaction)
        }
    }
}