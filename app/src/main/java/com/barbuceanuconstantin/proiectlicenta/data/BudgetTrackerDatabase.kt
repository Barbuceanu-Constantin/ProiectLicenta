package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
            entities = [Categories::class, MainCategories::class, Transactions::class, Budgets::class],
            version = 1, exportSchema = false
)
abstract class BudgetTrackerDatabase: RoomDatabase() {
    abstract fun mainCategoryDao(): MainCategoryDAO
    abstract fun categoryDao(): CategoryDAO
    abstract fun transactionDao(): TransactionsDAO
    abstract fun budgetDao(): BudgetsDAO
}