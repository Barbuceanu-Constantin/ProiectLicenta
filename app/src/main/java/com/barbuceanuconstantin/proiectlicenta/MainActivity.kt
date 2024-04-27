package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.BudgetSummaryComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.CalendarComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditBudgetScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditCategoryScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditTransactionScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.FixedBudgetsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.GraphsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsComposableScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//Branch ecrane-individuale//
private var listSubcategoriesRevenue = subcategorysPredefiniteActive.map {
    Category(name = it)
}.toMutableStateList()
private var listSubcategoriesExpenses = subcategorysPredefinitePasive.map {
    Category(name = it)
}.toMutableStateList()
private var listSubcategoriesDebts = subcategorysPredefiniteDatorii.map {
    Category(name = it)
}.toMutableStateList()
fun initHardcodedBudgets(lBudgets: SnapshotStateList<Budget>) {
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "ccc", 2f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
    lBudgets.add(Budget("2024-04-21", "2024-04-21", "---", 0f.toDouble(), "Divertisment"))
}
fun initHardcodedTransactions(lTrA: SnapshotStateList<Transaction>,
                              lTrP: SnapshotStateList<Transaction>,
                              lTrD: SnapshotStateList<Transaction>) {
    lTrA.add(Transaction(suma = 2F.toDouble(), data = "", descriere = "fd", payee = "fd", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Salariu"))
    lTrA.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Salariu"))

    lTrP.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Mancare"))
    lTrP.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Mancare"))

    lTrD.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Credit1"))
    lTrD.add(Transaction(suma = 0F.toDouble(), data = "", descriere = "", payee = "", subcategory = "Credit1"))
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                val lTrA = mutableStateListOf<Transaction>()
                val lTrP = mutableStateListOf<Transaction>()
                val lTrD = mutableStateListOf<Transaction>()
                val lBudgets = mutableStateListOf<Budget>()

                initHardcodedTransactions(lTrA, lTrP, lTrD)
                initHardcodedBudgets(lBudgets)

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable("homeScreen")
                    {
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
                        )
                    }
                    composable("transactionScreen") {
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
                        )
                    }
                    composable("categoriesScreen") {
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
                        )
                    }
                    composable("fixedBudgetsScreen") {
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
                        val transactionObject = gson.fromJson(transactionJson, Transaction::class.java)

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
                        val categoryObject = gson.fromJson(categoryJson, Category::class.java)

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
                        val budgetObject = gson.fromJson(budgetJson, Budget::class.java)

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
                            navController = navController
                        )
                    }
                    composable("calendarScreen") {
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
                            onNavigateToCalendarScreen = { },
                            onNavigateToGraphsScreen = {
                                navController.navigate("graphsScreen") {
                                    popUpTo("graphsScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("graphsScreen") {
                        //Bugete fixe
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
                            onNavigateToGraphsScreen = { }
                        )
                    }
                    // Add more destinations similarly.
                }
            }
        }
    }
}
