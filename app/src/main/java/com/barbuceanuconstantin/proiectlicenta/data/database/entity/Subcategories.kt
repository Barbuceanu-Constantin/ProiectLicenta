package com.barbuceanuconstantin.proiectlicenta.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategorys(
    @PrimaryKey (autoGenerate = true)
    val idSubcategory: Int,
    val denumire: String?,
    val descriere: String?,
    val idCategorie: Int
)
