package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PrincipalScreenUIState(
    val selectedIndex: Int = -1,
    val revenuesSum: Double = 0.0,
    val expensesSum: Double = 0.0,
    val debtSum: Double = 0.0
)
