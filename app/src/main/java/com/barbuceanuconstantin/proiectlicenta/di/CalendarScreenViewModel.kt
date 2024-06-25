package com.barbuceanuconstantin.proiectlicenta.di

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import com.barbuceanuconstantin.proiectlicenta.navigation.budgetSummaryScreen
import com.barbuceanuconstantin.proiectlicenta.stripTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun onStateChangedDate(date: String) {
        var revenuesSum: Double = 0.0
        var expensesSum: Double = 0.0
        var lTrA: List<CategoryAndTransactions>
        var lTrP: List<CategoryAndTransactions>
        viewModelScope.launch(Dispatchers.IO) {
            var dateCt: Date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)!!
            dateCt = stripTime(dateCt)

            lTrA = budgetTrackerRepository.getRevenueTransactionsByDate(dateCt)
            lTrP= budgetTrackerRepository.getExpensesTransactionsByDate(dateCt)
            revenuesSum = budgetTrackerRepository.getTransactionsRevenuesSumByDay(dateCt)
            expensesSum = budgetTrackerRepository.getTransactionsExpensesSumByDay(dateCt)
            _stateFlow.value = _stateFlow.value.copy(
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
                firstComposition = false
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
                                                    firstComposition = _stateFlow.value.firstComposition
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
                                                    firstComposition = _stateFlow.value.firstComposition
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
            idDelete = stateFlow.value.idDelete,
            firstComposition = false
        )
        onStateChangedDate(LocalDate.now().toString())
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
            idDelete = stateFlow.value.idDelete,
            firstComposition = _stateFlow.value.firstComposition
        )
    }
    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedDate(stateFlow.value.date)
    }
}