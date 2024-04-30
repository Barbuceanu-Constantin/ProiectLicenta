package com.barbuceanuconstantin.proiectlicenta.di

import android.content.Context
import androidx.room.Room
import com.barbuceanuconstantin.proiectlicenta.data.BudgetTrackerDatabase
import com.barbuceanuconstantin.proiectlicenta.data.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.MainCategoryDAO
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
    fun provideCategoryDao(budgetTrackerDatabase: BudgetTrackerDatabase): CategoryDAO {
        return  budgetTrackerDatabase.categoryDao()
    }
    @Provides
    fun provideMainCategoryDao(budgetTrackerDatabase: BudgetTrackerDatabase): MainCategoryDAO {
        return  budgetTrackerDatabase.mainCategoryDao()
    }

    @Provides
    @Singleton
    fun provideBudgetTrackerDatabase(@ApplicationContext context: Context): BudgetTrackerDatabase {
        return Room.databaseBuilder(
                                    context = context,
                                    BudgetTrackerDatabase::class.java,
                                    "budget_tracker_database"
                                )
                    .fallbackToDestructiveMigration()
                    .build()
    }
}