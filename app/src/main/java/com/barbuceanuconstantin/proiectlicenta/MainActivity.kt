package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.di.BudgetSummaryScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.CalendarScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.EditBudgetScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.EditCategoryScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.EditTransactionScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.GraphsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.MementosScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.PrincipalScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.di.TransactionsScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.BudgetSummaryComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.CalendarComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditBudgetScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditCategoryScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditTransactionScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.FixedBudgetsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.GraphsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.MementosComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.listaSubcategorysActive
import com.barbuceanuconstantin.proiectlicenta.view.screen.listaSubcategorysDatorii
import com.barbuceanuconstantin.proiectlicenta.view.screen.listaSubcategorysPasive
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers

fun initHardcodedTransactions(lTrA: SnapshotStateList<Transactions>,
                              lTrP: SnapshotStateList<Transactions>,
                              lTrD: SnapshotStateList<Transactions>) {
    lTrA.add(Transactions(value = 2F.toDouble(), date = "", description = "fd", payee = "fd", category = "Salariu", id = 1, budgetId = 1))
    lTrA.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Salariu", id = 2, budgetId = 1))
    lTrA.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Salariu", id = 3, budgetId = 1))
    lTrA.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Salariu", id = 4, budgetId = 1))
    lTrA.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Salariu", id = 5, budgetId = 1))

    lTrP.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Mancare", id = 1, budgetId = 1))
    lTrP.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Mancare", id = 2, budgetId = 1))
    lTrP.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Mancare", id = 3, budgetId = 1))
    lTrP.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Mancare", id = 4, budgetId = 1))
    lTrP.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Mancare", id = 5, budgetId = 1))

    lTrD.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Credit1", id = 1, budgetId = 1))
    lTrD.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Credit1", id = 2, budgetId = 1))
    lTrD.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Credit1", id = 3, budgetId = 1))
    lTrD.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Credit1", id = 4, budgetId = 1))
    lTrD.add(Transactions(value = 0F.toDouble(), date = "", description = "", payee = "", category = "Credit1", id = 5, budgetId = 1))
}

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

fun CoroutineScope.launchGetBudgetsLists(viewModel: FixedBudgetsScreenViewModel) = launch {
    viewModel.onStateChangedList()
}
fun runGetBudgetsLists(viewModel: FixedBudgetsScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetBudgetsLists(viewModel)
    }
}

fun CoroutineScope.launchGetCategoriesLists(viewModel: CategoriesScreenViewModel) = launch {
    viewModel.onStateChangedLists()
}

