package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import java.time.LocalDate

data class CalendarScreenUIState(
    val date: String = LocalDate.now().toString(),
    val incomes: Boolean = false,
    val expenses: Boolean = false,
    val buttons: Boolean = false,
    val sumRevenues: Double = 0.0,
    val sumExpenses: Double = 0.0,
    val lTrA: List<CategoryAndTransactions> = listOf(),
    val lTrP: List<CategoryAndTransactions> = listOf(),
    val categoriesA: List<Categories> = listOf(),
    val categoriesP: List<Categories> = listOf(),
    val categoriesD: List<Categories> = listOf(),
    val idDelete: Int = -1,
    val idUpdate: Int = -1,
    val firstComposition: Boolean = true
)
