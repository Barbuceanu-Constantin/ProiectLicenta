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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.Balance
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.CalendarSummaryTranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.di.CalendarScreenUIState
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarComposableScreen(lTrA: SnapshotStateList<Transaction>,
                             lTrP: SnapshotStateList<Transaction>,
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
                             updateButtons: (Boolean) -> Unit,
) {
    val dateMutable: MutableState<String> = remember {
        mutableStateOf(calendarScreenUIState.date)
    }
    val incomes: MutableState<Boolean> = remember {
        mutableStateOf(calendarScreenUIState.incomes)
    }
    val expenses: MutableState<Boolean> = remember {
        mutableStateOf(calendarScreenUIState.expenses)
    }
    val buttons: MutableState<Boolean> = remember {
        mutableStateOf(calendarScreenUIState.buttons)
    }

    if (incomes.value || expenses.value) {
        Scaffold (
            topBar = {
                EditTopAppBar(
                    id = R.string.situatie_zilnica,
                    onNavigateToBackScreen = onNavigateToCalendarScreen,
                    onNavigateToHomeScreen = onNavigateToHomeScreen
                )
            }
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                if (incomes.value && !expenses.value) {
                    CalendarSummaryTranzactiiLazyColumn(
                        tranzactii = lTrA,
                        backButton = incomes,
                        incomesOrExpenses = true,
                        date = dateMutable,
                        buttons = buttons,
                        updateButtons = updateButtons,
                        updateIncomesExpenses = updateIncomesExpenses
                    )
                } else if (!incomes.value && expenses.value) {
                    CalendarSummaryTranzactiiLazyColumn(
                        tranzactii = lTrP,
                        backButton = expenses,
                        incomesOrExpenses = false,
                        date = dateMutable,
                        buttons = buttons,
                        updateButtons = updateButtons,
                        updateIncomesExpenses = updateIncomesExpenses
                    )
                }
            }
        }
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
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

                Box(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.margin))
                ) {
                    Calendar(
                        onDateSelected = { selectedDate ->
                            dateMutable.value = selectedDate
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
                                incomes.value = true
                                updateIncomesExpenses(true, false)
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.venit_zi_curenta) + " : ",
                            modifier = Modifier
                                .padding(start = dimensionResource(id = R.dimen.medium_line))
                                .align(Alignment.CenterStart),
                            fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.upper_middle))
                            .background(color = colorResource(R.color.light_cream_red))
                            .clickable {
                                expenses.value = true
                                updateIncomesExpenses(false, true)
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.cheltuieli_zi_curenta) + " : ",
                            modifier = Modifier
                                .padding(start = dimensionResource(id = R.dimen.medium_line))
                                .align(Alignment.CenterStart),
                            fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
                        )
                    }
                    HorizontalDivider(
                        thickness = dimensionResource(id = R.dimen.thin_line),
                        color = colorResource(id = R.color.gray)
                    )
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

                Balance()
            }
        }
    }
}