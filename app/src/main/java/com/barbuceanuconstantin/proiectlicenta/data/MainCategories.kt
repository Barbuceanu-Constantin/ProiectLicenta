package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (
            tableName = "mainCategories"
)
data class MainCategories(
    @PrimaryKey val id: Int,
    val name: String
)
