package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.PrincipalScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetPrincipalScreenMetrics(viewModel: PrincipalScreenViewModel) = launch {
    viewModel.updateMetrics()
}
fun runGetPrincipalScreenMetrics(viewModel: PrincipalScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetPrincipalScreenMetrics(viewModel)
    }
}
@Composable
fun HomeScreenDestination(
    viewModel: PrincipalScreenViewModel = hiltViewModel<PrincipalScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val updateState: (Int) -> Unit = { selectedIndex ->
        viewModel.onStateChanged(selectedIndex)
    }

    runGetPrincipalScreenMetrics(viewModel)

    //Ecran principal
    PrincipalComposableScreen(
        onNavigateToEditTransactionScreen = { index ->
            navController.navigate("editTransactionScreen/$index")
        },
        onNavigateToHomeScreen = { },
        onNavigateToTransactionScreen = {
            navController.navigate("transactionScreen") {
                popUpTo("transactionScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToCategoriesScreen = {
            navController.navigate("categoriesScreen") {
                popUpTo("categoriesScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToFixedBudgetsScreen = {
            navController.navigate("fixedBudgetsScreen") {
                popUpTo("fixedBudgetsScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToBudgetSummaryScreen = {
            navController.navigate("budgetSummaryScreen") {
                popUpTo("budgetSummaryScreen") {
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
        onNavigateToGraphsScreen = {
            navController.navigate(graphsScreen) {
                popUpTo(graphsScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToMementosScreen = {
            navController.navigate(mementosScreen) {
                popUpTo(mementosScreen) {
                    inclusive = true
                }
            }
        },
        principalScreenUIState = state,
        updateState = updateState
    )
}