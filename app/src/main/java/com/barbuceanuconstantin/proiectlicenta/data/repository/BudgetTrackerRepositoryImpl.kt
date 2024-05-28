package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.BudgetTrackerDatabase
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.dao.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.dao.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.dao.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.dao.DatabaseDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.TransactionsDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class BudgetTrackerRepositoryImpl @Inject constructor(
    private val categoryDAO: CategoryDAO,
    private val mainCategoryDAO: MainCategoryDAO,
    private val transactionsDAO: TransactionsDAO,
    private val budgetsDAO: BudgetsDAO,
    private val databaseDao: DatabaseDAO,
    private val budgetTrackerDatabase: BudgetTrackerDatabase
) : BudgetTrackerRepository {
    override fun clearPrimaryKeyIndex() = databaseDao.clearPrimaryKeyIndex()

    override suspend fun resetDb() = withContext(Dispatchers.IO){
        budgetTrackerDatabase.runInTransaction{
            runBlocking {
                budgetTrackerDatabase.clearAllTables()
                clearPrimaryKeyIndex()
            }
        }
    }

    /////////////////////////////////////////////////////////
    override suspend fun insertMainCategory(mainCategory: MainCategories) = mainCategoryDAO.insertMainCategory(mainCategory)
    override fun isEmptyMainCategories(): Boolean = mainCategoryDAO.isEmptyMainCategories()
    override fun getMainCategoryCount(): Int = mainCategoryDAO.getMainCategoryCount()
    override fun getAllMainCategories(): List<MainCategories> = mainCategoryDAO.getAllMainCategories()
    override suspend fun deleteAllMainCategories() = mainCategoryDAO.deleteAllEntriesFromMainCategories()
    override suspend fun resetPrimaryKeyAutoIncrementValueMainCategories() = mainCategoryDAO.resetPrimaryKeyAutoIncrementValueMainCategories()
    override suspend fun deletePrimaryKeyIndexMainCategories() = mainCategoryDAO.deletePrimaryKeyIndexMainCategories()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertCategory(category: Categories):Long = categoryDAO.insertCategory(category)
    override fun isEmptyCategories(): Boolean = categoryDAO.isEmptyCategories()
    override fun getCategoryCount(): Int = categoryDAO.getCategoryCount()
    override fun getSeqIndexCategories() : Int = categoryDAO.getSeqIndexCategories()
    override fun getAllCategories(): List<Categories> = categoryDAO.getAllCategories()
    override fun getCategoryName(id: Int) : String = categoryDAO.getCategoryName(id)
    override fun getCategoryId(name: String, main: String) : Int = categoryDAO.getCategoryId(name, main)
    override fun getRevenueCategories() : List<Categories> = categoryDAO.getListCategories("Active")
    override fun getSpendingCategories() : List<Categories> = categoryDAO.getListCategories("Pasive")
    override fun getDebtCategories() : List<Categories> = categoryDAO.getListCategories("Datorii")
    override fun updateCategory(category: Categories) = categoryDAO.updateCategory(category)
    override fun deleteCategoryByNameAndPrincipal(name: String, main: String) = categoryDAO.deleteCategoryByNameAndPrincipal(name, main)
    override fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions> = transactionsDAO.getTransactionsCategoryList(mainCategory)
    override fun getTransactionsCategoryListTotalSum(mainCategory: String): Double = transactionsDAO.getTransactionsCategoryListTotalSum(mainCategory)
    override suspend fun deletePrimaryKeyIndexCategories() = categoryDAO.deletePrimaryKeyIndexCategories()
    override suspend fun deleteAllCategories() = categoryDAO.deleteAllEntriesFromCategories()
    override suspend fun resetPrimaryKeyAutoIncrementValueCategories() = categoryDAO.resetPrimaryKeyAutoIncrementValueCategories()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertTransaction(transaction: Transactions) = transactionsDAO.insertTransaction(transaction)
    override fun isEmptyTransactions(): Boolean = transactionsDAO.isEmptyTransactions()
    override suspend fun prepopulateDbForDemo() = transactionsDAO.prepopulateDbForDemo()
    override fun getAllTransactions(): List<Transactions> = transactionsDAO.getAllTransactions()
    override fun getTransactionsByCategoryAndInterval(
        categoryId: Int,
        startDate: Date,
        endDate: Date
    ): Double = transactionsDAO.getTransactionsByCategoryAndInterval(categoryId, startDate, endDate)
    override fun getTransactionsRevenuesSumByDay(currentDate: Date): Double = transactionsDAO.getTransactionsSumByDay(currentDate, "Active")
    override fun getTransactionsExpensesSumByDay(currentDate: Date): Double = transactionsDAO.getTransactionsSumByDay(currentDate, "Pasive")
    override fun getRevenueTransactionsByDate(currentDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByDate(currentDate, "Active")
    override fun getExpensesTransactionsByDate(currentDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByDate(currentDate, "Pasive")
    override fun getDebtTransactionsByDate(currentDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByDate(currentDate, "Datorii")
    override fun getRevenueTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByInterval(startDate, endDate, "Active")
    override fun getExpensesTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByInterval(startDate, endDate, "Pasive")
    override fun getDebtTransactionsByInterval(startDate: Date, endDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByInterval(startDate, endDate, "Datorii")
    override fun deleteTransactionById(id: Int) = transactionsDAO.deleteTransactionById(id)
    override fun updateTransaction(transaction: Transactions) = transactionsDAO.updateTransaction(transaction)
    override suspend fun deleteAllTransactions() = transactionsDAO.deleteAllEntriesFromTransactions()
    override suspend fun resetPrimaryKeyAutoIncrementValueTransactions() = transactionsDAO.resetPrimaryKeyAutoIncrementValueTransactions()
    override suspend fun deletePrimaryKeyIndexTransactions() = transactionsDAO.deletePrimaryKeyIndexTransactions()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertBudget(budget: Budgets) = budgetsDAO.insertBudget(budget)
    override fun isEmptyBudgets(): Boolean = budgetsDAO.isEmptyBudgets()
    override fun getAllBudgets(): List<Budgets> = budgetsDAO.getAllBudgets()
    override fun updateBudget(budget: Budgets) = budgetsDAO.updateBudget(budget)
    override fun deleteBudgetByName(name: String) = budgetsDAO.deleteBudgetByName(name)
    override fun deleteBudgetById(id: Int) = budgetsDAO.deleteBudgetById(id)
    override suspend fun deleteAllBudgets() = budgetsDAO.deleteAllEntriesFromBudgets()
    override suspend fun resetPrimaryKeyAutoIncrementValueBudgets() = budgetsDAO.resetPrimaryKeyAutoIncrementValueBudgets()
    override suspend fun deletePrimaryKeyIndexBudgets() = budgetsDAO.deletePrimaryKeyIndexBudgets()
    /////////////////////////////////////////////////////////
}