package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CategoriesScreenUIState())
    val stateFlow: StateFlow<CategoriesScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.value = CategoriesScreenUIState(showA, showP, showD)
    }

    fun onStateChangedLists() {
        _stateFlow.value = CategoriesScreenUIState(
                                                    showA = _stateFlow.value.showA,
                                                    showP = _stateFlow.value.showP,
                                                    showD = _stateFlow.value.showD,
                                                    categoriesA = _stateFlow.value.categoriesA,
                                                    categoriesP = _stateFlow.value.categoriesP,
                                                    categoriesD = _stateFlow.value.categoriesD,
                                                )
    }
}