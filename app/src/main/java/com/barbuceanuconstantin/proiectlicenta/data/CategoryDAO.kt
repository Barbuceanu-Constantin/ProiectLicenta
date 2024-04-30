package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert
    fun insertCategory(category: Categories)

    @Query("SELECT * FROM categories")
    fun getAllCategories() : Flow<List<Categories>>
}