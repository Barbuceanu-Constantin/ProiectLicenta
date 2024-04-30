package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindBudgetTrackerRepository(impl: BudgetTrackerRepositoryImpl) : BudgetTrackerRepository
}