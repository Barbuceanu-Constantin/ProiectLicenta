package com.barbuceanuconstantin.proiectlicenta.data

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndBudgets(
    @Embedded
    var category: Categories,

    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    var budgets: List<Budgets>
)
