package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.di.DemoScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screen.DemoComposableScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun DemoScreenDestination(
    viewModel: DemoScreenViewModel = hiltViewModel<DemoScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val onInitCategoryLists: suspend () -> Unit = {
        viewModel.runInitCategoryLists()
    }
    val onDeleteTables: suspend () -> Unit = {
        viewModel.onDeleteTables()
    }
    val updateTablesForDemo: suspend () -> Unit = {
        viewModel.updateTablesForDemo()
    }
    val getCategoryCount: () -> Unit = {
        viewModel.getCategoryCount()
    }
    val isEmpty: () -> Unit = {
        viewModel.isEmpty()
    }
    val isEmptyTransactions: () -> Unit = {
        viewModel.isEmptyTransactions()
    }

    //Ecran demo
    DemoComposableScreen(
        onNavigateToHomeScreen = {
            navController.navigate("homeScreen") {
                popUpTo("demoScreen") {
                    inclusive = true
                }
            }
        },
        demoScreenUIState = state,
        onInitCategoryLists = onInitCategoryLists,
        onDeleteTables = onDeleteTables,
        updateTablesForDemo = updateTablesForDemo,
        isEmpty = isEmpty,
        isEmptyTransactions = isEmptyTransactions,
        getCategoryCount = getCategoryCount
    )
}