package com.barbuceanuconstantin.proiectlicenta.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAllCategories() : Flow<List<Categories>>

    @Query("SELECT * FROM categories where name == 'Active'")
    fun getRevenueCategories() : LiveData<MutableList<Categories>>

    @Query("SELECT * FROM categories where name == 'Pasive'")
    fun getSpendingCategories() : Flow<List<Categories>>

    @Query("SELECT * FROM categories where name == 'Datorii'")
    fun getDebtCategories() : Flow<List<Categories>>
}