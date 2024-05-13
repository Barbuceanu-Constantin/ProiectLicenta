package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Database
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
    abstract fun transactionsDao(): TransactionsDAO
    abstract fun budgetsDao(): BudgetsDAO
}