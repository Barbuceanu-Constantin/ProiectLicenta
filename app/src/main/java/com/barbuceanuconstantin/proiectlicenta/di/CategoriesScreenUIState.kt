package com.barbuceanuconstantin.proiectlicenta.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.barbuceanuconstantin.proiectlicenta.data.Categories

data class CategoriesScreenUIState(
    val showA: Boolean = true,
    val showP: Boolean = false,
    val showD: Boolean = false,
    val categoriesA: List<Categories> = listOf(),
    val categoriesP: List<Categories> = listOf(),
    val categoriesD: List<Categories> = listOf()
)
