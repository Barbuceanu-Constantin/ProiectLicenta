package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TransactionsScreenUIState(
    val showA: MutableState<Boolean> =  mutableStateOf(false),
    val showP: MutableState<Boolean> =  mutableStateOf(false),
    val showD: MutableState<Boolean> = mutableStateOf(false),
    val buttons: MutableState<Boolean> = mutableStateOf(false),
)
