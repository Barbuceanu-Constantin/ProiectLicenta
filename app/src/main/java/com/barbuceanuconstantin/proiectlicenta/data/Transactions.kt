package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transactions(
    @PrimaryKey val id: Int,
    val category: String,
    val budgetId: Int,
    var value: Float,
    var description: String,
    var date: String,
    var payee: String,
)
