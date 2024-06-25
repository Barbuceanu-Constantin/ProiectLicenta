package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (
            tableName = "mainCategories"
)
data class MainCategories(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
) {
    constructor(name: String): this(id = 0, name = name)
}
