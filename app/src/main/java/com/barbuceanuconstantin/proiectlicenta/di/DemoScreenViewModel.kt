package com.barbuceanuconstantin.proiectlicenta.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DemoScreenViewModel @Inject constructor(
    val budgetTrackerRepository: BudgetTrackerRepository
): ViewModel() {
    private val _stateFlow = MutableStateFlow(DemoScreenUIState())

    val stateFlow: StateFlow<DemoScreenUIState>
        get() = _stateFlow.asStateFlow()

    suspend fun runInitCategoryLists() {
        withContext(Dispatchers.IO) {
            try {
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Active"))
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Pasive"))
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Datorii"))
                for (category in subcategorysPredefiniteActive) {
                    budgetTrackerRepository.insertCategory(
                        Categories(
                            mainCategory = "Active",
                            name = category
                        )
                    )
                }
                for (category in subcategorysPredefinitePasive) {
                    budgetTrackerRepository.insertCategory(
                        Categories(
                            mainCategory = "Pasive",
                            name = category
                        )
                    )
                }
                for (category in subcategorysPredefiniteDatorii) {
                    budgetTrackerRepository.insertCategory(
                        Categories(
                            mainCategory = "Datorii",
                            name = category
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e("runInitCategoryLists", "Error inserting categories", e)
            }
        }
    }

    suspend fun onDeleteTables() {
        withContext(Dispatchers.IO) {
            budgetTrackerRepository.deleteAllMainCategories()
            budgetTrackerRepository.deleteAllBudgets()
            budgetTrackerRepository.deleteAllTransactions()
            budgetTrackerRepository.deleteAllCategories()
            budgetTrackerRepository.deletePrimaryKeyIndexMainCategories()
            budgetTrackerRepository.deletePrimaryKeyIndexBudgets()
            budgetTrackerRepository.deletePrimaryKeyIndexTransactions()
            budgetTrackerRepository.deletePrimaryKeyIndexCategories()
            budgetTrackerRepository.resetDb()
        }
    }

    suspend fun updateTablesForDemo() {
        withContext(Dispatchers.IO) {
            budgetTrackerRepository.prepopulateDbForDemo()
        }
    }

    fun getCategoryCount() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = DemoScreenUIState(
                categoryCount = budgetTrackerRepository.getCategoryCount(),
                mainCategoryCount = _stateFlow.value.mainCategoryCount,
                isEmpty = _stateFlow.value.isEmpty,
                isEmptyTransactions = _stateFlow.value.isEmptyTransactions
            )
        }
    }

    fun getMainCategoryCount() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = DemoScreenUIState(
                categoryCount = _stateFlow.value.categoryCount,
                mainCategoryCount = budgetTrackerRepository.getMainCategoryCount(),
                isEmpty = _stateFlow.value.isEmpty,
                isEmptyTransactions = _stateFlow.value.isEmptyTransactions
            )
        }
    }

    fun isEmpty() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = DemoScreenUIState(
                categoryCount = _stateFlow.value.categoryCount,
                mainCategoryCount = _stateFlow.value.mainCategoryCount,
                isEmpty = budgetTrackerRepository.isEmptyMainCategories() &&
                        budgetTrackerRepository.isEmptyBudgets() &&
                        budgetTrackerRepository.isEmptyTransactions() &&
                        budgetTrackerRepository.isEmptyCategories(),
                isEmptyTransactions = _stateFlow.value.isEmptyTransactions
            )
        }
    }

    fun isEmptyTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = DemoScreenUIState(
                categoryCount = _stateFlow.value.categoryCount,
                mainCategoryCount = _stateFlow.value.mainCategoryCount,
                isEmpty = _stateFlow.value.isEmpty,
                isEmptyTransactions = budgetTrackerRepository.isEmptyTransactions()
            )
        }
    }
}