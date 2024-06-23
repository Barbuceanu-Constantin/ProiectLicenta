package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(TransactionsScreenUIState())
    val stateFlow: StateFlow<TransactionsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.value = TransactionsScreenUIState(
            showA = showA,
            showP = showP,
            showD = showD,
            buttons = _stateFlow.value.buttons,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedButtons() {
        _stateFlow.value = TransactionsScreenUIState(
            showA = stateFlow.value.showA,
            showP = stateFlow.value.showP,
            showD = stateFlow.value.showD,
            buttons =  !stateFlow.value.buttons,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedLists() {
        _stateFlow.value = TransactionsScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            buttons = _stateFlow.value.buttons,
            revenueTransactions = budgetTrackerRepository.getTransactionsCategoryList("Active"),
            expensesTransactions = budgetTrackerRepository.getTransactionsCategoryList("Pasive"),
            debtTransactions = budgetTrackerRepository.getTransactionsCategoryList("Datorii"),
            categoriesA = budgetTrackerRepository.getRevenueCategories(),
            categoriesP = budgetTrackerRepository.getSpendingCategories(),
            categoriesD = budgetTrackerRepository.getDebtCategories(),
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedIdDelete(idDelete: Int) {
        _stateFlow.value = TransactionsScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            buttons = _stateFlow.value.buttons,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedIdUpdate(idUpdate: Int) {
        _stateFlow.value = TransactionsScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            buttons = _stateFlow.value.buttons,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = idUpdate
        )
    }
    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedLists()
    }
}