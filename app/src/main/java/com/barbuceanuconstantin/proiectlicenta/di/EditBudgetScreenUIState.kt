package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date

data class EditBudgetScreenUIState(
    val formattedDate: Date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
    val date1: Date = formattedDate,
    val date2: Date = formattedDate,
    val openWarningDialog: Boolean = false,
    val filledText: String = "",
    val valueSum: String = "",
    val category: String = "",
    val idWarningString: Int = -1,
    val readyToGo: Boolean = false,
    val alertDialog: Boolean = false,
    val budget: Budgets? = null
)
