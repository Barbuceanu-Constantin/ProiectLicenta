package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
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
    fun getAllCategories(): List<Categories>
    fun getRevenueCategories() : List<Categories>
    fun getSpendingCategories() : List<Categories>
    fun getDebtCategories() : List<Categories>
    fun updateCategory(category: Categories)
    fun deleteCategoryByName(name: String)
    fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions>
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertTransaction(transaction: Transactions)
    fun getAllTransactions(): Flow<List<Transactions>>
    fun deleteTransactionById(id: Int)
    fun updateTransaction(transaction: Transactions)
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertBudget(budget: Budgets)
    fun getAllBudgets(): List<Budgets>
    fun updateBudget(budget: Budgets)
    fun deleteBudgetByName(name: String)
    ///////////////////////////////////////////////
}