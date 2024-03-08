package com.barbuceanuconstantin.proiectlicenta.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories(
    @PrimaryKey (autoGenerate = true)
    val idCategorie: Int,
    val denumire: String?
)
