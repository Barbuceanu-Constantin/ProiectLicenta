package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CategoriesScreenUIState(showA = false, showP = false, showD = false))
    val stateFlow: StateFlow<CategoriesScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(showA = showA, showP = showP, showD = showD)
        }
    }
}