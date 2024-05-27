package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.di.DemoScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.DemoComposableScreen

@Composable
fun DemoScreenDestination(
    viewModel: DemoScreenViewModel = hiltViewModel<DemoScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val onInitCategoryLists: () -> Unit = {
        viewModel.runInitCategoryLists()
    }
    val onDeleteTables: () -> Unit = {
        viewModel.onDeleteTables()
    }
    val updateTablesForDemo: () -> Unit = {
        viewModel.updateTablesForDemo()
    }
    val getMainCategoryCount: () -> Int = {
        viewModel.getMainCategoryCount()
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
        getMainCategoryCount = getMainCategoryCount
    )
}