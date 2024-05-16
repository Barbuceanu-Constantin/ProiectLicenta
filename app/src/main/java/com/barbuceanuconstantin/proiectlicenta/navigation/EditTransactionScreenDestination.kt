package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.di.EditTransactionScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.returnToBudgetSummaryIndex
import com.barbuceanuconstantin.proiectlicenta.returnToCalendarIndex
import com.barbuceanuconstantin.proiectlicenta.returnToTransactionIndex
import com.barbuceanuconstantin.proiectlicenta.showAIndex
import com.barbuceanuconstantin.proiectlicenta.showDIndex
import com.barbuceanuconstantin.proiectlicenta.showPIndex
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditTransactionScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date

fun CoroutineScope.launchGetCategoriesListsEditTransactionScreen(viewModel: EditTransactionScreenViewModel) = launch {
    viewModel.updateLists()
}
fun runGetCategoryListsEditTransactionScreen(viewModel: EditTransactionScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesListsEditTransactionScreen(viewModel)
    }
}
@Composable
fun EditTransactionScreenDestination(
    viewModel: EditTransactionScreenViewModel = hiltViewModel<EditTransactionScreenViewModel>(),
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    // Creating gson object
    val gson: Gson = GsonBuilder().create()
    /* Extracting the user object json from the route */
    val transactionJson = backStackEntry.arguments?.getString("transaction")
    // Convert json string to the User data class object
    val transactionObject = gson.fromJson(transactionJson, Transactions::class.java)

    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    runGetCategoryListsEditTransactionScreen(viewModel)

    val updateState: (Boolean, Boolean, Boolean) -> Unit = { showA, showP, showD ->
        viewModel.onStateChanged(showA, showP, showD)
    }
    val updateDate: (Date) -> Unit = { date ->
        viewModel.onUpdateDate(date)
    }
    val updatePayee: (String) -> Unit = { payee ->
        viewModel.onUpdatePayee(payee)
    }
    val updateDescription: (String) -> Unit = { description ->
        viewModel.onUpdateDescription(description)
    }
    val updateCategory: (String) -> Unit = { category ->
        viewModel.onUpdateCategory(category)
    }
    val updateValueSum: (String) -> Unit = { valueSum ->
        viewModel.onUpdateValueSum(valueSum)
    }
    val updateTransaction: (Transactions) -> Unit = { transaction ->
        viewModel.onAddTransaction(transaction)
    }
    val updateReadyToGo: (Boolean) -> Unit = { readyToGo ->
        viewModel.onUpdateReadyToGo(readyToGo)
    }
    val updateAlertDialog: (Boolean) -> Unit = { update ->
        viewModel.onUpdateAlertDialog(update)
    }
    val nullCheckFields: () -> Boolean = {
        viewModel.nullCheckFields()
    }
    val insertTransaction: suspend (Transactions) -> Unit = { transaction ->
        viewModel.insertTransaction(transaction)
    }
    val updateTransactionInDb: suspend (transaction: Transactions) -> Unit =
        { transaction ->
            viewModel.updateTransactionInDb(transaction)
        }

    val index = requireNotNull(backStackEntry.arguments).getInt("index")

    if (transactionObject != null) {
        viewModel.onAddTransaction(transactionObject)
        if (!state.showA && !state.showP && !state.showD) {
            updateCategory(transactionObject.categoryName)
            updatePayee(transactionObject.payee)
            updateValueSum(transactionObject.value.toString())
            updateDescription(transactionObject.description)
            updateDate(transactionObject.date)

            if (state.listCategoriesRevenue.any { it.name == transactionObject.categoryName })
                updateState(true, false, false)
            else if (state.listCategoriesExpenses.any { it.name == transactionObject.categoryName })
                updateState(false, true, false)
            else if (state.listCategoriesDebts.any { it.name == transactionObject.categoryName })
                updateState(false, false, true)
        }
    } else {
        when (index) {
            showAIndex -> updateState(true, false, false)
            showPIndex -> updateState(false, true, false)
            showDIndex -> updateState(false, false, true)
        }
    }

    var lambda = {
        navController.navigate(homeScreen) {
            popUpTo(homeScreen) {
                inclusive = true
            }
        }
    }

    if (index == returnToTransactionIndex) {
        lambda = {
            navController.navigate(transactionScreen) {
                popUpTo(transactionScreen) {
                    inclusive = true
                }
            }
        }
    } else if (index == returnToBudgetSummaryIndex) {
        lambda = {
            navController.navigate("budgetSummaryScreen") {
                popUpTo("budgetSummaryScreen") {
                    inclusive = true
                }
            }
        }
    } else if (index == returnToCalendarIndex) {
        lambda = {
            navController.navigate(calendarScreen) {
                popUpTo(calendarScreen) {
                    inclusive = true
                }
            }
        }
    }

    EditTransactionScreen(
        onNavigateToBackScreen = lambda,
        onNavigateToHomeScreen = {
            navController.navigate(homeScreen) {
                popUpTo(homeScreen) {
                    inclusive = true
                }
            }
        },
        editTransactionScreenUIState = state,
        updateState = updateState,
        updateCategory = updateCategory,
        updateValueSum = updateValueSum,
        updateDescription = updateDescription,
        updatePayee = updatePayee,
        updateDate = updateDate,
        updateTransaction = updateTransaction,
        updateReadyToGo = updateReadyToGo,
        updateAlertDialog = updateAlertDialog,
        nullCheckFields = nullCheckFields,
        insertCoroutine = insertTransaction,
        updateCoroutine = updateTransactionInDb
    )
}