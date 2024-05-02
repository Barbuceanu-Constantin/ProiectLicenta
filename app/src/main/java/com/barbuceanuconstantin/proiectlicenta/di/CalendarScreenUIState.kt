package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDate

data class CalendarScreenUIState(
    val date: String = LocalDate.now().toString(),
    val incomes: Boolean = false,
    val expenses: Boolean = false,
    val buttons: Boolean = false,
)
