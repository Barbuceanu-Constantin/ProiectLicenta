package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.di.CalendarScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.CalendarComposableScreen

@Composable
fun CalendarScreenDestination(
    viewModel: CalendarScreenViewModel = hiltViewModel<CalendarScreenViewModel>(),
    navController: NavHostController,
) {
    val lTrA = mutableStateListOf<Transactions>()
    val lTrP = mutableStateListOf<Transactions>()

    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val updateDate: (String) -> Unit = { date ->
        viewModel.onStateChangedDate(date)
    }
    val updateIncomesExpenses: (Boolean, Boolean) -> Unit = { incomes, expenses ->
        viewModel.onStateChangedIncomesExpenses(incomes, expenses)
    }
    val updateButtons: (Boolean) -> Unit = { buttons ->
        viewModel.onStateChangedButtons(buttons)
    }

    //Bugete fixe
    CalendarComposableScreen(
        lTrA, lTrP,
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
        navController = navController
    )
}