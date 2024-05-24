package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.Budgets

@Dao
interface BudgetsDAO {
    @Insert
    fun insertBudget(budget: Budgets)

    @Query("SELECT * FROM budgets")
    fun getAllBudgets() : List<Budgets>

    @Query("SELECT (SELECT COUNT(*) FROM budgets) == 0")
    fun isEmptyBudgets(): Boolean

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateBudget(budget: Budgets)

    @Query("DELETE FROM budgets WHERE name = :name")
    fun deleteBudgetByName(name: String)

    @Query("DELETE FROM budgets")
    suspend fun deleteAllEntriesFromBudgets()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'budgets'")
    suspend fun resetPrimaryKeyAutoIncrementValueBudgets()

    @Query("DELETE FROM sqlite_sequence WHERE name = 'budgets'")
    suspend fun deletePrimaryKeyIndexBudgets()
}