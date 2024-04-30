package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    var transactionsScreenUIState = mutableStateOf(TransactionsScreenUIState()).value
}