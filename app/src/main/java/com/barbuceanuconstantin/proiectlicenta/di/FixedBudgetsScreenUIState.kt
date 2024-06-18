package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Budgets

data class FixedBudgetsScreenUIState(
    val buttons: Boolean = false,
    val budgets: List<Budgets> = listOf(),
    val revenues: Double = 0.0
)
