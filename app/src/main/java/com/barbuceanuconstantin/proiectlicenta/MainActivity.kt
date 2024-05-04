package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint

//Branch ecrane-individuale//
private var listSubcategoriesRevenue = subcategorysPredefiniteActive.map {
    Categories(id = 0, name = it, mainCategory = "Active")
}.toMutableStateList()
private var listSubcategoriesExpenses = subcategorysPredefinitePasive.map {
    Categories(id = 0, name = it, mainCategory = "Pasive")
}.toMutableStateList()
private var listSubcategoriesDebts = subcategorysPredefiniteDatorii.map {
    Categories(id = 0, name = it, mainCategory = "Datorii")
}.toMutableStateList()
fun initHardcodedBudgets(lBudgets: SnapshotStateList<Budgets>) {
    lBudgets.add(Budgets(id = 1, startDate = "2024-04-21", endDate = "2024-04-21", name = "ccc", upperTreshold = 2f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 2, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 3, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 4, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 5, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 6, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 7, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 8, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 9, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
    lBudgets.add(Budgets(id = 10, startDate = "2024-04-21", endDate = "2024-04-21", name = "---", upperTreshold = 0f.toDouble(), category = "Divertisment"))
}
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                val lTrA = mutableStateListOf<Transactions>()
                val lTrP = mutableStateListOf<Transactions>()
                val lTrD = mutableStateListOf<Transactions>()
                val lBudgets = mutableStateListOf<Budgets>()

                initHardcodedTransactions(lTrA, lTrP, lTrD)
                initHardcodedBudgets(lBudgets)

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

                        //Categorii
                        CategoriesComposableScreen(
                            listSubcategoriesRevenue,
                            listSubcategoriesExpenses,
                            listSubcategoriesDebts,
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
                            updateState = updateStateMainScreen
                        )
                    }
                    composable("fixedBudgetsScreen") {
                        val viewModel = hiltViewModel<FixedBudgetsScreenViewModel>()
                        val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
                        val updateState: (Boolean) -> Unit = { buttons ->
                            viewModel.onStateChangedButtons(buttons)
                        }

                        //Bugete fixe
                        FixedBudgetsComposableScreen(
                            lBudgets, navController,
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
                            updateStateButtons = updateState
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

                        val index = requireNotNull(backStackEntry.arguments).getInt("index")
                        var lambda = {
                            navController.navigate("homeScreen") {
                                popUpTo("homeScreen") {
                                    inclusive = true
                                }
                            }
                        }

                        if (index == 3) {
                            lambda = {
                                navController.navigate("transactionScreen") {
                                    popUpTo("transactionScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        } else if (index == 4) {
                            lambda = {
                                navController.navigate("budgetSummaryScreen") {
                                    popUpTo("budgetSummaryScreen") {
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
                                                index = index,
                                                transaction = transactionObject
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

                        EditCategoryScreen(
                            category = categoryObject,
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
                            }
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
                            budget = budgetObject
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
                            updateButtons = updateButtons
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