fun runGetCategoriesLists(viewModel: CategoriesScreenViewModel) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchGetCategoriesLists(viewModel)
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProiectLicentaTheme {
                val lTrA = mutableStateListOf<Transactions>()
                val lTrP = mutableStateListOf<Transactions>()
                val lTrD = mutableStateListOf<Transactions>()

                runInitCategoryLists(hiltViewModel<PrincipalScreenViewModel>())

                initHardcodedTransactions(lTrA, lTrP, lTrD)

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable("homeScreen")
                    {
                        val viewModel = hiltViewModel<PrincipalScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateState: (Int) -> Unit = { selectedIndex ->
                            viewModel.onStateChanged(selectedIndex)
                        }

                        //Ecran principal
                        PrincipalComposableScreen(
                            onNavigateToEditTransactionScreen = {index ->
                                                                    navController.navigate("editTransactionScreen/$index")
                                                                },
                            onNavigateToHomeScreen = { },
                            onNavigateToTransactionScreen = {
                                                    navController.navigate("transactionScreen") {
                                                        popUpTo("transactionScreen") {
                                                            inclusive = true
                                                        }
                                                    }
                                                },
                            onNavigateToCategoriesScreen = {
                                                    navController.navigate("categoriesScreen") {
                                                        popUpTo("categoriesScreen") {
                                                            inclusive = true
                                                        }
                                                    }
                                                },
                            onNavigateToFixedBudgetsScreen = {
                                                    navController.navigate("fixedBudgetsScreen") {
                                                        popUpTo("fixedBudgetsScreen") {
                                                            inclusive = true
                                                        }
                                                    }
                                                },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            principalScreenUIState = state,
                            updateState = updateState
                        )
                    }
                    composable("transactionScreen") {
                        val viewModel = hiltViewModel<TransactionsScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

                        // Define a function that can be called to update the state
                        val updateStateMainScreen: (Boolean, Boolean, Boolean) -> Unit = { showA, showP, showD ->
                            viewModel.onStateChangedMainScreen(showA, showP, showD)
                        }
                        val updateStateButtons: (Boolean) -> Unit = { buttons ->
                            viewModel.onStateChangedButtons(buttons)
                        }

                        //Tranzactii
                        TransactionsComposableScreen(
                            lTrA, lTrP, lTrD, navController,
                            onNavigateToEditTransactionScreen = {index ->
                                navController.navigate("editTransactionScreen/$index")
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = { },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            transactionsScreenUIState = state,
                            updateStateMainScreen = updateStateMainScreen,
                            updateStateButtons = updateStateButtons
                        )
                    }
                    composable("categoriesScreen") {
                        val viewModel = hiltViewModel<CategoriesScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateStateMainScreen: (Boolean, Boolean, Boolean) -> Unit = { showA, showP, showD ->
                            viewModel.onStateChangedMainScreen(showA, showP, showD)
                        }
                        val deleteByName: (String) -> Unit = { name ->
                            viewModel.onDeleteByName(name)
                        }

                        runGetCategoriesLists(viewModel)

                        //Categorii
                        CategoriesComposableScreen(
                            state.categoriesA,
                            state.categoriesP,
                            state.categoriesD,
                            navController,
                            onNavigateToEditCategoriesScreen = {
                                navController.navigate("editCategoryScreen")
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = { },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            categoriesScreenUIState = state,
                            updateState = updateStateMainScreen,
                            deleteByNameCoroutine = deleteByName
                        )
                    }
                    composable("fixedBudgetsScreen") {
                        val viewModel = hiltViewModel<FixedBudgetsScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateState: (Boolean) -> Unit = { buttons ->
                            viewModel.onStateChangedButtons(buttons)
                        }
                        val deleteByName: (String) -> Unit = { name ->
                            viewModel.onDeleteByName(name)
                        }

                        runGetBudgetsLists(viewModel)

                        //Bugete fixe
                        FixedBudgetsComposableScreen(
                            state.budgets,
                            navController,
                            onNavigateToEditBudgetScreen = {
                                navController.navigate("editBudgetScreen")
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = { },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            fixedBudgetsScreenUIState = state,
                            updateStateButtons = updateState,
                            deleteByNameCoroutine = deleteByName
                        )
                    }
                    composable("editTransactionScreen/{index}?transaction={transaction}",
                                arguments = listOf(navArgument("index") {
                                                        type = NavType.IntType
                                                        defaultValue = 0
                                                    },
                                                    navArgument("transaction") {
                                                        nullable = true
                                                    }
                                )
                    )
                    {backStackEntry ->
                        // Creating gson object
                        val gson: Gson = GsonBuilder().create()
                        /* Extracting the user object json from the route */
                        val transactionJson = backStackEntry.arguments?.getString("transaction")
                        // Convert json string to the User data class object
                        val transactionObject = gson.fromJson(transactionJson, Transactions::class.java)

                        val viewModel = hiltViewModel<EditTransactionScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateState: (Boolean, Boolean, Boolean) -> Unit = { showA, showP, showD ->
                            viewModel.onStateChanged(showA, showP, showD)
                        }
                        val updateDate: (String) -> Unit = { date ->
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

                        val index = requireNotNull(backStackEntry.arguments).getInt("index")

                        if (transactionObject != null) {
                            viewModel.onAddTransaction(transactionObject)
                            if (!state.showA && !state.showP && !state.showD) {
                                updateCategory(transactionObject.category)
                                updatePayee(transactionObject.payee)
                                updateValueSum(transactionObject.value.toString())
                                updateDescription(transactionObject.description)
                                updateDate(transactionObject.date)

                                if (listaSubcategorysActive.contains(transactionObject.category))
                                    updateState(true, false, false)
                                else if (listaSubcategorysPasive.contains(transactionObject.category))
                                    updateState(false, true, false)
                                else if (listaSubcategorysDatorii.contains(transactionObject.category))
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
                            navController.navigate("homeScreen") {
                                popUpTo("homeScreen") {
                                    inclusive = true
                                }
                            }
                        }

                        if (index == returnToTransactionIndex) {
                            lambda = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
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
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        EditTransactionScreen(
                                                onNavigateToBackScreen = lambda,
                                                onNavigateToHomeScreen = {
                                                    navController.navigate("homeScreen") {
                                                        popUpTo("homeScreen") {
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
                                                updateTransaction = updateTransaction
                        )
                    }
                    composable("editCategoryScreen?category={category}")
                    { backStackEntry ->
                        // Creating gson object
                        val gson: Gson = GsonBuilder().create()
                        /* Extracting the user object json from the route */
                        val categoryJson = backStackEntry.arguments?.getString("category")
                        // Convert json string to the Category data class object
                        val categoryObject = gson.fromJson(categoryJson, Categories::class.java)

                        val viewModel = hiltViewModel<EditCategoryScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
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
                        val insertCategory: suspend (Categories) -> Unit = { category ->
                            viewModel.insertCategory(category)
                        }
                        val updateCategoryInDb: suspend (category : Categories) -> Unit = {
                            category -> viewModel.updateCategoryInDb(category)
                        }

                        //Add the category in state.
                        if(categoryObject != null) {
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
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
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
                            updateReadyToGo = updateReadyToGo
                        )
                    }
                    composable("editBudgetScreen?budget={budget}")
                    {backStackEntry ->
                        // Creating gson object
                        val gson: Gson = GsonBuilder().create()
                        /* Extracting the budget object json from the route */
                        val budgetJson = backStackEntry.arguments?.getString("budget")
                        // Convert json string to the Budget data class object
                        val budgetObject = gson.fromJson(budgetJson, Budgets::class.java)

                        val viewModel = hiltViewModel<EditBudgetScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateDate1: (String) -> Unit = { date1 ->
                            viewModel.onUpdateDate1(date1)
                        }
                        val updateDate2: (String) -> Unit = { date2 ->
                            viewModel.onUpdateDate2(date2)
                        }
                        val updateFilledText: (String) -> Unit = { filledText ->
                            viewModel.onUpdateFilledText(filledText)
                        }
                        val updateCategory: (String) -> Unit = { category ->
                            viewModel.onUpdateCategory(category)
                        }
                        val updateValueSum: (String) -> Unit = { valueSum ->
                            viewModel.onUpdateValueSum(valueSum)
                        }
                        val updateOpenWarningDialog: (Boolean, Int) -> Unit = {
                                                                                openWarningDialog,
                                                                                idWarningString ->
                            viewModel.onUpdateOpenWarningDialog(openWarningDialog, idWarningString)
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

                        if(budgetObject != null) {
                            if (state.budget == null) {
                                viewModel.onAddBudget(budgetObject)
                                if (state.date1 == state.formattedDate)
                                    updateDate1(budgetObject.startDate)
                                if (state.date2 == state.formattedDate)
                                    updateDate2(budgetObject.endDate)
                                if (state.filledText == "")
                                    updateFilledText(budgetObject.name)
                                if (state.category == "")
                                    updateCategory(budgetObject.categoryName)
                                if (state.valueSum == "")
                                    updateValueSum(budgetObject.upperThreshold.toString())
                            }
                        }

                        EditBudgetScreen(
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
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
                            updateCoroutine = updateBudget
                        )
                    }
                    composable("budgetSummaryScreen") {
                        val viewModel = hiltViewModel<BudgetSummaryScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        //Aici vin functiile pe care trebuie sa le extrag.
                        val updateStateDateButton: (Boolean) -> Unit = { dateButton ->
                            viewModel.onStateChangedDateButton(dateButton)
                        }
                        val updateStateMonth: (String) -> Unit = { month ->
                            viewModel.onStateChangedMonth(month)
                        }
                        val updateStateTimeInterval: (Boolean, Boolean, Boolean) -> Unit = { daily, weekly, monthly ->
                                viewModel.onStateChangedTimeInterval(daily, weekly, monthly)
                        }
                        val updateStateDate: (String) -> Unit = { date ->
                            viewModel.onStateChangedDate(date)
                        }
                        val updateStateButtons: (Boolean) -> Unit = { buttons ->
                            viewModel.onStateChangedButtons(buttons)
                        }

                        //Bugete fixe
                        BudgetSummaryComposableScreen(
                            lTrA, lTrP,
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = { },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            navController = navController,
                            budgetSummaryScreenUIState = state,
                            updateStateDateButton = updateStateDateButton,
                            updateStateMonth = updateStateMonth,
                            updateStateTimeInterval = updateStateTimeInterval,
                            updateStateDate = updateStateDate,
                            updateStateButtons = updateStateButtons,
                        )
                    }
                    composable("calendarScreen") {
                        val viewModel = hiltViewModel<CalendarScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateDate: (String) -> Unit = { date ->
                            viewModel.onStateChangedDate(date)
                        }
                        val updateIncomesExpenses: (Boolean, Boolean) -> Unit = { incomes, expenses ->
                            viewModel.onStateChangedIncomesExpenses(incomes, expenses)
                        }
                        val updateButtons: (Boolean) -> Unit = { buttons ->
                            viewModel.onStateChangedButtons(buttons)
                        }

                        //Bugete fixe
                        CalendarComposableScreen(
                            lTrA, lTrP,
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            calendarScreenUIState = state,
                            updateDate = updateDate,
                            updateIncomesExpenses = updateIncomesExpenses,
                            updateButtons = updateButtons,
                            navController = navController
                        )
                    }
                    composable("graphsScreen") {
                        val viewModel = hiltViewModel<GraphsScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

                        GraphsComposableScreen(
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToGraphsScreen = { },
                            onNavigateToMementosScreen = {
                                navController.navigate("mementosScreen") {
                                    popUpTo("mementosScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            graphsScreenUIState = state
                        )
                    }
                    composable("mementosScreen") {
                        val viewModel = hiltViewModel<MementosScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

                        MementosComposableScreen(
                            onNavigateToHomeScreen = {
                                navController.navigate("homeScreen") {
                                    popUpTo("homeScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToTransactionScreen = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCategoriesScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToBudgetSummaryScreen = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToCalendarScreen = {
                                navController.navigate("calendarScreen") {
                                    popUpTo("calendarScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToMementosScreen = { },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            mementosScreenUIState = state
                        )
                    }
                    // Add more destinations similarly.
                }
            }
        }
    }
}
