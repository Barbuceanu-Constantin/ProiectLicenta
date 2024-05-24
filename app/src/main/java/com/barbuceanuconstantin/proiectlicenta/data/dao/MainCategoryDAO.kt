package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface MainCategoryDAO {
    @Transaction
    @Insert
    fun insertMainCategory(mainCategory: MainCategories)

    @Transaction
    @Query("SELECT * FROM mainCategories")
    fun getAllMainCategories() : List<MainCategories>

    @Transaction
    @Query("DELETE FROM mainCategories")
    fun deleteAllEntriesFromMainCategories()

    @Transaction
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'mainCategories'") // Reset auto-increment counter
    fun resetPrimaryKeyAutoIncrementValueMainCategories()
}