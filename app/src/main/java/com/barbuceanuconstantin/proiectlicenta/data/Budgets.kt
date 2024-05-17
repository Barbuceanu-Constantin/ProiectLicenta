package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
        tableName = "budgets",
        indices = [Index(value = ["name"], unique = true)],
        foreignKeys = [ForeignKey(
            entity = Categories::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Budgets(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "category_id", index = true)
    var categoryId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "upper_threshold")
    var upperThreshold: Double,

    @ColumnInfo(name = "start_date_string")
    var startDate: Date,

    @ColumnInfo(name = "end_date_string")
    var endDate: Date,
) {
    constructor(categoryId: Int,
                name: String,
                upperThreshold: Double,
                startDate: Date,
                endDate: Date): this(   id = 0, categoryId = categoryId, name = name,
                                        upperThreshold = upperThreshold, startDate = startDate,
                                        endDate = endDate
                )
}