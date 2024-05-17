package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.di.PrincipalScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.navigation.AppNavHost
import com.barbuceanuconstantin.proiectlicenta.navigation.homeScreen
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun CoroutineScope.insertPredefinedCategory(viewModel: PrincipalScreenViewModel,
                                            categoryName: String,
                                            mainCategory: String
) = launch {
    viewModel.budgetTrackerRepository.insertCategory(Categories(mainCategory = mainCategory, name = categoryName))
}

// Extension function to check if the categories list is empty or not
suspend fun isCategoriesEmpty(viewModel: PrincipalScreenViewModel): Boolean {
    return withContext(Dispatchers.IO) {
        val categories = viewModel.budgetTrackerRepository.getAllCategories()
        categories.isEmpty()
    }
}

fun runInitCategoryLists(viewModel: PrincipalScreenViewModel) {
    runBlocking {
        val isEmpty = isCategoriesEmpty(viewModel)

        if (isEmpty) {
            for (category in subcategorysPredefiniteActive) {
                CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                    viewModel = viewModel,
                    categoryName = category,
                    mainCategory = "Active"
                )
            }
            for (category in subcategorysPredefinitePasive) {
                CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                    viewModel = viewModel,
                    categoryName = category,
                    mainCategory = "Pasive"
                )
            }
            for (category in subcategorysPredefiniteDatorii) {
                CoroutineScope(Dispatchers.Default).insertPredefinedCategory(
                    viewModel = viewModel,
                    categoryName = category,
                    mainCategory = "Datorii"
                )
            }
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
