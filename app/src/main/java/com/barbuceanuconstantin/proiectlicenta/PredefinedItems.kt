package com.barbuceanuconstantin.proiectlicenta

var subcategorysPredefiniteActive = mutableListOf(
    "Salariu",
    "Bursa",
    "Treburi marunte"
).sorted()

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
).sorted()

var subcategorysPredefiniteDatorii = mutableListOf(
    "Credit1",
    "Credit2",
    "Credit3",
).sorted()

const val showAIndex = 0
const val showPIndex = 1
const val showDIndex = 2
const val returnToTransactionIndex = 3
const val returnToBudgetSummaryIndex = 4
const val returnToCalendarIndex = 5
