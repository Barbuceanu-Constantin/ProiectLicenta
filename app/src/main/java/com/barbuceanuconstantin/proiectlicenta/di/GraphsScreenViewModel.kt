package com.barbuceanuconstantin.proiectlicenta.di

import android.content.ContentValues.TAG
import android.util.Log
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
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class GraphsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(GraphsScreenUIState())

    val stateFlow: StateFlow<GraphsScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun onStateChangedMonthComparisonChartType(type: String) {
        _stateFlow.value = GraphsScreenUIState(
            monthComparisonChartType = type,
            graphChoice = _stateFlow.value.graphChoice,
            //Restul pot fi resetate
        )
    }

    fun onStateChangedGraphInterval(name : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = name,
            //type trebuie resetat. Deci nu il pun aici.
            //monthComparisonChartType la fel
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum,
        )
    }

    fun onStateChangedGraphType(type : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = _stateFlow.value.graphChoice,
            chartType = type,
            //monthComparisonChartType trebuie resetat deci nu il pun aici
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum,
            month = _stateFlow.value.month,
        )
    }

    fun onStateChangedMonth(month : String) {
        _stateFlow.value = GraphsScreenUIState(
            graphChoice = _stateFlow.value.graphChoice,
            //type trebuie resetat. Deci nu il pun aici.
            //monthComparisonChartType la fel
            revenuesSum = _stateFlow.value.revenuesSum,
            expensesSum = _stateFlow.value.expensesSum,
            debtSum = _stateFlow.value.debtSum,
            month = month,
        )
    }

    fun updateMetricsGlobal() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = GraphsScreenUIState(
                graphChoice = _stateFlow.value.graphChoice,
                chartType = _stateFlow.value.chartType,
                //monthComparisonChartType trebuie resetat deci nu il pun aici
                revenuesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Active"),
                expensesSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Pasive"),
                debtSum = budgetTrackerRepository.getTransactionsCategoryListTotalSum("Datorii"),
                month = _stateFlow.value.month,
            )
        }
    }

    fun onStateChangedMonthsListSum(lMonth: List<String>): Triple<List<Double>, List<Double>, List<Double>> {
        val lRevenues: MutableList<Double> = mutableListOf()
        val lExpenses: MutableList<Double> = mutableListOf()
        val lDebt: MutableList<Double> = mutableListOf()
        val lPairDates: MutableList<Pair<Date, Date>> = mutableListOf()

        for (month in lMonth) {
            var startDateString = ""
            var endDateString = ""
            try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val currentYear = LocalDate.now().year

                val monthEnum = getMonthEnum(month)
                val startDate = LocalDate.of(currentYear, monthEnum, 1)
                val endDate = startDate.withDayOfMonth(startDate.lengthOfMonth())

                startDateString = startDate.format(formatter)
                endDateString = endDate.format(formatter)

                val parsedStartDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startDateString)
                val parsedEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endDateString)

                lPairDates.add(Pair(parsedStartDate!!, parsedEndDate!!))
            } catch (e: IllegalArgumentException) {
                println("Invalid month name: $month")
            }
        }

        var lTrRevenue: List<CategoryAndTransactions>
        var lTrExpense: List<CategoryAndTransactions>
        var lTrDebt: List<CategoryAndTransactions>
        var revenuesSum = 0.0
        var expensesSum = 0.0
        var debtSum = 0.0

        val job = viewModelScope.launch(Dispatchers.IO) {
            for (index in lPairDates.indices) {
                val parsedStartDate = lPairDates[index].first
                val parsedEndDate = lPairDates[index].second
                lTrRevenue = budgetTrackerRepository.getRevenueTransactionsByInterval(parsedStartDate, parsedEndDate)
                lTrExpense = budgetTrackerRepository.getExpensesTransactionsByInterval(parsedStartDate, parsedEndDate)
                lTrDebt = budgetTrackerRepository.getDebtTransactionsByInterval(parsedStartDate, parsedEndDate)

                revenuesSum = 0.0
                expensesSum = 0.0
                debtSum = 0.0

                for (categTr in lTrRevenue) {
                    for (tr in categTr.transactions) {
                        revenuesSum += tr.value
                    }
                }
                lRevenues.add(revenuesSum)

                for (categTr in lTrExpense) {
                    for (tr in categTr.transactions) {
                        expensesSum += tr.value
                    }
                }
                lExpenses.add(expensesSum)

                for (categTr in lTrDebt) {
                    for (tr in categTr.transactions) {
                        debtSum += tr.value
                    }
                }
                lDebt.add(debtSum)
            }
        }

        runBlocking {
            job.join()
        }

        val currentMonth = LocalDate.now().monthValue

        return Triple(
            lRevenues.dropLast(12 - currentMonth),
            lExpenses.dropLast(12 - currentMonth),
            lDebt.dropLast(12 - currentMonth)
        )
    }

    fun updateMetricsMonth(month: String) {
        var startDateString = ""
        var endDateString = ""
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentYear = LocalDate.now().year

            val monthEnum = getMonthEnum(month)
            val startDate = LocalDate.of(currentYear, monthEnum, 1)
            val endDate = startDate.withDayOfMonth(startDate.lengthOfMonth())

            startDateString = startDate.format(formatter)
            endDateString = endDate.format(formatter)
        } catch (e: IllegalArgumentException) {
            println("Invalid month name: $month")
        }

        var lTrRevenue: List<CategoryAndTransactions>
        var lTrExpense: List<CategoryAndTransactions>
        var lTrDebt: List<CategoryAndTransactions>
        var revenuesSum = 0.0
        var expensesSum = 0.0
        var debtSum = 0.0
        val parsedStartDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startDateString)
        val parsedEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endDateString)

        viewModelScope.launch(Dispatchers.IO) {
            lTrRevenue = budgetTrackerRepository.getRevenueTransactionsByInterval(parsedStartDate!!, parsedEndDate!!)
            lTrExpense = budgetTrackerRepository.getExpensesTransactionsByInterval(parsedStartDate, parsedEndDate)
            lTrDebt = budgetTrackerRepository.getDebtTransactionsByInterval(parsedStartDate, parsedEndDate)

            for (categTr in lTrRevenue) {
                for (tr in categTr.transactions) {
                    revenuesSum += tr.value
                }
            }
            for (categTr in lTrExpense) {
                for (tr in categTr.transactions) {
                    expensesSum += tr.value
                }
            }
            for (categTr in lTrDebt) {
                for (tr in categTr.transactions) {
                    debtSum += tr.value
                }
            }

            _stateFlow.value = GraphsScreenUIState(
                graphChoice = _stateFlow.value.graphChoice,
                chartType = _stateFlow.value.chartType,
                //monthComparisonChartType trebuie resetat deci nu il pun aici
                revenuesSum = revenuesSum,
                expensesSum = expensesSum,
                debtSum = debtSum,
                month = _stateFlow.value.month,
            )
        }
    }

    private fun getMonthEnum(month: String): Month {
        return when (month.lowercase()) {
            "ianuarie" -> Month.JANUARY
            "februarie" -> Month.FEBRUARY
            "martie" -> Month.MARCH
            "aprilie" -> Month.APRIL
            "mai" -> Month.MAY
            "iunie" -> Month.JUNE
            "iulie" -> Month.JULY
            "august" -> Month.AUGUST
            "septembrie" -> Month.SEPTEMBER
            "octombrie" -> Month.OCTOBER
            "noiembrie" -> Month.NOVEMBER
            "decembrie" -> Month.DECEMBER
            else -> throw IllegalArgumentException("Invalid month name: $month")
        }
    }
}