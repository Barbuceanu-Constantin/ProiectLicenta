package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetsDAO {
    @Insert
    fun insertBudget(budget: Budgets)

    @Query("SELECT * FROM budgets")
    fun getAllBudgets() : List<Budgets>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateBudget(budget: Budgets)

    @Query("DELETE FROM budgets WHERE name = :name")
    fun deleteBudgetByName(name: String)
}