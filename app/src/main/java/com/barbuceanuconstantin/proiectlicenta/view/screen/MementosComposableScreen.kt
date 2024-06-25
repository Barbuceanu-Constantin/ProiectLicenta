package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
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
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.MementosLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.MementosScreenUIState
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MementosComposableScreen(modifier: Modifier = Modifier,
                             onNavigateToHomeScreen: () -> Unit,
                             onNavigateToTransactionScreen: () -> Unit,
                             onNavigateToCategoriesScreen: () -> Unit,
                             onNavigateToFixedBudgetsScreen: () -> Unit,
                             onNavigateToBudgetSummaryScreen: () -> Unit,
                             onNavigateToCalendarScreen: () -> Unit,
                             onNavigateToGraphsScreen: () -> Unit,
                             onNavigateToMementosScreen: () -> Unit,
                             mementosScreenUIState: MementosScreenUIState,
                             getCategoryName: suspend (Int) -> String,
                             getCurrentFilling: (Int, Date, Date) -> Double) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold (
                bottomBar = {
                    BottomNavigationBar(
                        onNavigateToHomeScreen,
                        onNavigateToTransactionScreen,
                        onNavigateToCategoriesScreen,
                        onNavigateToFixedBudgetsScreen
                    )
                },
                topBar = {
                    MainScreenToAppBar( id = R.string.mementouri, scrollBehavior = scrollBehavior,
                        onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                        onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                        onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                        onNavigateToMementosScreen = onNavigateToMementosScreen
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxWidth().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            FadingArrowIcon()

            MementosLazyColumn(
                lFixedBudgets = mementosScreenUIState.budgets,
                getCategoryName = getCategoryName,
                getCurrentFilling = getCurrentFilling
            )
        }
    }
}