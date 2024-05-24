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
    @Transaction
    @Insert
    fun insertBudget(budget: Budgets)

    @Transaction
    @Query("SELECT * FROM budgets")
    fun getAllBudgets() : List<Budgets>

    @Transaction
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateBudget(budget: Budgets)

    @Transaction
    @Query("DELETE FROM budgets WHERE name = :name")
    fun deleteBudgetByName(name: String)

    @Transaction
    @Query("DELETE FROM budgets")
    fun deleteAllEntriesFromBudgets()

    @Transaction
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'budgets'") // Reset auto-increment counter
    fun resetPrimaryKeyAutoIncrementValueBudgets()
}