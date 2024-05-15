package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.model.CalendarSummaryTranzactiiLazyColumn

@Composable
fun CurrentSituationComposableScreen(
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
    onNavigateToCalendarScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    incomes: Boolean,
    expenses: Boolean,
    lTrA: List<CategoryAndTransactions>,
    lTrP: List<CategoryAndTransactions>,
    date: String,
    buttons: Boolean,
    updateButtons: () -> Unit,
    updateIncomesExpenses: (Boolean, Boolean) -> Unit,
    navController: NavController,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit,
    idUpdate: Int
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
                    navController = navController,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD
                )
            } else if (!incomes && expenses) {
                CalendarSummaryTranzactiiLazyColumn(
                    tranzactii = lTrP,
                    incomesOrExpenses = false,
                    date = date,
                    buttons = buttons,
                    updateButtons = updateButtons,
                    updateIncomesExpenses = updateIncomesExpenses,
                    navController = navController,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD
                )
            }
        }
    }
}
