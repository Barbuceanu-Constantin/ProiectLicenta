package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.model.BudgetsLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenUIState
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedBudgetsComposableScreen(
    lFixedBudgets: List<Budgets>,
    navController: NavController,
    onNavigateToEditBudgetScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToTransactionScreen: () -> Unit,
    onNavigateToCategoriesScreen: () -> Unit,
    onNavigateToFixedBudgetsScreen: () -> Unit,
    onNavigateToBudgetSummaryScreen: () -> Unit,
    onNavigateToCalendarScreen: () -> Unit,
    onNavigateToGraphsScreen: () -> Unit,
    onNavigateToMementosScreen: () -> Unit,
    fixedBudgetsScreenUIState: FixedBudgetsScreenUIState,
    updateStateButtons: (Boolean) -> Unit,
    deleteByIdCoroutine: (Int) -> Unit,
    getCategoryName: suspend (Int) -> String,
    getTotalRevenues: (Date, Date) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val buttons: Boolean = fixedBudgetsScreenUIState.buttons

    Scaffold(
        floatingActionButton = { FloatingActionButtonCustom(
                                                            navigateAction = onNavigateToEditBudgetScreen,
                                                            )
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
            MainScreenToAppBar( id = R.string.bugete, scrollBehavior = scrollBehavior,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally ) {
            FadingArrowIcon()

            BudgetsLazyColumn(lFixedBudgets,
                              buttons,
                              navController,
                              updateStateButtons,
                              deleteByIdCoroutine = deleteByIdCoroutine,
                              getCategoryName = getCategoryName,
                              getTotalRevenues = getTotalRevenues,
                              state = fixedBudgetsScreenUIState
            )
        }
    }
}