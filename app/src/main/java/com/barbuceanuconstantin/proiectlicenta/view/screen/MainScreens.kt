package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.initHardcodedTransactions
import com.barbuceanuconstantin.proiectlicenta.lBudgets
import com.barbuceanuconstantin.proiectlicenta.lTrA
import com.barbuceanuconstantin.proiectlicenta.lTrD
import com.barbuceanuconstantin.proiectlicenta.lTrP
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var lTranzactiiActive: SnapshotStateList<Tranzactie> = lTrA
private var lTranzactiiPasive: SnapshotStateList<Tranzactie> = lTrP
private var lTranzactiiDatorii: SnapshotStateList<Tranzactie> = lTrD
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
    TransactionsComposableScreen(lTranzactiiActive, lTranzactiiPasive, lTranzactiiDatorii)
}
@Composable
fun Screen2() {
    val showA = mutableStateOf(true)
    val showP = mutableStateOf(true)
    val showD = mutableStateOf(true)
    val addButton = mutableStateOf(false)

    CategoriesComposableScreen(showA, showP, showD, addButton, listSubcategoriesRevenue, listSubcategoriesExpenses, listSubcategoriesDebts)
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen4() {
    val fab = mutableStateOf(false)

    FixedBudgetsComposableScreen(fab, lFixedBudgets)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen6() {
    val showDaily = mutableStateOf(true)
    val showWeekly = mutableStateOf(true)
    val showMonthly = mutableStateOf(true)

    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val date by remember { mutableStateOf(formattedDate) }
    val dateMutable: MutableState<String> = mutableStateOf(date)

    val monthMutable : MutableState<String> = mutableStateOf("")

    initHardcodedTransactions()

    BudgetSummaryComposableScreen(daily = showDaily, weekly = showWeekly, monthly = showMonthly,
                                    lTrA = lTranzactiiActive,
                                    lTrP = lTranzactiiPasive,
                                    dateMutable = dateMutable,
                                    monthMutable = monthMutable
    )
}

@Composable
fun Screen7() {
    val dateMutable: MutableState<String> = mutableStateOf("")

    CalendarComposableScreen(dateMutable)
}