package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class BudgetSummaryScreenUIState(
    val daily: Boolean = false,
    val weekly: Boolean = false,
    val monthly: Boolean = false,
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val month: String = "",
    val buttons: Boolean = false,
    val dateButton: Boolean = false,
    val revenueTransactions: List<CategoryAndTransactions> = listOf(),
    val expensesTransactions: List<CategoryAndTransactions> = listOf(),
    val categoriesA: List<Categories> = listOf(),
    val categoriesP: List<Categories> = listOf(),
    val categoriesD: List<Categories> = listOf(),
    val idUpdate: Int = -1,
    val sumRevenues: Double = 0.0,
    val sumExpenses: Double = 0.0
)
