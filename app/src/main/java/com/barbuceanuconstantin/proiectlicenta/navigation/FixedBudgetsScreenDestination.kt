package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.FixedBudgetsComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetBudgetsLists(viewModel: FixedBudgetsScreenViewModel) = launch {
    viewModel.onStateChangedList()
}
fun runGetBudgetsLists(viewModel: FixedBudgetsScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetBudgetsLists(viewModel)
    }
}
@Composable
fun FixedBudgetsScreenDestination(
    viewModel: FixedBudgetsScreenViewModel = hiltViewModel<FixedBudgetsScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val updateState: (Boolean) -> Unit = { buttons ->
        viewModel.onStateChangedButtons(buttons)
    }
    val deleteByName: (String) -> Unit = { name ->
        viewModel.onDeleteByName(name)
    }

    runGetBudgetsLists(viewModel)

    //Bugete fixe
    FixedBudgetsComposableScreen(
        state.budgets,
        navController,
        onNavigateToEditBudgetScreen = {
            navController.navigate(editBudgetScreenShortPath)
        },
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
        onNavigateToFixedBudgetsScreen = { },
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
        fixedBudgetsScreenUIState = state,
        updateStateButtons = updateState,
        deleteByNameCoroutine = deleteByName
    )
}