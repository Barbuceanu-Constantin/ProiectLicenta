package com.barbuceanuconstantin.proiectlicenta.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategories(
    @PrimaryKey (autoGenerate = true)
    val idSubcategorie: Int,
    val denumire: String?,
    val descriere: String?,
    val idCategorie: Int
)
