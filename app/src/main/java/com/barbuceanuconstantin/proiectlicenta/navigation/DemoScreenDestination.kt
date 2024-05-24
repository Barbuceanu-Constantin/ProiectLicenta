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
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun CoroutineScope.insertMainCategory(
    viewModel: DemoScreenViewModel,
    name: String
) = launch {
    viewModel.budgetTrackerRepository.insertMainCategory(MainCategories(name = name))
}


fun CoroutineScope.insertPredefinedCategory(
    viewModel: DemoScreenViewModel,
    categoryName: String,
    mainCategory: String
) = launch {
    viewModel.budgetTrackerRepository.insertCategory(Categories(mainCategory = mainCategory, name = categoryName))
}

// Extension function to check if the categories list is empty or not
suspend fun isMainCategoriesEmpty(viewModel: DemoScreenViewModel): Boolean {
    return withContext(Dispatchers.IO) {
        val categories = viewModel.budgetTrackerRepository.getAllMainCategories()
        categories.isEmpty()
    }
}

fun runInitCategoryLists(viewModel: DemoScreenViewModel) {
    runBlocking {
        val isEmpty = isMainCategoriesEmpty(viewModel)

        if (isEmpty) {
            // Insert main categories concurrently
            val mainCategories = listOf("Active", "Pasive", "Datorii")
            val mainCategoryJobs = mainCategories.map { name ->
                launch(Dispatchers.Default) {
                    viewModel.budgetTrackerRepository.insertCategory(Categories(mainCategory = name, name = name))
                }
            }
            mainCategoryJobs.joinAll()

            // Insert predefined subcategories concurrently for each main category
            val predefinedCategories = mapOf(
                "Active" to subcategorysPredefiniteActive,
                "Pasive" to subcategorysPredefinitePasive,
                "Datorii" to subcategorysPredefiniteDatorii
            )

            val subCategoryJobs = predefinedCategories.flatMap { (mainCategory, subcategories) ->
                subcategories.map { categoryName ->
                    launch(Dispatchers.Default) {
                        viewModel.budgetTrackerRepository.insertCategory(Categories(mainCategory = mainCategory, name = categoryName))
                    }
                }
            }
            subCategoryJobs.joinAll()
        }
    }
}
@Composable
fun DemoScreenDestination(
    viewModel: DemoScreenViewModel = hiltViewModel<DemoScreenViewModel>(),
    navController: NavHostController,
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val onInitCategoryLists = {
        runInitCategoryLists(viewModel)
    }
    val onDeleteTables: (onInitCategoryLists: () -> Unit) -> Unit = { it ->
        viewModel.onDeleteTables(it)
    }
    val updateTablesForDemo: () -> Unit = {
        viewModel.updateTablesForDemo()
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
        updateTablesForDemo = updateTablesForDemo
    )
}