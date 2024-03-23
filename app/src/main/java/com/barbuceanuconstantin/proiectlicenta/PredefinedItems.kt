package com.barbuceanuconstantin.proiectlicenta

import androidx.compose.runtime.mutableStateListOf
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie

var subcategorysPredefiniteActive = mutableStateListOf(
    "Salariu",
    "Pensie",
    "Bursa",
    "Treburi marunte"
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

var subcategorysPredefinitePasive = mutableStateListOf(
    "Mancare",
    "Sanatate",
    "Divertisment",
    "Haine",
    "Reparatii casa",
    "Reparatii masina",
    "Abonament metrou",
    "Abonament transport suprafata",
    "Tips"
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

var subcategorysPredefiniteDatorii = mutableStateListOf(
    "Credit1",
    "Credit2",
    "Credit3",
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

var lTrA = mutableStateListOf<Tranzactie>()
var lTrP = mutableStateListOf<Tranzactie>()
var lTrD = mutableStateListOf<Tranzactie>()

var lBudgets = mutableStateListOf<Budget>()
