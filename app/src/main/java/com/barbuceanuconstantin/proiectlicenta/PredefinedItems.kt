package com.barbuceanuconstantin.proiectlicenta

import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie

var subcategorysPredefiniteActive = mutableListOf(
    "Salariu",
    "Pensie",
    "Bursa",
    "Treburi marunte"
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

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
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

var subcategorysPredefiniteDatorii = mutableListOf(
    "Credit1",
    "Credit2",
    "Credit3",
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

var lTrA = mutableListOf<Tranzactie>()
var lTrP = mutableListOf<Tranzactie>()
var lTrD = mutableListOf<Tranzactie>()