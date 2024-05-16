package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BudgetSummaryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(BudgetSummaryScreenUIState())
    val stateFlow: StateFlow<BudgetSummaryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedTimeInterval(daily: Boolean, weekly: Boolean, monthly: Boolean) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = daily,
            weekly = weekly,
            monthly = monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedDate(date: String) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedButtons() {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = stateFlow.value.date,
            buttons = !stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedMonth(month: String) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedDateButton(dateButton: Boolean) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }

    fun onStateChangedLists() {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = _stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = budgetTrackerRepository.getTransactionsCategoryList("Active"),
            expensesTransactions = budgetTrackerRepository.getTransactionsCategoryList("Pasive"),
            categoriesA = budgetTrackerRepository.getRevenueCategories(),
            categoriesP = budgetTrackerRepository.getSpendingCategories(),
            categoriesD = budgetTrackerRepository.getDebtCategories(),
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedIdDelete(idDelete: Int) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = _stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = idDelete,
            idUpdate = _stateFlow.value.idUpdate
        )
    }
    fun onStateChangedIdUpdate(idUpdate: Int) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = _stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
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