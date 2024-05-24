package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.Categories

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<Categories>

    @Query("SELECT COUNT(*) FROM categories")
    fun getCategoryCount(): Int

    @Query("SELECT (SELECT COUNT(*) FROM categories) == 0")
    fun isEmptyCategories(): Boolean

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

    @Transaction
    @Query("DELETE FROM categories")
    suspend fun deleteAllEntriesFromCategories()

    @Transaction
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'categories'")
    suspend fun resetPrimaryKeyAutoIncrementValueCategories()

    @Query("DELETE FROM sqlite_sequence WHERE name = 'categories'")
    suspend fun deletePrimaryKeyIndexCategories()

    @Query("SELECT seq FROM sqlite_sequence WHERE name = 'categories'")
    fun getSeqIndexCategories() : Int
}