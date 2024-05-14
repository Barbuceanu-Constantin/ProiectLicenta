package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions


@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Categories>

    @Query("SELECT * FROM categories where main_category == :mainCategory")
    fun getListCategories(mainCategory: String) : List<Categories>

    @Transaction
    @Query("SELECT * FROM categories where main_category == :mainCategory")
    fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions>

    @Query("DELETE FROM categories WHERE name = :name")
    fun deleteCategoryByName(name: String)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCategory(category: Categories)
}