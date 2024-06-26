package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories

data class EditCategoryScreenUIState(
    val showA: Boolean = true,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val filledText: String = "",
    val readyToGo: Boolean = false,
    val readyToUpdate: Boolean = false,
    val alertDialog: Boolean = false,
    val alertAlreadyExistDialog: Boolean = false,
    val category: Categories? = null,
    val revenueCategories: List<Categories> = listOf(),
    val expensesCategories: List<Categories> = listOf(),
    val debtCategories: List<Categories> = listOf()
)
