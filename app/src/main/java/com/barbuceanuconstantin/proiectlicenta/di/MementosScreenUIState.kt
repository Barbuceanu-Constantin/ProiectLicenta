package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MementosScreenUIState(
    val nothing: MutableState<Boolean> = mutableStateOf(true)
)