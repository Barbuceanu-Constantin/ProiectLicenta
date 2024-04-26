package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.data.model.CategoriesLazyColumn
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MoreScreensMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesComposableScreen(lSA: MutableList<Category>,
                               lSP: MutableList<Category>,
                               lSD: MutableList<Category>,
                               navController: NavController,
                               onNavigateToEditCategoriesScreen: () -> Unit,
                               onNavigateToHomeScreen: () -> Unit,
                               onNavigateToTransactionScreen: () -> Unit,
                               onNavigateToCategoriesScreen: () -> Unit,
                               onNavigateToFixedBudgetsScreen: () -> Unit,
                               onNavigateToBudgetSummaryScreen: () -> Unit,
                               onNavigateToCalendarScreen: () -> Unit) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }

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
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen)
        }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer (Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            SegmentedButton3(first = showA, second = showP, third = showD)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_extra)))

            if (showA.value && !showP.value && !showD.value) {
                CategoriesLazyColumn(categorii = lSA, navController = navController)
            } else if (showP.value && !showA.value && !showD.value) {
                CategoriesLazyColumn(categorii = lSP, navController = navController)
            } else if (showD.value && !showA.value && !showP.value) {
                CategoriesLazyColumn(categorii = lSD, navController = navController)
            } else if (showA.value && showP.value && showD.value) {
                CategoriesLazyColumn(categorii = (lSA + lSP + lSD).toMutableList(), navController = navController)
            } else if (showA.value && showP.value && !showD.value) {
                CategoriesLazyColumn(categorii = (lSA + lSP).toMutableList(), navController = navController)
            } else if (showA.value && !showP.value && showD.value) {
                CategoriesLazyColumn(categorii = (lSA + lSD).toMutableList(), navController = navController)
            } else if (!showA.value && showP.value && showD.value) {
                CategoriesLazyColumn(categorii = (lSP + lSD).toMutableList(), navController = navController)
            }
        }
    }
}