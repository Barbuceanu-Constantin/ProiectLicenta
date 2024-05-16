package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = Categories::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("category_name"),
        onDelete = CASCADE
    )]
)
data class Transactions(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "category_name", index = true)
    var categoryName: String,

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
                value: Double,
                description: String,
                date: String,
                payee: String): this( id = 0, categoryName = categoryName,
                                      value = value, description = description, date = date,
                                      payee = payee)
}
