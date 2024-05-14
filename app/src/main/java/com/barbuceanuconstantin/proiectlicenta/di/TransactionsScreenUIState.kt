package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions

data class TransactionsScreenUIState(
    val showA: Boolean = true,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val buttons: Boolean = false,
    val idDelete: Int = -1,
    val idUpdate: Int = -1,
    val revenueTransactions: List<CategoryAndTransactions> = listOf(),
    val expensesTransactions: List<CategoryAndTransactions> = listOf(),
    val debtTransactions: List<CategoryAndTransactions> = listOf(),
    val categoriesA: List<Categories> = listOf(),
    val categoriesP: List<Categories> = listOf(),
    val categoriesD: List<Categories> = listOf()
)
