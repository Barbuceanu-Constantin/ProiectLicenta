package com.barbuceanuconstantin.proiectlicenta.data.repository

import androidx.lifecycle.LiveData
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.TransactionsDAO
import kotlinx.coroutines.flow.Flow
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
    override fun getAllCategories(): Flow<List<Categories>> = categoryDAO.getAllCategories()
    override fun getRevenueCategories() : LiveData<MutableList<Categories>> = categoryDAO.getRevenueCategories()
    override fun getSpendingCategories() : Flow<List<Categories>> = categoryDAO.getSpendingCategories()
    override fun getDebtCategories() : Flow<List<Categories>> = categoryDAO.getDebtCategories()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertTransaction(transaction: Transactions) = transactionsDAO.insertTransaction(transaction)
    override fun getAllTransactions(): Flow<List<Transactions>> = transactionsDAO.getAllTransactions()
    /////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    override suspend fun insertBudget(budget: Budgets) = budgetsDAO.insertBudget(budget)
    override fun getAllBudgets(): Flow<List<Budgets>> = budgetsDAO.getAllBudgets()
    /////////////////////////////////////////////////////////
}