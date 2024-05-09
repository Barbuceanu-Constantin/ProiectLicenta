package com.barbuceanuconstantin.proiectlicenta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.barbuceanuconstantin.proiectlicenta.data.dao.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.TransactionsDAO

@Database(
            entities = [Categories::class, MainCategories::class, Transactions::class, Budgets::class],
            version = 1, exportSchema = false
)
abstract class BudgetTrackerDatabase: RoomDatabase() {
    abstract fun mainCategoryDao(): MainCategoryDAO
    abstract fun categoryDao(): CategoryDAO
    abstract fun transactionDao(): TransactionsDAO
    abstract fun budgetDao(): BudgetsDAO

    companion object {
        @Volatile
        private var INSTANCE: BudgetTrackerDatabase? = null

        fun getInstance(context: Context): BudgetTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetTrackerDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}