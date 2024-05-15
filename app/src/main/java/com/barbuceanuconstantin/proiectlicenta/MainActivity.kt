package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.di.BudgetSummaryScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.EditBudgetScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.EditTransactionScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.PrincipalScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.navigation.AppNavHost
import com.barbuceanuconstantin.proiectlicenta.navigation.homeScreen
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers

fun CoroutineScope.insertPredefinedCategory(viewModel: PrincipalScreenViewModel,
                                            categoryName: String,
                                            mainCategory: String
) = launch {
    viewModel.budgetTrackerRepository.insertCategory(Categories(mainCategory = mainCategory, name = categoryName))
}

fun runInitCategoryLists(viewModel: PrincipalScreenViewModel) {
    for (category in subcategorysPredefiniteActive) {
        runBlocking {
            CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                viewModel = viewModel,
                categoryName = category,
                mainCategory = "Active"
            )
        }
    }
    for (category in subcategorysPredefinitePasive) {
        runBlocking {
            CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                viewModel = viewModel,
                categoryName = category,
                mainCategory = "Pasive"
            )
        }
    }
    for (category in subcategorysPredefiniteDatorii) {
        runBlocking {
            CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                viewModel = viewModel,
                categoryName = category,
                mainCategory = "Datorii"
            )
        }
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProiectLicentaTheme {
                runInitCategoryLists(hiltViewModel<PrincipalScreenViewModel>())

                val navController = rememberNavController()
                AppNavHost(navController = navController, startDestination = homeScreen)
            }
        }
    }
}
