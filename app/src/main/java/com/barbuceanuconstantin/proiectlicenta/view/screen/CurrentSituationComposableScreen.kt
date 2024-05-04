package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.data.model.CalendarSummaryTranzactiiLazyColumn

@Composable
fun CurrentSituationComposableScreen(
    onNavigateToCalendarScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    incomes: Boolean,
    expenses: Boolean,
    lTrA: SnapshotStateList<Transactions>,
    lTrP: SnapshotStateList<Transactions>,
    date: String,
    buttons: Boolean,
    updateButtons: (Boolean) -> Unit,
    updateIncomesExpenses: (Boolean, Boolean) -> Unit,
    navController: NavController
) {
    Scaffold (
        topBar = {
            EditTopAppBar(
                id = R.string.situatie_zilnica,
                onNavigateToBackScreen = onNavigateToCalendarScreen,
                onNavigateToHomeScreen = onNavigateToHomeScreen
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (incomes && !expenses) {
                CalendarSummaryTranzactiiLazyColumn(
                    tranzactii = lTrA,
                    incomesOrExpenses = true,
                    date = date,
                    buttons = buttons,
                    updateButtons = updateButtons,
                    updateIncomesExpenses = updateIncomesExpenses,
                    navController = navController
                )
            } else if (!incomes && expenses) {
                CalendarSummaryTranzactiiLazyColumn(
                    tranzactii = lTrP,
                    incomesOrExpenses = false,
                    date = date,
                    buttons = buttons,
                    updateButtons = updateButtons,
                    updateIncomesExpenses = updateIncomesExpenses,
                    navController = navController
                )
            }
        }
    }
}
