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

    @Query("SELECT name FROM categories where id = :id")
    fun getCategoryName(id: Int) : String

    @Query("SELECT id FROM categories where name = :name AND main_category = :main")
    fun getCategoryId(name: String, main: String) : Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCategory(category: Categories)

    @Query("DELETE FROM categories WHERE name = :name AND  main_category = :main")
    fun deleteCategoryByNameAndPrincipal(name: String, main: String)

    @Query("DELETE FROM categories")
    fun deleteAllEntriesFromCategories()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'categories'") // Reset auto-increment counter
    fun resetPrimaryKeyAutoIncrementValueCategories()
}