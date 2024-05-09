package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetsDAO {
    @Insert
    fun insertBudget(budget: Budgets)

    @Query("SELECT * FROM budgets")
    fun getAllBudgets() : Flow<List<Budgets>>
}