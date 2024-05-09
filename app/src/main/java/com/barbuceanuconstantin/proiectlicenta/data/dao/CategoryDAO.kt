package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAllCategories() : Flow<List<Categories>>

    @Query("SELECT * FROM categories where main_category == 'Active'")
    fun getRevenueCategories() : Flow<List<Categories>>

    @Query("SELECT * FROM categories where main_category == 'Pasive'")
    fun getSpendingCategories() : Flow<List<Categories>>

    @Query("SELECT * FROM categories where main_category == 'Datorii'")
    fun getDebtCategories() : Flow<List<Categories>>
}