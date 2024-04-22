package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MoreScreensMenu

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
                                 onNavigateToFixedBudgetsScreen: () -> Unit) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }
    val buttons: MutableState<Boolean> = remember { mutableStateOf(false)}

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonCustom(navigateAction = { onNavigateToEditTransactionScreen(0) })
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
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.tranzactii))
                },
                actions = {
                    MoreScreensMenu()
                }
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


