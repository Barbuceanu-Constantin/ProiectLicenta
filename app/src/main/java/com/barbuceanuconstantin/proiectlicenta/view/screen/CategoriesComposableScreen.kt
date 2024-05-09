package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.model.CategoriesLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesComposableScreen(lSA: List<Categories>,
                               lSP: List<Categories>,
                               lSD: List<Categories>,
                               navController: NavController,
                               onNavigateToEditCategoriesScreen: () -> Unit,
                               onNavigateToHomeScreen: () -> Unit,
                               onNavigateToTransactionScreen: () -> Unit,
                               onNavigateToCategoriesScreen: () -> Unit,
                               onNavigateToFixedBudgetsScreen: () -> Unit,
                               onNavigateToBudgetSummaryScreen: () -> Unit,
                               onNavigateToCalendarScreen: () -> Unit,
                               onNavigateToGraphsScreen: () -> Unit,
                               onNavigateToMementosScreen: () -> Unit,
                               categoriesScreenUIState: CategoriesScreenUIState,
                               updateState: (Boolean, Boolean, Boolean) -> Unit,) {
    val showA = categoriesScreenUIState.showA
    val showP = categoriesScreenUIState.showP
    val showD = categoriesScreenUIState.showD

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonCustom(
                                        navigateAction = onNavigateToEditCategoriesScreen,
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigateToHomeScreen,
                onNavigateToTransactionScreen,
                onNavigateToCategoriesScreen,
                onNavigateToFixedBudgetsScreen
            )
        },
        topBar = {
            MainScreenToAppBar( id = R.string.categorii,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer (Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            SegmentedButton3(showA = showA, showP = showP, showD = showD, updateState = updateState)

            FadingArrowIcon()

            if (showA && !showP && !showD) {
                CategoriesLazyColumn(categorii = lSA, navController = navController)
            } else if (showP && !showA && !showD) {
                CategoriesLazyColumn(categorii = lSP, navController = navController)
            } else if (showD && !showA && !showP) {
                CategoriesLazyColumn(categorii = lSD, navController = navController)
            } else if (showA && showP && showD) {
                CategoriesLazyColumn(categorii = (lSA + lSP + lSD).toMutableList(), navController = navController)
            } else if (showA && showP && !showD) {
                CategoriesLazyColumn(categorii = (lSA + lSP).toMutableList(), navController = navController)
            } else if (showA && !showP && showD) {
                CategoriesLazyColumn(categorii = (lSA + lSD).toMutableList(), navController = navController)
            } else if (!showA && showP && showD) {
                CategoriesLazyColumn(categorii = (lSP + lSD).toMutableList(), navController = navController)
            }
        }
    }
}