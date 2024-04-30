package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsComposableScreen(lTrA: SnapshotStateList<Transaction>,
                                 lTrP: SnapshotStateList<Transaction>,
                                 lTrD: SnapshotStateList<Transaction>,
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
                                 transactionsScreenUIState: TransactionsScreenUIState) {
    val showA: MutableState<Boolean> = transactionsScreenUIState.showA
    val showP: MutableState<Boolean> = transactionsScreenUIState.showP
    val showD: MutableState<Boolean> = transactionsScreenUIState.showD
    val buttons: MutableState<Boolean> = transactionsScreenUIState.buttons

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

            SegmentedButton3(first = showA, second = showP, third = showD)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_extra)))

            if (showA.value && !showP.value && !showD.value) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrA,
                                        buttons = buttons,
                                        navController = navController,
                )
            } else if (showP.value && !showA.value && !showD.value) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrP,
                                        buttons = buttons,
                                        navController = navController,
                )
            } else if (showD.value && !showA.value && !showP.value) {
                TranzactiiLazyColumn(
                                        tranzactii = lTrD,
                                        buttons = buttons,
                                        navController = navController,
                )
            } else if (showA.value && showP.value && showD.value) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                )
            } else  if (showA.value && showP.value && !showD.value) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrP).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController
                )
            } else if (showA.value && showD.value && !showP.value) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrA + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                )
            } else if (!showA.value && showP.value && showD.value ) {
                TranzactiiLazyColumn(
                                        tranzactii = (lTrP + lTrD).toMutableStateList(),
                                        buttons = buttons,
                                        navController = navController,
                )
            }
        }
    }
}


