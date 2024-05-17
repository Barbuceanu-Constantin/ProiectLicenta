package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndTransactions(
    @Embedded
    var category: Categories,

    @Relation(
        parentColumn = "name",
        entityColumn = "category_name"
    )
    var transactions: List<Transactions>
)
