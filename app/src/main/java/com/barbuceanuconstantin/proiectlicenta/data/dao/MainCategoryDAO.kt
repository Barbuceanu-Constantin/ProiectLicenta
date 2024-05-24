package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface MainCategoryDAO {
    @Insert
    fun insertMainCategory(mainCategory: MainCategories)

    @Query("SELECT * FROM mainCategories")
    fun getAllMainCategories() : List<MainCategories>

    @Query("DELETE FROM mainCategories")
    suspend fun deleteAllEntriesFromMainCategories()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'mainCategories'") // Reset auto-increment counter
    suspend fun resetPrimaryKeyAutoIncrementValueMainCategories()
}