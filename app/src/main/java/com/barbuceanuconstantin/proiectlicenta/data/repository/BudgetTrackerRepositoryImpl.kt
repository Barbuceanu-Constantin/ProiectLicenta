package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategoryDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BudgetTrackerRepositoryImpl @Inject constructor(private val categoryDAO: CategoryDAO,
                                                      private val mainCategoryDAO: MainCategoryDAO)
                                                      : BudgetTrackerRepository {
    /////////////////////////////////////////////////////////
    override suspend fun insertCategory(category: Categories) = categoryDAO.insertCategory(category)
    override fun getAllCategories(): Flow<List<Categories>> = categoryDAO.getAllCategories()
    /////////////////////////////////////////////////////////
    override suspend fun insertMainCategory(mainCategory: MainCategories) = mainCategoryDAO.insertMainCategory(mainCategory)
    override fun getAllMainCategories(): Flow<List<MainCategories>> = mainCategoryDAO.getAllMainCategories()
    /////////////////////////////////////////////////////////
}