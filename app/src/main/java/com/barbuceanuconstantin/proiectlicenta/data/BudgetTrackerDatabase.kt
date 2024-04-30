package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Categories::class, MainCategories::class], version = 1, exportSchema = false)
abstract class BudgetTrackerDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDAO
    abstract fun mainCategoryDao(): MainCategoryDAO
}