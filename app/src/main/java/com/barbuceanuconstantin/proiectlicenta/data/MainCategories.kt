package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainCategories(
    @PrimaryKey val id: Int,
    var name: String
)
