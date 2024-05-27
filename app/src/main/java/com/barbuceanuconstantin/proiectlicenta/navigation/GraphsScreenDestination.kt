package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.GraphsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.GraphsComposableScreen

@Composable
fun GraphsScreenDestination(
    viewModel: GraphsScreenViewModel = hiltViewModel<GraphsScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    val updateGraphInterval: (String) -> Unit = { it ->
        viewModel.onStateChangedGraphInterval(it)
    }
    val updateGraphType: (String) -> Unit = { it ->
        viewModel.onStateChangedGraphType(it)
    }
    val updateMonth: (String) -> Unit = { it ->
        viewModel.onStateChangedMonth(it)
    }
    val updateMetricsGlobal: () -> Unit = {
        viewModel.updateMetricsGlobal()
    }
    val updateMetricsMonth: (String) -> Unit = {
        viewModel.updateMetricsMonth(it)
    }
    val updateMonthComparisonType: (String) -> Unit = {
        viewModel.onStateChangedMonthComparisonChartType(it)
    }
    val updateMonthsListSum: (List<String>) -> Triple<List<Double>, List<Double>, List<Double>> = {
        viewModel.onStateChangedMonthsListSum(it)
    }

    GraphsComposableScreen(
        onNavigateToHomeScreen = {
            navController.navigate(homeScreen) {
                popUpTo(homeScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToTransactionScreen = {
            navController.navigate(transactionScreen) {
                popUpTo(transactionScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToCategoriesScreen = {
            navController.navigate(categoriesScreen) {
                popUpTo(categoriesScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToFixedBudgetsScreen = {
            navController.navigate(fixedBudgetsScreen) {
                popUpTo(fixedBudgetsScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToBudgetSummaryScreen = {
            navController.navigate(budgetSummaryScreen) {
                popUpTo(budgetSummaryScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToCalendarScreen = {
            navController.navigate(calendarScreen) {
                popUpTo(calendarScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToGraphsScreen = { },
        onNavigateToMementosScreen = {
            navController.navigate(mementosScreen) {
                popUpTo(mementosScreen) {
                    inclusive = true
                }
            }
        },
        graphsScreenUIState = state,
        updateGraphInterval = updateGraphInterval,
        updateChartTypeChoice = updateGraphType,
        updateMonth = updateMonth,
        updateMetricsGlobal = updateMetricsGlobal,
        updateMetricsMonth = updateMetricsMonth,
        updateMonthComparisonType = updateMonthComparisonType,
        updateMonthsListSum = updateMonthsListSum
    )
}