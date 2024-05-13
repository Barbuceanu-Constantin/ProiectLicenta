package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories

data class FixedBudgetsScreenUIState(
    val buttons: Boolean = false,
    val budgets: List<Budgets> = listOf()
)
