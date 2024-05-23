package com.barbuceanuconstantin.proiectlicenta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val demoScreen = "demoScreen"
const val homeScreen = "homeScreen"
const val transactionScreen = "transactionScreen"
const val categoriesScreen = "categoriesScreen"
const val fixedBudgetsScreen = "fixedBudgetsScreen"
const val budgetSummaryScreen = "budgetSummaryScreen"
const val calendarScreen = "calendarScreen"
const val graphsScreen = "graphsScreen"
const val mementosScreen = "mementosScreen"

const val editTransactionScreenFullPath = "editTransactionScreen/{index}?transaction={transaction}"
const val editCategoryScreenFullPath = "editCategoryScreen?category={category}"
const val editCategoryScreenShortPath = "editCategoryScreen"
const val editBudgetScreenFullPath = "editBudgetScreen?budget={budget}"
const val editBudgetScreenShortPath = "editBudgetScreen"

@Composable
fun AppNavHost(navController: NavHostController,
               startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(demoScreen)
        {
            DemoScreenDestination(navController = navController)
        }
        composable(homeScreen)
        {
            HomeScreenDestination(navController = navController)
        }
        composable(transactionScreen) {
            TransactionsScreenDestination(navController = navController)
        }
        composable(categoriesScreen) {
            CategoriesScreenDestination(navController = navController)
        }
        composable(fixedBudgetsScreen) {
            FixedBudgetsScreenDestination(navController = navController)
        }
        composable(
            editTransactionScreenFullPath,
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("transaction") {
                    nullable = true
                }
            )
        )
        { backStackEntry ->
            EditTransactionScreenDestination(
                                                navController = navController,
                                                backStackEntry = backStackEntry
            )
        }
        composable(editCategoryScreenFullPath)
        { backStackEntry ->
            EditCategoryScreenDestination(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }
        composable(editBudgetScreenFullPath)
        { backStackEntry ->
            EditBudgetScreenDestination(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }
        composable(budgetSummaryScreen) {
            BudgetSummaryScreenDestination(navController = navController)
        }
        composable(calendarScreen) {
            CalendarScreenDestination(navController = navController)
        }
        composable(graphsScreen) {
            GraphsScreenDestination(navController = navController)
        }
        composable(mementosScreen) {
            MementosScreenDestination(navController = navController)
        }
        // Add more destinations similarly.
    }
}
