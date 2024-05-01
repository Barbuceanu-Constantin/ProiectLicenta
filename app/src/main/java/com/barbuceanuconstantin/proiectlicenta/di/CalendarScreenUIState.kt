package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDate

data class CalendarScreenUIState(
    val dateMutable: MutableState<String> = mutableStateOf(LocalDate.now().toString()),
    val incomes: MutableState<Boolean> = mutableStateOf(false),
    val expenses: MutableState<Boolean> = mutableStateOf(false),
    val buttons: MutableState<Boolean> = mutableStateOf(false),
)
