package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.MementosScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.MementosComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun CoroutineScope.launchGetBudgetsLists(viewModel: MementosScreenViewModel) = launch {
    viewModel.onStateChangedList()
}
fun runGetBudgetsLists(viewModel: MementosScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetBudgetsLists(viewModel)
    }
}

suspend fun getCategoryNameMemento(id: Int, viewModel: MementosScreenViewModel): String {
    return withContext(Dispatchers.IO) {
        viewModel.budgetTrackerRepository.getCategoryName(id)
    }
}

suspend fun getCategoryNameMementoAsync(id: Int, viewModel: MementosScreenViewModel): String {
    return getCategoryNameMemento(id, viewModel)
}

@Composable
fun MementosScreenDestination(
    viewModel: MementosScreenViewModel = hiltViewModel<MementosScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val getCategoryName: suspend (Int) -> String = { id ->
        withContext(Dispatchers.Main) {
            val categoryName = getCategoryNameMementoAsync(id, viewModel)
            categoryName
        }
    }

    runGetBudgetsLists(viewModel)

    MementosComposableScreen(
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
        onNavigateToMementosScreen = { },
        onNavigateToGraphsScreen = {
            navController.navigate(graphsScreen) {
                popUpTo(graphsScreen) {
                    inclusive = true
                }
            }
        },
        mementosScreenUIState = state,
        getCategoryName = getCategoryName
    )
}