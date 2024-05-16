package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import com.barbuceanuconstantin.proiectlicenta.navigation.budgetSummaryScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CalendarScreenUIState())
    val stateFlow: StateFlow<CalendarScreenUIState>
        get() = _stateFlow.asStateFlow()

    suspend fun onStateChangedDate(date: String) {
        var revenuesSum: Double = 0.0
        var expensesSum: Double = 0.0
        var lTrA: List<CategoryAndTransactions> = listOf()
        var lTrP: List<CategoryAndTransactions> = listOf()
        _stateFlow.value = CalendarScreenUIState(
                                                    date = date,
                                                    incomes = stateFlow.value.incomes,
                                                    expenses = stateFlow.value.expenses,
                                                    buttons = stateFlow.value.buttons,
                                                    sumRevenues = revenuesSum,
                                                    sumExpenses = expensesSum,
                                                    lTrA = lTrA,
                                                    lTrP = lTrP,
                                                    idUpdate = stateFlow.value.idUpdate,
                                                    idDelete = stateFlow.value.idDelete,
                                                    categoriesA = stateFlow.value.categoriesA,
                                                    categoriesP = stateFlow.value.categoriesP,
                                                    categoriesD = stateFlow.value.categoriesD,
        )
        viewModelScope.launch(Dispatchers.IO) {
            revenuesSum = budgetTrackerRepository.getTransactionsRevenuesSumByDay(date)
            expensesSum = budgetTrackerRepository.getTransactionsExpensesSumByDay(date)
            lTrA = budgetTrackerRepository.getRevenueTransactionsByDate(date)
            lTrP= budgetTrackerRepository.getExpensesTransactionsByDate(date)
            _stateFlow.value = _stateFlow.value.copy(
                sumRevenues = revenuesSum,
                sumExpenses = expensesSum,
                lTrA = lTrA,
                lTrP = lTrP
            )
        }
    }

    fun onStateChangedIncomesExpenses(incomes: Boolean, expenses: Boolean) {
        _stateFlow.value = CalendarScreenUIState(
                                                    date = stateFlow.value.date,
                                                    incomes = incomes,
                                                    expenses = expenses,
                                                    buttons = stateFlow.value.buttons,
                                                    sumRevenues = stateFlow.value.sumRevenues,
                                                    sumExpenses = stateFlow.value.sumExpenses,
                                                    lTrA = stateFlow.value.lTrA,
                                                    lTrP = stateFlow.value.lTrP,
                                                    idUpdate = stateFlow.value.idUpdate,
                                                    idDelete = stateFlow.value.idDelete,
                                                    categoriesA = stateFlow.value.categoriesA,
                                                    categoriesP = stateFlow.value.categoriesP,
                                                    categoriesD = stateFlow.value.categoriesD,
        )
    }
    fun onStateChangedButtons() {
        _stateFlow.value = CalendarScreenUIState(
                                                    date = stateFlow.value.date,
                                                    incomes = stateFlow.value.incomes,
                                                    expenses = stateFlow.value.expenses,
                                                    buttons = !stateFlow.value.buttons,
                                                    sumRevenues = stateFlow.value.sumRevenues,
                                                    sumExpenses = stateFlow.value.sumExpenses,
                                                    lTrA = stateFlow.value.lTrA,
                                                    lTrP = stateFlow.value.lTrP,
                                                    idUpdate = stateFlow.value.idUpdate,
                                                    idDelete = stateFlow.value.idDelete,
                                                    categoriesA = stateFlow.value.categoriesA,
                                                    categoriesP = stateFlow.value.categoriesP,
                                                    categoriesD = stateFlow.value.categoriesD,
        )
    }

    fun onStateChangedCategoryLists() {
        _stateFlow.value = CalendarScreenUIState(
            date = stateFlow.value.date,
            incomes = stateFlow.value.incomes,
            expenses = stateFlow.value.expenses,
            buttons = stateFlow.value.buttons,
            sumRevenues = stateFlow.value.sumRevenues,
            sumExpenses = stateFlow.value.sumExpenses,
            lTrA = stateFlow.value.lTrA,
            lTrP = stateFlow.value.lTrP,
            categoriesA = budgetTrackerRepository.getRevenueCategories(),
            categoriesP = budgetTrackerRepository.getSpendingCategories(),
            categoriesD = budgetTrackerRepository.getDebtCategories(),
            idUpdate = stateFlow.value.idUpdate,
            idDelete = stateFlow.value.idDelete
        )
    }

    fun onStateChangedIdDelete(idDelete: Int) {
        _stateFlow.value = CalendarScreenUIState(
            date = stateFlow.value.date,
            incomes = stateFlow.value.incomes,
            expenses = stateFlow.value.expenses,
            buttons = stateFlow.value.buttons,
            sumRevenues = stateFlow.value.sumRevenues,
            sumExpenses = stateFlow.value.sumExpenses,
            lTrA = stateFlow.value.lTrA,
            lTrP = stateFlow.value.lTrP,
            categoriesA = stateFlow.value.categoriesA,
            categoriesP = stateFlow.value.categoriesP,
            categoriesD = stateFlow.value.categoriesD,
            idUpdate = stateFlow.value.idUpdate,
            idDelete = idDelete,
        )
    }

    fun onStateChangedIdUpdate(idUpdate: Int) {
        _stateFlow.value = CalendarScreenUIState(
            date = stateFlow.value.date,
            incomes = stateFlow.value.incomes,
            expenses = stateFlow.value.expenses,
            buttons = stateFlow.value.buttons,
            sumRevenues = stateFlow.value.sumRevenues,
            sumExpenses = stateFlow.value.sumExpenses,
            lTrA = stateFlow.value.lTrA,
            lTrP = stateFlow.value.lTrP,
            categoriesA = stateFlow.value.categoriesA,
            categoriesP = stateFlow.value.categoriesP,
            categoriesD = stateFlow.value.categoriesD,
            idUpdate = idUpdate,
            idDelete = stateFlow.value.idDelete
        )
    }
    suspend fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedDate(stateFlow.value.date)
    }
}