package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditBudgetScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditCategoryScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.EditTransactionScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.FixedBudgetsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsComposableScreen


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
fun InitHardcodedBudgets(lBudgets: SnapshotStateList<Budget>) {
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
    lBudgets.add(Budget("---", "---", "---", 0f.toDouble()))
}
fun initHardcodedTransactions(lTrA: SnapshotStateList<Transaction>,
                              lTrP: SnapshotStateList<Transaction>,
                              lTrD: SnapshotStateList<Transaction>) {
    lTrA.add(Transaction(suma = 2F.toDouble(), data = "fd", descriere = "fd", payee = "fd", subcategory = "Salariu"))
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
                InitHardcodedBudgets(lBudgets)

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
                                                    }
                        )
                    }
                    composable("transactionScreen") {
                        //Tranzactii
                        TransactionsComposableScreen(
                            lTrA, lTrP, lTrD,
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
                            }
                        )
                    }
                    composable("categoriesScreen") {
                        //Categorii
                        CategoriesComposableScreen(
                            listSubcategoriesRevenue,
                            listSubcategoriesExpenses,
                            listSubcategoriesDebts,
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
                            }
                        )
                    }
                    composable("fixedBudgetsScreen") {
                        //Bugete fixe
                        FixedBudgetsComposableScreen(
                            lBudgets,
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
                            onNavigateToFixedBudgetsScreen = { }
                        )
                    }
                    composable("editTransactionScreen/{index}",
                                arguments = listOf(navArgument("index") {
                                                        type = NavType.IntType
                                                        defaultValue = 0
                                                    }
                                )
                    )
                    {backStackEntry ->
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
                        }

                        EditTransactionScreen(
                                                onNavigateToHomeScreen = lambda,
                                                index = index
                        )
                    }
                    composable("editCategoryScreen")
                    {
                        EditCategoryScreen(
                            onNavigateToCategoryScreen = {
                                navController.navigate("categoriesScreen") {
                                    popUpTo("categoriesScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("editBudgetScreen")
                    {
                        EditBudgetScreen(
                            onNavigateToFixedBudgetsScreen = {
                                navController.navigate("fixedBudgetsScreen") {
                                    popUpTo("fixedBudgetsScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    // Add more destinations similarly.
                }
            }
        }
    }
}
