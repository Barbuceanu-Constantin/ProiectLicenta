package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PrincipalScreenUIState(
    var selectedIndex: MutableState<Int> = mutableStateOf(-1)
)
