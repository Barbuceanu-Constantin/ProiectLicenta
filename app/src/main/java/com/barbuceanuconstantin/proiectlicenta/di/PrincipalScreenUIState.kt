package com.barbuceanuconstantin.proiectlicenta.di

data class PrincipalScreenUIState(
    val selectedIndex: Int = -1,
    val revenuesSum: Double = 0.0,
    val expensesSum: Double = 0.0,
    val debtSum: Double = 0.0
)
