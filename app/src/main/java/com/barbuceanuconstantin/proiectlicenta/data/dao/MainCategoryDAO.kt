package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories

@Dao
interface MainCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMainCategory(mainCategory: MainCategories)

    @Query("SELECT * FROM mainCategories")
    fun getAllMainCategories() : List<MainCategories>

    @Query("SELECT (SELECT COUNT(*) FROM mainCategories) == 0")
    fun isEmptyMainCategories(): Boolean

    @Query("SELECT COUNT(*) FROM mainCategories")
    fun getMainCategoryCount(): Int

    @Query("DELETE FROM mainCategories")
    suspend fun deleteAllEntriesFromMainCategories()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'mainCategories'") // Reset auto-increment counter
    suspend fun resetPrimaryKeyAutoIncrementValueMainCategories()

    @Query("DELETE FROM sqlite_sequence WHERE name = 'mainCategories'")
    suspend fun deletePrimaryKeyIndexMainCategories()
}