package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TransactionsScreenUIState(
    val showA: Boolean = false,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val buttons: Boolean = false
)
