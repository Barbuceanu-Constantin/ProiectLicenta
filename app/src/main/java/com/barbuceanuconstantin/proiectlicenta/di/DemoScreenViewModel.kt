package com.barbuceanuconstantin.proiectlicenta.di

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import com.barbuceanuconstantin.proiectlicenta.revenuesPredefinedSubcategories
import com.barbuceanuconstantin.proiectlicenta.DebtPredefinedSubcategories
import com.barbuceanuconstantin.proiectlicenta.expensesPredefinedSubcategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DemoScreenViewModel @Inject constructor(
    val budgetTrackerRepository: BudgetTrackerRepository
): ViewModel() {
    private val _stateFlow = MutableStateFlow(DemoScreenUIState())

    val stateFlow: StateFlow<DemoScreenUIState>
        get() = _stateFlow.asStateFlow()

    fun runInitCategoryLists() {
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Active"))
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Pasive"))
                budgetTrackerRepository.insertMainCategory(MainCategories(name = "Datorii"))
                for (category in revenuesPredefinedSubcategories) {
                    budgetTrackerRepository.insertCategory(
                        Categories(
                            mainCategory = "Active",
                            name = category
                        )
                    )
                }
                for (category in expensesPredefinedSubcategories) {
                    budgetTrackerRepository.insertCategory(
                        Categories(
                            mainCategory = "Pasive",
                            name = category
                        )
                    )
                }
                for (category in DebtPredefinedSubcategories) {
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

        runBlocking {
            job.join()
        }
    }

    fun onDeleteTables() {
        val job = viewModelScope.launch(Dispatchers.IO) {
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

        runBlocking {
            job.join()
        }
    }

    fun updateTablesForDemo() {
        val job = viewModelScope.launch(Dispatchers.IO) {
            budgetTrackerRepository.prepopulateDbForDemo()
        }

        runBlocking {
            job.join()
        }
    }

    fun getMainCategoryCount(): Int {
        var size = 0

        val job = viewModelScope.launch(Dispatchers.IO) {
            size = budgetTrackerRepository.getAllMainCategories().size
        }

        runBlocking {
            job.join()
        }

        return size
    }
}