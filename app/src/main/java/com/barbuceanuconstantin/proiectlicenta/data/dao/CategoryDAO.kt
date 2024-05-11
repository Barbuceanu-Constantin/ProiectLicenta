package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barbuceanuconstantin.proiectlicenta.data.Categories

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Categories>

    @Query("SELECT * FROM categories where main_category == 'Active'")
    fun getRevenueCategories() : List<Categories>

    @Query("SELECT * FROM categories where main_category == 'Pasive'")
    fun getSpendingCategories() : List<Categories>

    @Query("SELECT * FROM categories where main_category == 'Datorii'")
    fun getDebtCategories() : List<Categories>

    @Query("DELETE FROM categories WHERE name = :name")
    fun deleteCategoryByName(name: String)
}