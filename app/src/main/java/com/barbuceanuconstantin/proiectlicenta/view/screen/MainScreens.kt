package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.initHardcodedTransactions
import com.barbuceanuconstantin.proiectlicenta.lBudgets
import com.barbuceanuconstantin.proiectlicenta.lTrA
import com.barbuceanuconstantin.proiectlicenta.lTrD
import com.barbuceanuconstantin.proiectlicenta.lTrP
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.InitHardcodedBudgets

private var lFixedBudgets: SnapshotStateList<Budget> = lBudgets

private var listSubcategoriesRevenue = subcategorysPredefiniteActive.map {
    Category(name = it)
}.toMutableStateList()
private var listSubcategoriesExpenses = subcategorysPredefinitePasive.map {
    Category(name = it)
}.toMutableStateList()
private var listSubcategoriesDebts = subcategorysPredefiniteDatorii.map {
    Category(name = it)
}.toMutableStateList()
@Composable
fun Screen0() {
    PrincipalComposableScreen()
}
@Composable
fun Screen1() {
    initHardcodedTransactions()
    TransactionsComposableScreen(lTrA, lTrP, lTrD)
}
@Composable
fun Screen2() {
    CategoriesComposableScreen(listSubcategoriesRevenue, listSubcategoriesExpenses, listSubcategoriesDebts)
}
@Composable
fun Screen4() {
    InitHardcodedBudgets()
    FixedBudgetsComposableScreen(lFixedBudgets)
}
@Composable
fun Screen6() {
    initHardcodedTransactions()
    BudgetSummaryComposableScreen(lTrA = lTrA, lTrP = lTrP)
}
@Composable
fun Screen7() {
    initHardcodedTransactions()
    CalendarComposableScreen(lTrA = lTrA, lTrP = lTrP)
}