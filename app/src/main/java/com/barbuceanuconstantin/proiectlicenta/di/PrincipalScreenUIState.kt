package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.R

data class PrincipalScreenUIState(
    var selectedIndex: MutableState<Int> =  mutableStateOf(-1)
)
