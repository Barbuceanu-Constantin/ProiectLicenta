package com.barbuceanuconstantin.proiectlicenta.data.repository

import androidx.lifecycle.LiveData
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow

interface BudgetTrackerRepository {
    ///////////////////////////////////////////////
    suspend fun insertMainCategory(mainCategory: MainCategories)
    fun getAllMainCategories(): Flow<List<MainCategories>>
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertCategory(category: Categories): Long
    fun getAllCategories(): Flow<List<Categories>>
    fun getRevenueCategories() : LiveData<MutableList<Categories>>
    fun getSpendingCategories() : Flow<List<Categories>>
    fun getDebtCategories() : Flow<List<Categories>>
    ///////////////////////////////////////////////
    suspend fun insertTransaction(transaction: Transactions)
    fun getAllTransactions(): Flow<List<Transactions>>
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertBudget(budget: Budgets)
    fun getAllBudgets(): Flow<List<Budgets>>
    ///////////////////////////////////////////////
}