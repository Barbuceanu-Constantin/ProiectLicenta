package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = Categories::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("category_id"),
        onUpdate = CASCADE
    )]
)
data class Transactions(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "category_id", index = true)
    var categoryId: Int,

    @ColumnInfo(name = "value")
    var value: Double,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "payee")
    var payee: String
) {
    constructor(categoryId: Int,
                value: Double,
                description: String,
                date: Date,
                payee: String): this( id = 0, categoryId = categoryId,
                                      value = value, description = description, date = date,
                                      payee = payee)
}
