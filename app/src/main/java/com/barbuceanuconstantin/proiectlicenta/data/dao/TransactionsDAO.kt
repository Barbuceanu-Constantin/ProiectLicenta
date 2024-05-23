package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionsDAO {
    @Insert
    fun insertTransaction(transaction: Transactions)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions() : Flow<List<Transactions>>

    @Transaction
    @Query("SELECT * FROM categories where main_category == :mainCategory order by name ASC")
    fun getTransactionsCategoryListQuery(mainCategory: String) : List<CategoryAndTransactions>

    fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions> {
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val sortedTransactions = categoryAndTransactions.transactions.sortedByDescending { it.date }
            categoryAndTransactions.transactions = sortedTransactions
        }

        return list
    }

    @Transaction
    fun getTransactionsByDate(currentDate: Date, mainCategory: String): List<CategoryAndTransactions>{
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val filteredTransactions = categoryAndTransactions.transactions.filter { it.date == currentDate }
                                                                           .sortedByDescending { it.date }
            categoryAndTransactions.transactions = filteredTransactions
        }

        return list
    }

    @Transaction
    fun getTransactionsByInterval(startDate: Date, endDate: Date, mainCategory: String): List<CategoryAndTransactions>{
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val filteredTransactions = categoryAndTransactions.transactions .filter { it.date in startDate..endDate }
                                                                            .sortedByDescending { it.date }
            categoryAndTransactions.transactions = filteredTransactions
        }

        return list
    }

    @Transaction
    @Query( "SELECT SUM(Transactions.value)" +
            "FROM Categories " +
            "LEFT JOIN Transactions ON Categories.id = Transactions.category_id " +
            "WHERE Categories.main_category = :mainCategory " +
            "AND Transactions.date = :currentDate")
    fun getTransactionsSumByDay(currentDate: Date, mainCategory: String): Double

    @Transaction
    @Query( "SELECT SUM(Transactions.value) " +
            "FROM Categories " +
            "LEFT JOIN Transactions ON Categories.id = Transactions.category_id " +
            "WHERE Categories.main_category = :mainCategory")
    fun getTransactionsCategoryListTotalSum(mainCategory: String): Double

    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteTransactionById(id: Int)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateTransaction(transaction: Transactions)
}