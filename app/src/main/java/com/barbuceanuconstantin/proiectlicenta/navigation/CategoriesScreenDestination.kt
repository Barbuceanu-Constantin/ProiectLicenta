package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetCategoriesListsCategoriesScreen(viewModel: CategoriesScreenViewModel) = launch {
    viewModel.onStateChangedLists()
}
fun runGetCategoriesListsCategoriesScreen(viewModel: CategoriesScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesListsCategoriesScreen(viewModel)
    }
}
@Composable
fun CategoriesScreenDestination(
    viewModel: CategoriesScreenViewModel = hiltViewModel<CategoriesScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val updateStateMainScreen: (Boolean, Boolean, Boolean) -> Unit =
        { showA, showP, showD ->
            viewModel.onStateChangedMainScreen(showA, showP, showD)
        }
    val deleteByName: (String) -> Unit = { name ->
        viewModel.onDeleteByName(name)
    }

    runGetCategoriesListsCategoriesScreen(viewModel)

    //Categorii
    CategoriesComposableScreen(
        state.categoriesA,
        state.categoriesP,
        state.categoriesD,
        navController,
        onNavigateToEditCategoriesScreen = {
            navController.navigate(editCategoryScreenShortPath)
        },
        onNavigateToHomeScreen = {
            navController.navigate("homeScreen") {
                popUpTo("homeScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToTransactionScreen = {
            navController.navigate("transactionScreen") {
                popUpTo("transactionScreen") {
                    inclusive = true
                }
            }
        },
        onNavigateToCategoriesScreen = { },
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
        categoriesScreenUIState = state,
        updateState = updateStateMainScreen,
        deleteByNameCoroutine = deleteByName
    )
}