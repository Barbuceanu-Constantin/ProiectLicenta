package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDAO {
    @Insert
    fun insertTransaction(transaction: Transactions)
    @Query("SELECT * FROM transactions")
    fun getAllTransactions() : Flow<List<Transactions>>
    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteTransactionById(id: Int)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateTransaction(transaction: Transactions)
}