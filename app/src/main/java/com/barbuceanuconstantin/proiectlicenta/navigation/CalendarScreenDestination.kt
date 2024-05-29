package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.CalendarScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.CalendarComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchDeleteByIdCalendarScreen(id: Int, viewModel: CalendarScreenViewModel) = launch {
    viewModel.onDeleteById(id)
}
fun runDeleteByIdCalendarScreen(id: Int, viewModel: CalendarScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchDeleteByIdCalendarScreen(id = id, viewModel = viewModel)
    }
}
fun CoroutineScope.launchGetCategoriesListsCalendarScreen(viewModel: CalendarScreenViewModel) = launch {
    viewModel.onStateChangedCategoryLists()
}
fun runGetCategoriesListsCalendarScreen(viewModel: CalendarScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesListsCalendarScreen(viewModel)
    }
}
@Composable
fun CalendarScreenDestination(
    viewModel: CalendarScreenViewModel = hiltViewModel<CalendarScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    val updateDate: (String) -> Unit = { date ->
        viewModel.onStateChangedDate(date)
    }
    val updateIncomesExpenses: (Boolean, Boolean) -> Unit = { incomes, expenses ->
        viewModel.onStateChangedIncomesExpenses(incomes, expenses)
    }
    val updateButtons: () -> Unit =  { viewModel.onStateChangedButtons() }
    val deleteById: (Int) -> Unit = { id ->
        runDeleteByIdCalendarScreen(id, viewModel)
    }
    val updateUpdateId: (Int) -> Unit = { id ->
        viewModel.onStateChangedIdUpdate(id)
    }

    if(state.firstComposition) {
        runGetCategoriesListsCalendarScreen(viewModel)
    }

    //Bugete fixe
    CalendarComposableScreen(
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
        calendarScreenUIState = state,
        updateDate = updateDate,
        updateIncomesExpenses = updateIncomesExpenses,
        updateButtons = updateButtons,
        navController = navController,
        lTrA = state.lTrA,
        lTrP = state.lTrP,
        deleteById = deleteById,
        updateUpdateId = updateUpdateId,
        categoriesA = state.categoriesA,
        categoriesP = state.categoriesP,
        categoriesD = state.categoriesD
    )
}