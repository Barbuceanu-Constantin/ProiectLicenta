package com.barbuceanuconstantin.proiectlicenta.di

import androidx.lifecycle.ViewModel
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.repository.BudgetTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditTransactionScreenViewModel @Inject constructor(val budgetTrackerRepository: BudgetTrackerRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow(EditTransactionScreenUIState())
    val stateFlow: StateFlow<EditTransactionScreenUIState>
        get() = _stateFlow.asStateFlow()
    fun onAddTransaction(transaction: Transactions) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = transaction
        )
    }
    fun onStateChanged(showA: Boolean, showP: Boolean, showD: Boolean) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = showA,
            showP = showP,
            showD = showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
    fun onUpdateCategory(category: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
    fun onUpdateDescription(description: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
    fun onUpdatePayee(payee: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = payee,
            date = _stateFlow.value.date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
    fun onUpdateDate(date: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = date,
            valueSum = _stateFlow.value.valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
    fun onUpdateValueSum(valueSum: String) {
        _stateFlow.value = EditTransactionScreenUIState(
            showA = _stateFlow.value.showA,
            showP = _stateFlow.value.showP,
            showD = _stateFlow.value.showD,
            category = _stateFlow.value.category,
            description = _stateFlow.value.description,
            payee = _stateFlow.value.payee,
            date = _stateFlow.value.date,
            valueSum = valueSum,
            transaction = _stateFlow.value.transaction
        )
    }
}