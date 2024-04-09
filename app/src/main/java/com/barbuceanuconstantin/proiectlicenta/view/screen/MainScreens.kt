package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
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
    Subcategory(name = it.key.toString(), items = it.value.toMutableStateList())
}.toMutableStateList()
private var listSubcategoriesExpenses = subcategorysPredefinitePasive.map {
    Subcategory(name = it.key.toString(), items = it.value.toMutableStateList())
}.toMutableStateList()
private var listSubcategoriesDebts = subcategorysPredefiniteDatorii.map {
    Subcategory(name = it.key.toString(), items = it.value.toMutableStateList())
}.toMutableStateList()
@Composable
fun Screen0() {
    PrincipalComposableScreen()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen1() {
    initHardcodedTransactions()
    TransactionsComposableScreen(lTrA, lTrP, lTrD)
}
@Composable
fun Screen2() {
    CategoriesComposableScreen(listSubcategoriesRevenue, listSubcategoriesExpenses, listSubcategoriesDebts)
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen4() {
    InitHardcodedBudgets()
    FixedBudgetsComposableScreen(lFixedBudgets)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen6() {
    initHardcodedTransactions()
        BudgetSummaryComposableScreen(lTrA = lTrA, lTrP = lTrP)
}

@Composable
fun Screen7() {
    CalendarComposableScreen()
}