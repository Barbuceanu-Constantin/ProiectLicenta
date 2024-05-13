package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class EditBudgetScreenUIState(
    val formattedDate: Date =   SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).
                                parse(LocalDateTime.now().
                                format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))!!,
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
