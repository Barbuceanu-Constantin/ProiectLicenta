package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TransactionsDAO {
    @Insert
    fun insertTransaction(transaction: Transactions)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions() : Flow<List<Transactions>>
}