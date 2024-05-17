package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "categories"
)
data class Categories(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "main_category")
    val mainCategory: String,

    @ColumnInfo(name = "name")
    var name: String,
) {
    constructor(mainCategory: String, name: String): this(id = 0, mainCategory = mainCategory, name = name)
}
