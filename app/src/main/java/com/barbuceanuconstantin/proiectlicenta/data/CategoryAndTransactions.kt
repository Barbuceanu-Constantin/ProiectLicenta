package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndTransactions(
    @Embedded
    var category: Categories,

    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    var transactions: List<Transactions>
)
