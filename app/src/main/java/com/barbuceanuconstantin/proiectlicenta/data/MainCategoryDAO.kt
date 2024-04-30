package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MainCategoryDAO {
    @Insert
    fun insertMainCategory(mainCategory: MainCategories)

    @Query("SELECT * FROM mainCategories")
    fun getAllMainCategories() : Flow<List<MainCategories>>
}