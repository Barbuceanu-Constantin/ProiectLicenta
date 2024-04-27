package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphsComposableScreen(modifier: Modifier = Modifier,
                           onNavigateToHomeScreen: () -> Unit,
                           onNavigateToTransactionScreen: () -> Unit,
                           onNavigateToCategoriesScreen: () -> Unit,
                           onNavigateToFixedBudgetsScreen: () -> Unit,
                           onNavigateToBudgetSummaryScreen: () -> Unit,
                           onNavigateToCalendarScreen: () -> Unit,
                           onNavigateToGraphsScreen: () -> Unit,
                           onNavigateToMementosScreen: () -> Unit) {
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
            MainScreenToAppBar( id = R.string.grafice,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        }
    ) {innerPadding ->
        Column(
            modifier = modifier.fillMaxWidth().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))
        }
    }
}

