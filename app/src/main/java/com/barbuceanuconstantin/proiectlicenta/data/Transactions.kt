package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transactions(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "budget_id")
    val budgetId: Int,

    @ColumnInfo(name = "value")
    var value: Double,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "payee")
    var payee: String
) {
    constructor(categoryName: String,
                budgetId: Int,
                value: Double,
                description: String,
                date: String,
                payee: String): this( id = 0, categoryName = categoryName, budgetId = budgetId,
                                      value = value, description = description, date = date,
                                      payee = payee)
}
