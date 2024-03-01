package com.example.proiectlicenta

val subcategoriiPredefiniteActive = mutableListOf(
    "Salariu",
    "Pensie",
    "Bursa",
    "Treburi marunte"
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()

val subcategoriiPredefinitePasive = mutableListOf(
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

val subcategoriiPredefiniteDatorii = mutableListOf(
    "Credit1",
    "Credit2",
    "Credit3",
).groupByTo(mutableMapOf()) { it.first() }.toSortedMap()