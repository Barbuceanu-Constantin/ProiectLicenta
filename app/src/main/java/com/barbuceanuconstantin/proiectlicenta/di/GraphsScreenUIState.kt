package com.barbuceanuconstantin.proiectlicenta.di

data class GraphsScreenUIState(
    val graphChoice: String = "",
    val chartType: String = "",
    val revenuesSum: Double = 0.0,
    val expensesSum: Double = 0.0,
    val debtSum: Double = 0.0,
    val month: String = "",
    val monthComparisonChartType: String = ""
)
