package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BudgetSummaryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(BudgetSummaryScreenUIState())
    val stateFlow: StateFlow<BudgetSummaryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedTimeInterval(daily: Boolean, weekly: Boolean, monthly: Boolean) {
        val date: Date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(_stateFlow.value.date)!!
        var revenuesSum = 0.0
        var expensesSum = 0.0
        if (daily && !weekly && !monthly) {
            _stateFlow.value = BudgetSummaryScreenUIState(
                date = stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = true,
                weekly = false,
                monthly = false,
                revenueTransactions = budgetTrackerRepository.getRevenueTransactionsByDate(date),
                expensesTransactions = budgetTrackerRepository.getExpensesTransactionsByDate(date),
                categoriesA = _stateFlow.value.categoriesA,
                categoriesP = _stateFlow.value.categoriesP,
                categoriesD = _stateFlow.value.categoriesD,
                idUpdate = _stateFlow.value.idUpdate,
                sumExpenses = _stateFlow.value.sumExpenses,
                sumRevenues = _stateFlow.value.sumRevenues
            )
        } else {
            _stateFlow.value = BudgetSummaryScreenUIState(
                date = stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = daily,
                weekly = weekly,
                monthly = monthly,
                revenueTransactions = listOf(),
                expensesTransactions = listOf(),
                categoriesA = _stateFlow.value.categoriesA,
                categoriesP = _stateFlow.value.categoriesP,
                categoriesD = _stateFlow.value.categoriesD,
                idUpdate = _stateFlow.value.idUpdate,
                sumExpenses = _stateFlow.value.sumExpenses,
                sumRevenues = _stateFlow.value.sumRevenues
            )
        }
        println("_stateFlow.value.revenueTransactions : " + _stateFlow.value.revenueTransactions)
        for(categoryAndTransactions in _stateFlow.value.revenueTransactions)
            for(transaction in categoryAndTransactions.transactions)
                revenuesSum += transaction.value
        println("_stateFlow.value.expensesTransactions : " + _stateFlow.value.expensesTransactions)
        for(categoryAndTransactions in _stateFlow.value.expensesTransactions)
            for(transaction in categoryAndTransactions.transactions)
                expensesSum += transaction.value
        _stateFlow.value = _stateFlow.value.copy(
            sumRevenues = revenuesSum,
            sumExpenses = expensesSum,
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
            idUpdate = _stateFlow.value.idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
        )
        onStateChangedTimeInterval(
            _stateFlow.value.daily,
            _stateFlow.value.weekly,
            _stateFlow.value.monthly
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
            idUpdate = _stateFlow.value.idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
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
            idUpdate = _stateFlow.value.idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
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
            idUpdate = _stateFlow.value.idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
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
            idUpdate = _stateFlow.value.idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
        )
        var revenuesSum = 0.0
        var expensesSum = 0.0
        for(categoryAndTransactions in _stateFlow.value.revenueTransactions)
            for(transaction in categoryAndTransactions.transactions)
                revenuesSum += transaction.value
        for(categoryAndTransactions in _stateFlow.value.expensesTransactions)
            for(transaction in categoryAndTransactions.transactions)
                expensesSum += transaction.value
        _stateFlow.value = _stateFlow.value.copy(
            sumRevenues = revenuesSum,
            sumExpenses = expensesSum,
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
            idUpdate = idUpdate,
            sumExpenses = _stateFlow.value.sumExpenses,
            sumRevenues = _stateFlow.value.sumRevenues
        )
    }
    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedLists()
    }
}