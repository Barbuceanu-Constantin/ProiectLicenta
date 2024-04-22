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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.BudgetsLazyColumn
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MoreScreensMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedBudgetsComposableScreen(
    lFixedBudgets: SnapshotStateList<Budget>,
    navController: NavController,
    onNavigateToEditBudgetScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToTransactionScreen: () -> Unit,
    onNavigateToCategoriesScreen: () -> Unit,
    onNavigateToFixedBudgetsScreen: () -> Unit
) {
    val buttons: MutableState<Boolean> = remember { mutableStateOf(false)}

    Scaffold(
        floatingActionButton = { FloatingActionButtonCustom(
                                                            navigateAction = onNavigateToEditBudgetScreen,
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
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.bugete))
                },
                actions = {
                    MoreScreensMenu()
                }
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            BudgetsLazyColumn(lFixedBudgets, buttons, navController)
        }
    }
}