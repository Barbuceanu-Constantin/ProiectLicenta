package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(CategoriesScreenUIState())
    val stateFlow: StateFlow<CategoriesScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChangedMainScreen(showA: Boolean, showP: Boolean, showD:Boolean) {
        _stateFlow.value = CategoriesScreenUIState(
                                                    showA = showA,
                                                    showP = showP,
                                                    showD = showD,
                                                    categoriesA = _stateFlow.value.categoriesA,
                                                    categoriesP = _stateFlow.value.categoriesP,
                                                    categoriesD = _stateFlow.value.categoriesD,
        )
    }
    fun onStateChangedLists() {
        _stateFlow.value = CategoriesScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            categoriesA = budgetTrackerRepository.getRevenueCategories(),
            categoriesP = budgetTrackerRepository.getSpendingCategories(),
            categoriesD = budgetTrackerRepository.getDebtCategories(),
        )
    }
    fun onDeleteByName(name: String, main: String) {
        budgetTrackerRepository.deleteCategoryByNameAndPrincipal(name, main)
        onStateChangedLists()
    }
}