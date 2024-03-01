package com.example.proiectlicenta

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategorii(
    @PrimaryKey (autoGenerate = true)
    val idSubcategorie: Int,
    val denumire: String?,
    val descriere: String?,
    val idCategorie: Int
)
