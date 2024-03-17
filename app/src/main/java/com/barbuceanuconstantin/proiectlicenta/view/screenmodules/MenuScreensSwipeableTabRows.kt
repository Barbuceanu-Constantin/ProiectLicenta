@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.RememberMe
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.RememberMe
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.lTrA
import com.barbuceanuconstantin.proiectlicenta.lTrD
import com.barbuceanuconstantin.proiectlicenta.lTrP
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screen.calendarComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.categoriesComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.fixedBudgetsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.principalComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.transactionsComposableScreen

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

private var lTranzactiiActive: SnapshotStateList<Tranzactie> = lTrA
private var lTranzactiiPasive: SnapshotStateList<Tranzactie> = lTrP
private var lTranzactiiDatorii: SnapshotStateList<Tranzactie> = lTrD

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
    var showA = mutableStateOf(true)
    var showP = mutableStateOf(true)
    var showD = mutableStateOf(true)
    var addButton = mutableStateOf(false)
    val index = mutableIntStateOf(-1)
    val sem = mutableIntStateOf(-1)
    val updateTransactionButton = mutableStateOf(false)

    transactionsComposableScreen(showA, showP, showD, addButton, lTranzactiiActive, lTranzactiiPasive, lTranzactiiDatorii, index, sem, updateTransactionButton)
}
@Composable
fun screen2() {
    var showA = mutableStateOf(true)
    var showP = mutableStateOf(true)
    var showD = mutableStateOf(true)
    var addButton = mutableStateOf(false)

    categoriesComposableScreen(showA, showP, showD, addButton, listSubcategoriesRevenue, listSubcategoriesExpenses, listSubcategoriesDebts)
}
@Composable
fun screen4() {
    var fab = mutableStateOf(false)

    fixedBudgetsComposableScreen(fab)
}
@Composable
fun screen7() {
    calendarComposableScreen()
}

@Composable
fun showMenu() {
    val tabItems = listOf(
        TabItem(
            title = stringResource(id = R.string.acasa),
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        TabItem(
            title = stringResource(id = R.string.tranzactii),
            unselectedIcon = Icons.Outlined.Money,
            selectedIcon = Icons.Filled.Money
        ),
        TabItem(
            title = stringResource(id = R.string.categorii),
            unselectedIcon = Icons.Outlined.Category,
            selectedIcon = Icons.Filled.Category
        ),
        TabItem(
            title = stringResource(id = R.string.mementouri),
            unselectedIcon = Icons.Outlined.RememberMe,
            selectedIcon = Icons.Filled.RememberMe
        ),
        TabItem(
            title = stringResource(id = R.string.Bugete_fixe),
            unselectedIcon = Icons.Outlined.RememberMe,
            selectedIcon = Icons.Filled.RememberMe
        ),
        TabItem(
            title = stringResource(id = R.string.valute),
            unselectedIcon = Icons.Outlined.CurrencyExchange,
            selectedIcon = Icons.Filled.CurrencyExchange
        ),
        TabItem(
            title = stringResource(id = R.string.sumar_buget),
            unselectedIcon = Icons.Outlined.Summarize,
            selectedIcon = Icons.Filled.Summarize
        ),
        TabItem(
            title = stringResource(id = R.string.calendar),
            unselectedIcon = Icons.Outlined.CalendarMonth,
            selectedIcon = Icons.Filled.CalendarMonth
        ),
        TabItem(
            title = stringResource(id = R.string.grafice),
            unselectedIcon = Icons.Outlined.AutoGraph,
            selectedIcon = Icons.Filled.AutoGraph
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //am adaugat minOf pt siguranta in caz ca se modifica lista
        ScrollableTabRow(selectedTabIndex = minOf(tabItems.count(), selectedTabIndex),
                         modifier = Modifier.fillMaxHeight(100f / LocalConfiguration.current.screenHeightDp)) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                                selectedTabIndex = index
                              },
                    text = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if(index == selectedTabIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> {
                screen0()
            }
            1 -> {
                screen1()
            }
            2 -> {
                screen2()
            }
            3 -> {
            }
            4 -> {
                screen4()
            }
            5 -> {
            }
            6 -> {
            }
            7 -> {
                screen7()
            }
            8 -> {
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = tabItems[index].title)
            }
        }
    }
}