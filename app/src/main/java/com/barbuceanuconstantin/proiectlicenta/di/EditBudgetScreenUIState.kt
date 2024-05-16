package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class EditBudgetScreenUIState(
    val formattedDate: String =   LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val date1: String = formattedDate,
    val date2: String = formattedDate,
    val openWarningDialog: Boolean = false,
    val filledText: String = "",
    val valueSum: String = "",
    val category: String = "",
    val idWarningString: Int = -1,
    val readyToGo: Boolean = false,
    val alertDialog: Boolean = false,
    val expenseCategoriesList: List<Categories> = listOf(),
    val budget: Budgets? = null
)
