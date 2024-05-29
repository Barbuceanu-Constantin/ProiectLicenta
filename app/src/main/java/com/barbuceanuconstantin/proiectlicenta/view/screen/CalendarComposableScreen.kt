package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.Balance
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.di.CalendarScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarComposableScreen(
    lTrA: List<CategoryAndTransactions>,
    lTrP: List<CategoryAndTransactions>,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToTransactionScreen: () -> Unit,
    onNavigateToCategoriesScreen: () -> Unit,
    onNavigateToFixedBudgetsScreen: () -> Unit,
    onNavigateToBudgetSummaryScreen: () -> Unit,
    onNavigateToCalendarScreen: () -> Unit,
    onNavigateToGraphsScreen: () -> Unit,
    onNavigateToMementosScreen: () -> Unit,
    calendarScreenUIState: CalendarScreenUIState,
    updateDate: (String) -> Unit,
    updateIncomesExpenses: (Boolean, Boolean) -> Unit,
    updateButtons: () -> Unit,
    navController: NavController,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit,
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
) {
    val date: String = calendarScreenUIState.date
    var incomes: Boolean = calendarScreenUIState.incomes
    var expenses: Boolean = calendarScreenUIState.expenses
    val buttons: Boolean = calendarScreenUIState.buttons
    val revenuesSum: Double = calendarScreenUIState.sumRevenues
    val expensesSum: Double = calendarScreenUIState.sumExpenses
    val idUpdate: Int = calendarScreenUIState.idUpdate

    if (incomes || expenses) {
        //Nu e un ecran full-featured.
        //E doar un fel de ecran intermediar.
        //Nu l-am adaugat in navcontroller.
        CurrentSituationComposableScreen(
                                         onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                         onNavigateToHomeScreen = onNavigateToHomeScreen,
                                         incomes = incomes,
                                         expenses = expenses,
                                         lTrA = lTrA,
                                         lTrP = lTrP,
                                         date = date,
                                         buttons = buttons,
                                         updateButtons = updateButtons,
                                         updateIncomesExpenses = updateIncomesExpenses,
                                         navController = navController,
                                         deleteById = deleteById,
                                         updateUpdateId = updateUpdateId,
                                         idUpdate = idUpdate,
                                         categoriesA = categoriesA,
                                         categoriesP = categoriesP,
                                         categoriesD = categoriesD,
                                         updateDate = updateDate
        )
    } else {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    onNavigateToHomeScreen,
                    onNavigateToTransactionScreen,
                    onNavigateToCategoriesScreen,
                    onNavigateToFixedBudgetsScreen
                )
            },
            topBar = {
                MainScreenToAppBar( id = R.string.calendar,
                                    onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                    onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                    onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                    onNavigateToMementosScreen = onNavigateToMementosScreen
                )
            }
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

                Box(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.margin))
                ) {
                    Calendar(
                        onDateSelected = { selectedDate ->
                            updateDate(selectedDate)
                        }
                    )
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

                Card(
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin)),
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
                ) {
                    HorizontalDivider(
                        thickness = dimensionResource(id = R.dimen.thin_line),
                        color = colorResource(id = R.color.gray)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.upper_middle))
                            .background(color = colorResource(R.color.light_cream_yellow))
                            .clickable {
                                incomes = true
                                updateIncomesExpenses(true, false)
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.venit_zi_curenta) + " : " + revenuesSum.toString(),
                            modifier = Modifier
                                .padding(start = dimensionResource(id = R.dimen.medium_line))
                                .align(Alignment.CenterStart),
                            fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                            color = colorResource(R.color.black)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.upper_middle))
                            .background(color = colorResource(R.color.light_cream_red))
                            .clickable {
                                expenses = true
                                updateIncomesExpenses(false, true)
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.cheltuieli_zi_curenta) + " : " + expensesSum.toString(),
                            modifier = Modifier
                                .padding(start = dimensionResource(id = R.dimen.medium_line))
                                .align(Alignment.CenterStart),
                            fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                            color = colorResource(R.color.black)
                        )
                    }
                    HorizontalDivider(
                        thickness = dimensionResource(id = R.dimen.thin_line),
                        color = colorResource(id = R.color.gray)
                    )
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

                Balance(
                    revenuesSum = revenuesSum,
                    expensesSum = expensesSum
                )
            }
        }
    }
}