package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface BudgetTrackerRepository {
    ///////////////////////////////////////////////
    suspend fun insertMainCategory(mainCategory: MainCategories)
    fun getAllMainCategories(): Flow<List<MainCategories>>
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertCategory(category: Categories): Long
    fun getAllCategories(): List<Categories>
    fun getCategoryName(id: Int) : String
    fun getCategoryId(name: String, main: String) : Int
    fun getRevenueCategories() : List<Categories>
    fun getSpendingCategories() : List<Categories>
    fun getDebtCategories() : List<Categories>
    fun updateCategory(category: Categories)
    fun deleteCategoryByNameAndPrincipal(name: String, main: String)
    fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions>
    fun getTransactionsCategoryListTotalSum(mainCategory: String): Double
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertTransaction(transaction: Transactions)
    fun getAllTransactions(): Flow<List<Transactions>>
    fun getTransactionsRevenuesSumByDay(currentDate: Date): Double
    fun getTransactionsExpensesSumByDay(currentDate: Date): Double
    fun getRevenueTransactionsByDate(currentDate: Date): List<CategoryAndTransactions>
    fun getExpensesTransactionsByDate(currentDate: Date): List<CategoryAndTransactions>
    fun getRevenueTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions>
    fun getExpensesTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions>
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