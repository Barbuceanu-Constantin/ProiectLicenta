package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.IntToMonth
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BudgetSummaryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(BudgetSummaryScreenUIState())
    val stateFlow: StateFlow<BudgetSummaryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedFirstComposition(first: Boolean) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = stateFlow.value.date,
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = first
        )
    }
    fun onStateChangedTimeInterval(daily: Boolean, weekly: Boolean, monthly: Boolean) {
        _stateFlow.value = BudgetSummaryScreenUIState(
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            buttons = stateFlow.value.buttons,
            month = stateFlow.value.month,
            dateButton = stateFlow.value.dateButton,
            daily = daily,
            weekly = weekly,
            monthly = monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
        )
    }
    fun onStateChangedDate(date: String) {
        val month : Int = (date[5].code - 48) * 10 + (date[6].code - 48)
        var monthName = ""

        //Nu pot folosi stringResource deoarece nu sunt intr-o functie Composable.
        val monthNames = arrayOf(
            "ianuarie", "februarie", "martie", "aprilie", "mai", "iunie",
            "iulie", "august", "septembrie", "octombrie", "noiembrie", "decembrie"
        )

        // Asigură-te că luna este între 1 și 12
        if (month in 1..12) {
            monthName = monthNames[month - 1]
        }

        _stateFlow.value = BudgetSummaryScreenUIState(
            date = date,
            buttons = stateFlow.value.buttons,
            month = monthName,
            dateButton = stateFlow.value.dateButton,
            daily = stateFlow.value.daily,
            weekly = stateFlow.value.weekly,
            monthly = stateFlow.value.monthly,
            revenueTransactions = _stateFlow.value.revenueTransactions,
            expensesTransactions = _stateFlow.value.expensesTransactions,
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
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
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
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
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
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
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
        )
    }

    fun updateListsBasedOnInterval(
                                startDate: Date,
                                endDate: Date
    ) {
        var balance: Double = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = BudgetSummaryScreenUIState(
                date = _stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly,
                revenueTransactions = budgetTrackerRepository.getRevenueTransactionsByInterval(startDate, endDate),
                expensesTransactions = budgetTrackerRepository.getExpensesTransactionsByInterval(startDate, endDate),
                debtTransactions = budgetTrackerRepository.getDebtTransactionsByInterval(startDate, endDate),
                categoriesA = _stateFlow.value.categoriesA,
                categoriesP = _stateFlow.value.categoriesP,
                categoriesD = _stateFlow.value.categoriesD,
                idDelete = _stateFlow.value.idDelete,
                idUpdate = _stateFlow.value.idUpdate,
                balance = balance,
                firstComposition = _stateFlow.value.firstComposition
            )
            for (categTr in _stateFlow.value.revenueTransactions) {
                for (tr in categTr.transactions) {
                    balance += tr.value
                }
            }
            for (categTr in _stateFlow.value.expensesTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            for (categTr in _stateFlow.value.debtTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            _stateFlow.value = _stateFlow.value.copy(
                balance = balance
            )
        }
    }

    fun updateListsBasedOnDay(date: Date) {
        var balance: Double = 0.0
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = BudgetSummaryScreenUIState(
                date = _stateFlow.value.date,
                buttons = stateFlow.value.buttons,
                month = stateFlow.value.month,
                dateButton = stateFlow.value.dateButton,
                daily = stateFlow.value.daily,
                weekly = stateFlow.value.weekly,
                monthly = stateFlow.value.monthly,
                revenueTransactions = budgetTrackerRepository.getRevenueTransactionsByDate(date),
                expensesTransactions = budgetTrackerRepository.getExpensesTransactionsByDate(date),
                debtTransactions = budgetTrackerRepository.getDebtTransactionsByDate(date),
                categoriesA = _stateFlow.value.categoriesA,
                categoriesP = _stateFlow.value.categoriesP,
                categoriesD = _stateFlow.value.categoriesD,
                idDelete = _stateFlow.value.idDelete,
                idUpdate = _stateFlow.value.idUpdate,
                balance = balance,
                firstComposition = _stateFlow.value.firstComposition
            )
            for (categTr in _stateFlow.value.revenueTransactions) {
                for (tr in categTr.transactions) {
                    balance += tr.value
                }
            }
            for (categTr in _stateFlow.value.expensesTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            for (categTr in _stateFlow.value.debtTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            _stateFlow.value = _stateFlow.value.copy(
                balance = balance
            )
        }
    }

    fun updateListsFull() {
        var balance: Double = 0.0
        viewModelScope.launch(Dispatchers.IO) {
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
                debtTransactions = budgetTrackerRepository.getTransactionsCategoryList("Datorii"),
                categoriesA = _stateFlow.value.categoriesA,
                categoriesP = _stateFlow.value.categoriesP,
                categoriesD = _stateFlow.value.categoriesD,
                idDelete = _stateFlow.value.idDelete,
                idUpdate = _stateFlow.value.idUpdate,
                balance = balance,
                firstComposition = _stateFlow.value.firstComposition
            )
            for (categTr in _stateFlow.value.revenueTransactions) {
                for (tr in categTr.transactions) {
                    balance += tr.value
                }
            }
            for (categTr in _stateFlow.value.expensesTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            for (categTr in _stateFlow.value.debtTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            _stateFlow.value = _stateFlow.value.copy(
                balance = balance
            )
        }
    }

    fun onStateChangedLists() {
        //Se apeleaza la inceput doar o data
        var balance: Double = 0.0
        viewModelScope.launch(Dispatchers.IO) {
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
                debtTransactions = budgetTrackerRepository.getTransactionsCategoryList("Datorii"),
                categoriesA = budgetTrackerRepository.getRevenueCategories(),
                categoriesP = budgetTrackerRepository.getSpendingCategories(),
                categoriesD = budgetTrackerRepository.getDebtCategories(),
                idDelete = _stateFlow.value.idDelete,
                idUpdate = _stateFlow.value.idUpdate,
                balance = balance,
                firstComposition = _stateFlow.value.firstComposition
            )
            for (categTr in _stateFlow.value.revenueTransactions) {
                for (tr in categTr.transactions) {
                    balance += tr.value
                }
            }
            for (categTr in _stateFlow.value.expensesTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            for (categTr in _stateFlow.value.debtTransactions) {
                for (tr in categTr.transactions) {
                    balance -= tr.value
                }
            }
            _stateFlow.value = _stateFlow.value.copy(
                balance = balance
            )
        }
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
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = idDelete,
            idUpdate = _stateFlow.value.idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
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
            debtTransactions = _stateFlow.value.debtTransactions,
            categoriesA = _stateFlow.value.categoriesA,
            categoriesP = _stateFlow.value.categoriesP,
            categoriesD = _stateFlow.value.categoriesD,
            idDelete = _stateFlow.value.idDelete,
            idUpdate = idUpdate,
            balance = _stateFlow.value.balance,
            firstComposition = _stateFlow.value.firstComposition
        )
    }
    fun onDeleteById(id: Int) {
        budgetTrackerRepository.deleteTransactionById(id)
        onStateChangedLists()
    }
}