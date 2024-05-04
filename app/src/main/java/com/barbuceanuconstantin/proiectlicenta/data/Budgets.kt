package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budgets(
    @PrimaryKey val id: Int,
    val category: String,
    val name: String,
    var upperTreshold: Double,
    var startDate: String,
    var endDate: String,
)