package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface BudgetTrackerRepository {
    fun clearPrimaryKeyIndex()
    suspend fun resetDb()
    ///////////////////////////////////////////////
    suspend fun insertMainCategory(mainCategory: MainCategories)
    fun getAllMainCategories(): List<MainCategories>
    suspend fun deleteAllMainCategories()
    suspend fun resetPrimaryKeyAutoIncrementValueMainCategories()
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertCategory(category: Categories): Long
    fun getSeqIndexCategories() : Int
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
    suspend fun deleteAllCategories()
    suspend fun deletePrimaryKeyIndexCategories()
    suspend fun resetPrimaryKeyAutoIncrementValueCategories()
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertTransaction(transaction: Transactions)
    suspend fun prepopulateDbForDemo()
    fun getAllTransactions(): Flow<List<Transactions>>
    fun getTransactionsRevenuesSumByDay(currentDate: Date): Double
    fun getTransactionsExpensesSumByDay(currentDate: Date): Double
    fun getRevenueTransactionsByDate(currentDate: Date): List<CategoryAndTransactions>
    fun getExpensesTransactionsByDate(currentDate: Date): List<CategoryAndTransactions>
    fun getDebtTransactionsByDate(currentDate: Date): List<CategoryAndTransactions>
    fun getRevenueTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions>
    fun getExpensesTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions>
    fun getDebtTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions>
    fun deleteTransactionById(id: Int)
    fun updateTransaction(transaction: Transactions)
    suspend fun deleteAllTransactions()
    suspend fun resetPrimaryKeyAutoIncrementValueTransactions()
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    suspend fun insertBudget(budget: Budgets)
    fun getAllBudgets(): List<Budgets>
    fun updateBudget(budget: Budgets)
    fun deleteBudgetByName(name: String)
    suspend fun deleteAllBudgets()
    suspend fun resetPrimaryKeyAutoIncrementValueBudgets()
    ///////////////////////////////////////////////
}