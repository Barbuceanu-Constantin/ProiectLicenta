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
fun screen0() {
    var sumRevenue: Float = 0f;
    var sumExpenses: Float = 0f;
    var sumDebt: Float = 0f;
    principalComposableScreen(sumRevenue, sumExpenses, sumDebt)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun screen1() {
    val showA = mutableStateOf(true)
    val showP = mutableStateOf(true)
    val showD = mutableStateOf(true)
    val addButton = mutableStateOf(false)
    val index = mutableIntStateOf(-1)
    val sem = mutableIntStateOf(-1)
    val updateTransactionButton = mutableStateOf(false)

    transactionsComposableScreen(showA, showP, showD, addButton, lTranzactiiActive, lTranzactiiPasive, lTranzactiiDatorii, index, sem, updateTransactionButton)
}
@Composable
fun screen2() {
    val showA = mutableStateOf(true)
    val showP = mutableStateOf(true)
    val showD = mutableStateOf(true)
    val addButton = mutableStateOf(false)

    categoriesComposableScreen(showA, showP, showD, addButton, listSubcategoriesRevenue, listSubcategoriesExpenses, listSubcategoriesDebts)
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun screen4() {
    val fab = mutableStateOf(false)

    fixedBudgetsComposableScreen(fab, lFixedBudgets)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun screen6() {
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

    budgetSummaryComposableScreen(daily = showDaily, weekly = showWeekly, monthly = showMonthly,
                                    lTrA = lTranzactiiActive,
                                    lTrP = lTranzactiiPasive,
                                    dateMutable = dateMutable,
                                    monthMutable = monthMutable
    )
}

@Composable
fun screen7() {
    val dateMutable: MutableState<String> = mutableStateOf("")

    calendarComposableScreen(dateMutable)
}