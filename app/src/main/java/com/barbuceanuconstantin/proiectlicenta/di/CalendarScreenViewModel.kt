package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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
                                                    categoriesA = stateFlow.value.categoriesA,
                                                    categoriesP = stateFlow.value.categoriesP,
                                                    categoriesD = stateFlow.value.categoriesD,
        )
        viewModelScope.launch(Dispatchers.IO) {
            val dateCt: Date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)!!
            revenuesSum = budgetTrackerRepository.getTransactionsRevenuesSumByDay(dateCt)
            expensesSum = budgetTrackerRepository.getTransactionsExpensesSumByDay(dateCt)
            lTrA = budgetTrackerRepository.getRevenueTransactionsByDate(dateCt)
            lTrP= budgetTrackerRepository.getExpensesTransactionsByDate(dateCt)
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
        )
    }
    suspend fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedDate(stateFlow.value.date)
    }
}