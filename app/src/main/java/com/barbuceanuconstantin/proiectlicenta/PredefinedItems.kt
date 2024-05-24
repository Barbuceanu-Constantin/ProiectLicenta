package com.barbuceanuconstantin.proiectlicenta

var subcategorysPredefiniteActive = mutableListOf(
    "Salariu",
    "Bursa",
    "Bani parinti",
    "Chirie",
    "Treburi marunte",
    "Vanzare legume"
)

var subcategorysPredefinitePasive = mutableListOf(
    "Mancare",
    "Sanatate",
    "Divertisment",
    "Haine",
    "Reparatii casa",
    "Reparatii masina",
    "Abonament metrou",
    "Abonament transport suprafata",
    "Tips",
    "Benzina",
    "Factura curent",
    "Factura apa",
    "Factura gaz",
    "Internet"
)

var subcategorysPredefiniteDatorii = mutableListOf(
    "Leasing auto"
)

const val showAIndex = 0
const val showPIndex = 1
const val showDIndex = 2
const val returnToTransactionIndex = 3
const val returnToBudgetSummaryIndex = 4
const val returnToCalendarIndex = 5

enum class ListCategories {
    REVENUES, EXPENSES, DEBT
}
