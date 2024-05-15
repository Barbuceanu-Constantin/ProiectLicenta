package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetCategoriesTransactionsLists(viewModel: TransactionsScreenViewModel) = launch {
    viewModel.onStateChangedLists()
}
fun runGetCategoriesTransactionsLists(viewModel: TransactionsScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesTransactionsLists(viewModel)
    }
}
@Composable
fun TransactionsScreenDestination(
    viewModel: TransactionsScreenViewModel = hiltViewModel<TransactionsScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    // Define a function that can be called to update the state
    val updateStateMainScreen: (Boolean, Boolean, Boolean) -> Unit =
        { showA, showP, showD ->
            viewModel.onStateChangedMainScreen(showA, showP, showD)
        }
    val updateStateButtons: () -> Unit = {
        viewModel.onStateChangedButtons()
    }
    val deleteById: (Int) -> Unit = { id ->
        viewModel.onDeleteById(id)
    }
    val updateUpdateId: (Int) -> Unit = { id ->
        viewModel.onStateChangedIdUpdate(id)
    }

    runGetCategoriesTransactionsLists(viewModel)

    //Tranzactii
    TransactionsComposableScreen(
        state.revenueTransactions,
        state.expensesTransactions,
        state.debtTransactions,
        state.categoriesA,
        state.categoriesP,
        state.categoriesD,
        navController,
        onNavigateToEditTransactionScreen = { index ->
            navController.navigate("editTransactionScreen/$index")
        },
        onNavigateToHomeScreen = {
            navController.navigate("homeScreen") {
                popUpTo("homeScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToTransactionScreen = { },
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
        transactionsScreenUIState = state,
        updateStateMainScreen = updateStateMainScreen,
        updateStateButtons = updateStateButtons,
        deleteById = deleteById,
        updateUpdateId = updateUpdateId
    )
}