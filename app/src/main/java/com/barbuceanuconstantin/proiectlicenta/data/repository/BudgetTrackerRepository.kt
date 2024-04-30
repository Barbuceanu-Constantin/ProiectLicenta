package com.barbuceanuconstantin.proiectlicenta.data.repository

import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import kotlinx.coroutines.flow.Flow

interface BudgetTrackerRepository {
    ///////////////////////////////////////////////
    suspend fun insertCategory(category: Categories)
    fun getAllCategories(): Flow<List<Categories>>
    ///////////////////////////////////////////////
    suspend fun insertMainCategory(mainCategory: MainCategories)
    fun getAllMainCategories(): Flow<List<MainCategories>>
    ///////////////////////////////////////////////
}