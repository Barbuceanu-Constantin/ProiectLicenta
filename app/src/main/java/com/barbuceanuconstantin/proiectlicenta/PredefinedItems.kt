package com.barbuceanuconstantin.proiectlicenta

import androidx.compose.runtime.mutableStateListOf
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction

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

var lTrA = mutableStateListOf<Transaction>()
var lTrP = mutableStateListOf<Transaction>()
var lTrD = mutableStateListOf<Transaction>()

var lBudgets = mutableStateListOf<Budget>()
fun InitHardcodedBudgets() {
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
}
fun initHardcodedTransactions() {
    lTrA.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Salariu"))

    lTrP.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Mancare"))

    lTrD.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), valuta = "EURO", data = "", descriere = "", payee = "", subcategory = "Credit1"))
}
