package com.barbuceanuconstantin.proiectlicenta.di

import android.content.Context
import androidx.room.Room
import com.barbuceanuconstantin.proiectlicenta.data.BudgetTrackerDatabase
import com.barbuceanuconstantin.proiectlicenta.data.dao.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.DatabaseDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.TransactionsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDatabaseDao(budgetTrackerDatabase: BudgetTrackerDatabase): DatabaseDAO {
        return  budgetTrackerDatabase.databaseDAO()
    }

    @Provides
    fun provideMainCategoryDao(budgetTrackerDatabase: BudgetTrackerDatabase): MainCategoryDAO {
        return  budgetTrackerDatabase.mainCategoryDao()
    }
    @Provides
    fun provideCategoryDao(budgetTrackerDatabase: BudgetTrackerDatabase): CategoryDAO {
        return  budgetTrackerDatabase.categoryDao()
    }
    @Provides
    fun provideTransactionDao(budgetTrackerDatabase: BudgetTrackerDatabase): TransactionsDAO {
        return  budgetTrackerDatabase.transactionsDao()
    }
    @Provides
    fun provideBudgetDao(budgetTrackerDatabase: BudgetTrackerDatabase): BudgetsDAO {
        return budgetTrackerDatabase.budgetsDao()
    }
    @Provides
    @Singleton
    fun provideBudgetTrackerDatabase(@ApplicationContext context: Context): BudgetTrackerDatabase {
        return Room.databaseBuilder(
                                    context = context,
                                    BudgetTrackerDatabase::class.java,
                                    "budget_tracker_database"
                                )
                    .fallbackToDestructiveMigration() //.allowMainThreadQueries() //.createFromAsset("databases/transactions.db")
                    .build()
    }
}