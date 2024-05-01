package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class BudgetSummaryScreenUIState(
    val daily: MutableState<Boolean> = mutableStateOf(true),
    val weekly: MutableState<Boolean> = mutableStateOf(true),
    val monthly: MutableState<Boolean> = mutableStateOf(true),
    val date: MutableState<String> = mutableStateOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
    val dateMutable: MutableState<String> = mutableStateOf(date.value),
    val monthMutable : MutableState<String> = mutableStateOf(""),
    val buttons: MutableState<Boolean> = mutableStateOf(false),
    val dateButton: MutableState<Boolean> = mutableStateOf(false)
)
