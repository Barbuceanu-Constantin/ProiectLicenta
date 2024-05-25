package com.barbuceanuconstantin.proiectlicenta.view.screen

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
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.di.GraphsScreenUIState
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.Menu

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
                           onNavigateToMementosScreen: () -> Unit,
                           graphsScreenUIState: GraphsScreenUIState,
                           updateGraphInterval: (String) -> Unit,
                           updateChartTypeChoice: (String) -> Unit) {
    val lChoices = listOf(
        stringResource(id = R.string.selectie_globala),
        stringResource(id = R.string.selectie_lunara),
        stringResource(id = R.string.comparatie_luni)
    )
    val lChartTypeChoices = listOf(
        stringResource(id = R.string.pie_chart),
        stringResource(id = R.string.bar_chart),
    )
    val graphName = graphsScreenUIState.graphChoice
    val chartType = graphsScreenUIState.chartType

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
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            Menu(update = updateGraphInterval,
                 lChoices = lChoices,
                 value = graphName,
                 label = stringResource(id = R.string.selectare_graph_interval))

            if (graphName == stringResource(id = R.string.selectie_globala) || graphName == stringResource(id = R.string.selectie_lunara)) {
                Menu(update = updateChartTypeChoice,
                    lChoices = lChartTypeChoices,
                    value = chartType,
                    label = stringResource(id = R.string.selectare_graph_name))
            }
        }
    }
}

