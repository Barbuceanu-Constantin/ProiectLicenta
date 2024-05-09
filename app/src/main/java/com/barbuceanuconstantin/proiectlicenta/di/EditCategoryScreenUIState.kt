package com.barbuceanuconstantin.proiectlicenta.di

import com.barbuceanuconstantin.proiectlicenta.data.Categories

data class EditCategoryScreenUIState(
    val showA: Boolean = false,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val filledText: String = "",
    val readyToInsert: Boolean = false,
    val category: Categories? = null
)
