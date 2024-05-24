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
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: Categories): Long

    @Transaction
    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Categories>

    @Transaction
    @Query("SELECT * FROM categories where main_category == :mainCategory")
    fun getListCategories(mainCategory: String) : List<Categories>

    @Transaction
    @Query("SELECT name FROM categories where id = :id")
    fun getCategoryName(id: Int) : String

    @Transaction
    @Query("SELECT id FROM categories where name = :name AND main_category = :main")
    fun getCategoryId(name: String, main: String) : Int

    @Transaction
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCategory(category: Categories)

    @Transaction
    @Query("DELETE FROM categories WHERE name = :name AND  main_category = :main")
    fun deleteCategoryByNameAndPrincipal(name: String, main: String)

    @Transaction
    @Query("DELETE FROM categories")
    fun deleteAllEntriesFromCategories()

    @Transaction
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'categories'") // Reset auto-increment counter
    fun resetPrimaryKeyAutoIncrementValueCategories()
}