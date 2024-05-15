package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.BudgetSummaryScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.BudgetSummaryComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetTransactionListsBudgetSummaryScreen(viewModel: BudgetSummaryScreenViewModel) = launch {
    viewModel.onStateChangedLists()
}
fun runGetTransactionListsBudgetSummaryScreen(viewModel: BudgetSummaryScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetTransactionListsBudgetSummaryScreen(viewModel)
    }
}
@Composable
fun BudgetSummaryScreenDestination(
    viewModel: BudgetSummaryScreenViewModel = hiltViewModel<BudgetSummaryScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    //Aici vin functiile pe care trebuie sa le extrag.
    val updateStateDateButton: (Boolean) -> Unit = { dateButton ->
        viewModel.onStateChangedDateButton(dateButton)
    }
    val updateStateMonth: (String) -> Unit = { month ->
        viewModel.onStateChangedMonth(month)
    }
    val updateStateTimeInterval: (Boolean, Boolean, Boolean) -> Unit =
        { daily, weekly, monthly ->
            viewModel.onStateChangedTimeInterval(daily, weekly, monthly)
        }
    val updateStateDate: (String) -> Unit = { date ->
        viewModel.onStateChangedDate(date)
    }
    val updateStateButtons: () -> Unit = { viewModel.onStateChangedButtons() }
    val deleteById: (Int) -> Unit = { id ->
        viewModel.onDeleteById(id)
    }
    val updateUpdateId: (Int) -> Unit = { id ->
        viewModel.onStateChangedIdUpdate(id)
    }

    runGetTransactionListsBudgetSummaryScreen(viewModel)

    //Bugete fixe
    BudgetSummaryComposableScreen(
        lTrA = state.expensesTransactions,
        lTrP = state.revenueTransactions,
        categoriesA = state.categoriesA,
        categoriesP = state.categoriesP,
        categoriesD = state.categoriesD,
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
        onNavigateToBudgetSummaryScreen = { },
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
        navController = navController,
        budgetSummaryScreenUIState = state,
        updateStateDateButton = updateStateDateButton,
        updateStateMonth = updateStateMonth,
        updateStateTimeInterval = updateStateTimeInterval,
        updateStateDate = updateStateDate,
        updateStateButtons = updateStateButtons,
        deleteById = deleteById,
        updateUpdateId = updateUpdateId
    )
}