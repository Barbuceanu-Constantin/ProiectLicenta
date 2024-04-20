package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonTransactions
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn

@Composable
fun TransactionsComposableScreen(lTrA: SnapshotStateList<Transaction>,
                                 lTrP: SnapshotStateList<Transaction>,
                                 lTrD: SnapshotStateList<Transaction>,
                                 onNavigateToEditTransactionScreen: (index : Int) -> Unit,
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
            FloatingActionButtonTransactions(
                                        navigateAction = onNavigateToEditTransactionScreen,
                                        returnBackIndex = 3
                                    )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigateToHomeScreen,
                onNavigateToTransactionScreen,
                onNavigateToCategoriesScreen,
                onNavigateToFixedBudgetsScreen
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.gap)))

            SegmentedButton3(first = showA, second = showP, third = showD)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.gap)))

            if (showA.value && !showP.value && !showD.value) {
                TranzactiiLazyColumn(tranzactii = lTrA, buttons = buttons)
            } else if (showP.value && !showA.value && !showD.value) {
                TranzactiiLazyColumn(tranzactii = lTrP, buttons = buttons)
            } else if (showD.value && !showA.value && !showP.value) {
                TranzactiiLazyColumn(tranzactii = lTrD, buttons = buttons)
            } else if (showA.value && showP.value && showD.value) {
                TranzactiiLazyColumn(tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(), buttons = buttons)
            } else  if (showA.value && showP.value && !showD.value) {
                TranzactiiLazyColumn(tranzactii = (lTrA + lTrP).toMutableStateList(), buttons = buttons)
            } else if (showA.value && showD.value && !showP.value) {
                TranzactiiLazyColumn(tranzactii = (lTrA + lTrD).toMutableStateList(), buttons = buttons)
            } else if (!showA.value && showP.value && showD.value ) {
                TranzactiiLazyColumn(tranzactii = (lTrP + lTrD).toMutableStateList(), buttons = buttons)
            }
        }
    }
}


