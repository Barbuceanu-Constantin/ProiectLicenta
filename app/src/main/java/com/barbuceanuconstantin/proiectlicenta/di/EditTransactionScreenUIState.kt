package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class EditTransactionScreenUIState(
    val category: String = "",
    val payee: String = "",
    val valueSum: String = "",
    val description: String = "",
    val showA: Boolean = false,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val readyToGo: Boolean = false,
    val alertDialog: Boolean = false,
    val listCategoriesRevenue: List<Categories> = listOf(),
    val listCategoriesExpenses: List<Categories> = listOf(),
    val listCategoriesDebts: List<Categories> = listOf(),
    val transaction: Transactions? = null
)
