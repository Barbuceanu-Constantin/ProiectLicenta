package com.barbuceanuconstantin.proiectlicenta

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList

var subcategorysPredefiniteActive = mutableListOf(
    "Salariu",
    "Pensie",
    "Bursa",
    "Treburi marunte"
).sorted().toMutableStateList()

var subcategorysPredefinitePasive = mutableListOf(
    "Mancare",
    "Sanatate",
    "Divertisment",
    "Haine",
    "Reparatii casa",
    "Reparatii masina",
    "Abonament metrou",
    "Abonament transport suprafata",
    "Tips"
).sorted().toMutableStateList()

var subcategorysPredefiniteDatorii = mutableListOf(
    "Credit1",
    "Credit2",
    "Credit3",
).sorted().toMutableStateList()

const val showAIndex = 0
const val showPIndex = 1
const val showDIndex = 2
const val returnToTransactionIndex = 3
const val returnToBudgetSummaryIndex = 4
