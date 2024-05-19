package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.di.EditCategoryScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditCategoryScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchGetCategoriesLists(viewModel: EditCategoryScreenViewModel) = launch {
    viewModel.onUpdateCategoryLists()
}
fun runGetCategoriesLists(viewModel: EditCategoryScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesLists(viewModel)
    }
}
@Composable
fun EditCategoryScreenDestination(
    viewModel: EditCategoryScreenViewModel = hiltViewModel<EditCategoryScreenViewModel>(),
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    // Creating gson object
    val gson: Gson = GsonBuilder().create()
    /* Extracting the user object json from the route */
    val categoryJson = backStackEntry.arguments?.getString("category")
    // Convert json string to the Category data class object
    val categoryObject = gson.fromJson(categoryJson, Categories::class.java)

    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    println("DADADA3 " + state.readyToGo + "   " + state.alertDialog)
    runGetCategoriesLists(viewModel)

    val updateState: (Boolean, Boolean, Boolean) -> Unit = { showA, showP, showD ->
        viewModel.onStateChanged(showA, showP, showD)
    }
    val updateFilledText: (String) -> Unit = { filledText ->
        viewModel.onStateChangedFilledText(filledText)
    }
    val updateCategory: (Categories) -> Unit = { category ->
        viewModel.onAddCategory(category)
    }
    val updateReadyToGo: (Boolean) -> Unit = { readyToGo ->
        viewModel.onUpdateReadyToGo(readyToGo)
    }
    val updateAlertDialog: (Boolean) -> Unit = { update ->
        viewModel.onUpdateAlertDialog(update)
    }
    val updateAlertAlreadyExistDialog: (Boolean) -> Unit = { update ->
        viewModel.onUpdateAlertAlreadyExistDialog(update)
    }
    val nullCheckFields: () -> Boolean = {
        viewModel.nullCheckFields()
    }
    val insertCategory: suspend (Categories) -> Unit = { category ->
        viewModel.insertCategory(category)
    }
    val updateCategoryInDb: suspend (category: Categories) -> Unit = { category ->
        viewModel.updateCategoryInDb(category)
    }

    //Add the category in state.
    if (categoryObject != null) {
        viewModel.onAddCategory(categoryObject)
        if (state.showA && !state.showP && !state.showD && !state.readyToUpdate) {
            if (categoryObject.mainCategory == "Active")
                updateState(true, false, false)
            else if (categoryObject.mainCategory == "Pasive")
                updateState(false, true, false)
            else if (categoryObject.mainCategory == "Datorii")
                updateState(false, false, true)
            updateFilledText(categoryObject.name)
            viewModel.onUpdateReadyToUpdate(true)
        }
    }

    EditCategoryScreen(
        onNavigateToCategoryScreen = {
            navController.navigate(categoriesScreen) {
                popUpTo(categoriesScreen) {
                    inclusive = true
                }
            }
        },
        onNavigateToHomeScreen = {
            navController.navigate(homeScreen) {
                popUpTo(homeScreen) {
                    inclusive = true
                }
            }
        },
        editCategoryScreenUIState = state,
        updateState = updateState,
        onStateChangedFilledText = updateFilledText,
        onAddCategory = updateCategory,
        insertCoroutine = insertCategory,
        updateCoroutine = updateCategoryInDb,
        updateReadyToGo = updateReadyToGo,
        nullCheckFields = nullCheckFields,
        updateAlertAlreadyExistDialog = updateAlertAlreadyExistDialog,
        updateAlertDialog = updateAlertDialog
    )
}