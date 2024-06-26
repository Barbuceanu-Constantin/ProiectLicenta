package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.di.EditBudgetScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditBudgetScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date

fun CoroutineScope.launchGetCategoriesExpensesList(viewModel: EditBudgetScreenViewModel) = launch {
    viewModel.updateExpensesList()
}
fun runGetCategoryExpensesList(viewModel: EditBudgetScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesExpensesList(viewModel)
    }
}
@Composable
fun EditBudgetScreenDestination(
    viewModel: EditBudgetScreenViewModel = hiltViewModel<EditBudgetScreenViewModel>(),
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    // Creating gson object
    val gson: Gson = GsonBuilder().create()
    /* Extracting the budget object json from the route */
    val budgetJson = backStackEntry.arguments?.getString("budget")
    // Convert json string to the Budget data class object
    val budgetObject = gson.fromJson(budgetJson, Budgets::class.java)

    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    runGetCategoryExpensesList(viewModel)

    val updateDate1: (Date) -> Unit = { date1 ->
        viewModel.onUpdateDate1(date1)
    }
    val updateDate2: (Date) -> Unit = { date2 ->
        viewModel.onUpdateDate2(date2)
    }
    val updateFilledText: (String) -> Unit = { filledText ->
        viewModel.onUpdateFilledText(filledText)
    }
    val updateCategory: (Int) -> Unit = { category ->
        viewModel.onUpdateCategory(category)
    }
    val updateValueSum: (String) -> Unit = { valueSum ->
        viewModel.onUpdateValueSum(valueSum)
    }
    val updateOpenWarningDialog: (Boolean, Int) -> Unit = { openWarningDialog,
                                                            idWarningString ->
        viewModel.onUpdateOpenWarningDialog(openWarningDialog, idWarningString)
    }
    val updateAlertDialog: (Boolean) -> Unit = { update ->
        viewModel.onUpdateAlertDialog(update)
    }
    val nullCheckFields: () -> Boolean = {
        viewModel.nullCheckFields()
    }
    val checkNameExistence: (String) -> Boolean = { name ->
        viewModel.nameCheckExistence(name)
    }
    val addBudget: (Budgets) -> Unit = { budget ->
        viewModel.onAddBudget(budget)
    }
    val insertBudget: suspend (Budgets) -> Unit = { budget ->
        viewModel.insertBudget(budget)
    }
    val updateBudget: suspend (Budgets) -> Unit = { budget ->
        viewModel.updateBudgetInDb(budget)
    }
    val updateReadyToGo: (Boolean) -> Unit = { readyToGo ->
        viewModel.onUpdateReadyToGo(readyToGo)
    }
    val updateCategoryNameSimple: (String, String) -> Unit = { name, main ->
        viewModel.onUpdateCategoryNameSimple(name, main)
    }

    if (budgetObject != null) {
        if (state.budget == null) {
            viewModel.onAddBudget(budgetObject)
            if (state.date1 == state.formattedDate)
                updateDate1(budgetObject.startDate)
            if (state.date2 == state.formattedDate)
                updateDate2(budgetObject.endDate)
            if (state.filledText == "")
                updateFilledText(budgetObject.name)
            if (state.category == 0) {
                updateCategory(budgetObject.categoryId)
                viewModel.onUpdateCategoryName(budgetObject.categoryId)
            }
            if (state.valueSum == "")
                updateValueSum(budgetObject.upperThreshold.toString())
        }
    }

    EditBudgetScreen(
        onNavigateToFixedBudgetsScreen = {
            navController.navigate(fixedBudgetsScreen) {
                popUpTo(fixedBudgetsScreen) {
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
        onUpdateDate1 = updateDate1,
        onUpdateDate2 = updateDate2,
        onUpdateFilledText = updateFilledText,
        onUpdateCategory = updateCategory,
        onUpdateValueSum = updateValueSum,
        onUpdateOpenWarningDialog = updateOpenWarningDialog,
        addBudget = addBudget,
        editBudgetScreenUIState = state,
        updateReadyToGo = updateReadyToGo,
        insertCoroutine = insertBudget,
        updateCoroutine = updateBudget,
        updateAlertDialog = updateAlertDialog,
        nullCheckFields = nullCheckFields,
        updateCategoryNameSimple = updateCategoryNameSimple,
        checkNameExistence = checkNameExistence
    )
}