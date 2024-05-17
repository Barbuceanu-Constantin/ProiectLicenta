package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.dao.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.dao.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.dao.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.dao.TransactionsDAO
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class BudgetTrackerRepositoryImpl @Inject constructor(
    private val categoryDAO: CategoryDAO,
    private val mainCategoryDAO: MainCategoryDAO,
    private val transactionsDAO: TransactionsDAO,
    private val budgetsDAO: BudgetsDAO
) : BudgetTrackerRepository {
    /////////////////////////////////////////////////////////
    override suspend fun insertMainCategory(mainCategory: MainCategories) = mainCategoryDAO.insertMainCategory(mainCategory)
    override fun getAllMainCategories(): Flow<List<MainCategories>> = mainCategoryDAO.getAllMainCategories()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertCategory(category: Categories):Long = categoryDAO.insertCategory(category)
    override fun getAllCategories(): List<Categories> = categoryDAO.getAllCategories()
    override fun getCategoryName(id: Int) : String = categoryDAO.getCategoryName(id)
    override fun getCategoryId(name: String, main: String) : Int = categoryDAO.getCategoryId(name, main)
    override fun getRevenueCategories() : List<Categories> = categoryDAO.getListCategories("Active")
    override fun getSpendingCategories() : List<Categories> = categoryDAO.getListCategories("Pasive")
    override fun getDebtCategories() : List<Categories> = categoryDAO.getListCategories("Datorii")
    override fun updateCategory(category: Categories) { categoryDAO.updateCategory(category) }
    override fun deleteCategoryByName(name: String) { categoryDAO.deleteCategoryByName(name) }
    override fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions> = transactionsDAO.getTransactionsCategoryList(mainCategory)
    override fun getTransactionsCategoryListTotalSum(mainCategory: String): Double = transactionsDAO.getTransactionsCategoryListTotalSum(mainCategory)
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertTransaction(transaction: Transactions) = transactionsDAO.insertTransaction(transaction)
    override fun getAllTransactions(): Flow<List<Transactions>> = transactionsDAO.getAllTransactions()
    override fun getTransactionsRevenuesSumByDay(currentDate: Date): Double = transactionsDAO.getTransactionsSumByDay(currentDate, "Active")
    override fun getTransactionsExpensesSumByDay(currentDate: Date): Double = transactionsDAO.getTransactionsSumByDay(currentDate, "Pasive")
    override fun getRevenueTransactionsByDate(currentDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByDate(currentDate, "Active")
    override fun getExpensesTransactionsByDate(currentDate: Date): List<CategoryAndTransactions> = transactionsDAO.getTransactionsByDate(currentDate, "Pasive")
    override fun deleteTransactionById(id: Int) = transactionsDAO.deleteTransactionById(id)
    override fun updateTransaction(transaction: Transactions) = transactionsDAO.updateTransaction(transaction)
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertBudget(budget: Budgets) = budgetsDAO.insertBudget(budget)
    override fun getAllBudgets(): List<Budgets> = budgetsDAO.getAllBudgets()
    override fun updateBudget(budget: Budgets) { budgetsDAO.updateBudget(budget) }
    override fun deleteBudgetByName(name: String) { budgetsDAO.deleteBudgetByName(name) }
    /////////////////////////////////////////////////////////
}