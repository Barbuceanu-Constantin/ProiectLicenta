package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class BudgetSummaryScreenUIState(
    val daily: Boolean = true,
    val weekly: Boolean = true,
    val monthly: Boolean = true,
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val month: String = "",
    val buttons: Boolean = false,
    val dateButton: Boolean = false
)
