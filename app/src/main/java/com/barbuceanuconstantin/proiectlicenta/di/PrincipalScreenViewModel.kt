package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrincipalScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    val principalScreenUIState = PrincipalScreenUIState()
}