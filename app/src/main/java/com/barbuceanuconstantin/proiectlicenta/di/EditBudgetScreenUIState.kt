package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class EditBudgetScreenUIState(
    val formattedDate: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val date1: String = formattedDate,
    val date2: String = formattedDate,
    val openWarningDialog: Boolean = false,
    val filledText: String = "",
    val valueSum: String = "",
    val category: String = "",
    val idWarningString: Int = -1,
    val budget: Budgets? = null
)
