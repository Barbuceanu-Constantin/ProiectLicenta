package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.model.TransactionsLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenUIState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TransactionsComposableScreen(
    lTrA: List<CategoryAndTransactions>,
    lTrP: List<CategoryAndTransactions>,
    lTrD: List<CategoryAndTransactions>,
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
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
    updateStateButtons: () -> Unit,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit) {

    val showA = transactionsScreenUIState.showA
    val showP = transactionsScreenUIState.showP
    val showD = transactionsScreenUIState.showD
    val buttons = transactionsScreenUIState.buttons
    val idUpdate = transactionsScreenUIState.idUpdate

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
        },
        modifier = Modifier.semantics{ testTagsAsResourceId = true }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_extra)))

            SegmentedButton3(showA, showP, showD, updateStateMainScreen)

            FadingArrowIcon()

            if (showA && !showP && !showD) {
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrA,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else if (showP && !showA && !showD) {
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrP,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else if (showD && !showA && !showP) {
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrD,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else if (showA && showP && showD) {
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrA + lTrP + lTrD,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else  if (showA && showP) {
                //showA && showP && !showD
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrA + lTrP,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else if (showA) {
                //showA && showD && !showP
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrA + lTrD,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            } else if (showP) {
                //!showA && showP && showD
                TransactionsLazyColumn(
                                        categoriesA = categoriesA,
                                        categoriesP = categoriesP,
                                        categoriesD = categoriesD,
                                        tranzactii = lTrP + lTrD,
                                        navController = navController,
                                        updateStateButtons = updateStateButtons,
                                        buttons = buttons,
                                        deleteById = deleteById,
                                        updateUpdateId = updateUpdateId,
                                        idUpdate = idUpdate,
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            }
        }
    }
}


