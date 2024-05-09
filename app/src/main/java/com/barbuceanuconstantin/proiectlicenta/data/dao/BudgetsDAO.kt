package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetsDAO {
    @Insert
    fun insertBudget(budget: Budgets)

    @Query("SELECT * FROM budgets")
    fun getAllBudgets() : Flow<List<Budgets>>
}