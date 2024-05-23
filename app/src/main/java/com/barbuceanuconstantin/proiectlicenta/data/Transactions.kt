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

    @ColumnInfo(name = "payee")
    var payee: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "value")
    var value: Double,

    @ColumnInfo(name = "category_id", index = true)
    var categoryId: Int,

    @ColumnInfo(name = "date")
    var date: Date,
) {
    constructor(payee: String,
                description: String,
                value: Double,
                categoryId: Int,
                date: Date,
                ): this( id = 0, categoryId = categoryId,
                                      value = value, description = description, date = date,
                                      payee = payee)
}
