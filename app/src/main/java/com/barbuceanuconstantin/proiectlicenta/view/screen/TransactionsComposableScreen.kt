package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsComposableScreen(lTrA: SnapshotStateList<Transactions>,
                                 lTrP: SnapshotStateList<Transactions>,
                                 lTrD: SnapshotStateList<Transactions>,
                                 navController: NavController,
                                 onNavigateToEditTransactionScreen: (index: Int) -> Unit,
                                 onNavigateToHomeScreen: () -> Unit,
                                 onNavigateToTransactionScreen: () -> Unit,
                                 onNavigateToCategoriesScreen: () -> Unit,
                                 onNavigateToFixedBudgetsScreen: () -> Unit,
                                 onNavigateToBudgetSummaryScreen: () -> Unit,
                                 onNavigateToCalendarScreen: () -> Unit,
                                 onNavigateToGraphsScreen: () -> Unit,
                                 onNavigateToMementosScreen: () -> Unit,
                                 transactionsScreenUIState: TransactionsScreenUIState,
                                 updateStateMainScreen: (Boolean, Boolean, Boolean) -> Unit,
                                 updateStateButtons: (Boolean) -> Unit) {
    val showA = transactionsScreenUIState.showA
    val showP = transactionsScreenUIState.showP
    val showD = transactionsScreenUIState.showD
    val buttons = transactionsScreenUIState.buttons

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonCustom(navigateAction = { onNavigateToEditTransactionScreen(3) })
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigateToHomeScreen,
                onNavigateToTransactionScreen,
                onNavigateToCategoriesScreen,
                onNavigateToFixedBudgetsScreen
            )
        },
        topBar = {
            MainScreenToAppBar( id = R.string.tranzactii,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_extra)))

            SegmentedButton3(showA, showP, showD, updateStateMainScreen)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_extra)))

            if (showA && !showP && !showD) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrA,
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else if (showP && !showA && !showD) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrP,
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else if (showD && !showA && !showP) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrD,
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else if (showA && showP && showD) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else  if (showA && showP && !showD) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrP).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else if (showA && showD && !showP) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            } else if (!showA && showP && showD) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrP + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons
                )
            }
        }
    }
}


