package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditCategoryScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(EditCategoryScreenUIState())
    val stateFlow: StateFlow<EditCategoryScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onStateChanged(showA: Boolean, showP: Boolean, showD: Boolean) {
        _stateFlow.value = EditCategoryScreenUIState(
                                                        showA = showA,
                                                        showP = showP,
                                                        showD = showD,
                                                        category = _stateFlow.value.category,
                                                        filledText = _stateFlow.value.filledText,
        )
    }
    fun onAddCategory(category: Categories) {
        _stateFlow.value = EditCategoryScreenUIState(
                                                        showA = _stateFlow.value.showA,
                                                        showP = _stateFlow.value.showP,
                                                        showD = _stateFlow.value.showD,
                                                        category = category,
                                                        filledText = _stateFlow.value.filledText
        )
    }
    fun onStateChangedFilledText(filledText: String) {
        _stateFlow.value = EditCategoryScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            filledText = filledText,
            category = _stateFlow.value.category
        )
    }
}