package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
        tableName = "budgets",
        indices = [Index(value = ["name"], unique = true)]
)
data class Budgets(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "category_name")
    var categoryName: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "upper_threshold")
    var upperThreshold: Double,

    @ColumnInfo(name = "start_date_string")
    var startDate: String,

    @ColumnInfo(name = "end_date_string")
    var endDate: String,
) {
    constructor(categoryName: String,
                name: String,
                upperThreshold: Double,
                startDate: String,
                endDate: String): this(   id = 0, categoryName = categoryName, name = name,
                                        upperThreshold = upperThreshold, startDate = startDate,
                                        endDate = endDate
                )
}