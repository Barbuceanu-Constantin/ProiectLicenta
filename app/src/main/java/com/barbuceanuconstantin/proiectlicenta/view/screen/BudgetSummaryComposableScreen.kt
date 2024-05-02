package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.IntToMonth
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.TimeIntervalSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.getStartAndEndDateOfWeek
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.di.BudgetSummaryScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@Composable
fun SelectDay(dateMutable: MutableState<String>, dateButton: MutableState<Boolean>) {
    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_zi), fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.ziua)} ${dateMutable.value}", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = fontDimensionResource(R.dimen.medium_text_size))
}

@Composable
fun SelectWeek(dateMutable: MutableState<String>, dateButton: MutableState<Boolean>) {
    val limits = getStartAndEndDateOfWeek(dateMutable.value)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_saptamana),
             fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.saptamana)} (${limits.first} ; ${limits.second})",
        fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center, fontSize = fontDimensionResource(R.dimen.medium_text_size)
    )
}
@Composable
fun SelectMonth(dateMutable: MutableState<String>, monthMutable: MutableState<String>, dateButton: MutableState<Boolean>) {
    val month : Int = (dateMutable.value[5].code - 48) * 10 + (dateMutable.value[6].code - 48)
    IntToMonth(month, monthMutable)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_luna),
             fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.luna)} ${monthMutable.value}",
         fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(),
         textAlign = TextAlign.Center, fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetSummaryComposableScreen(lTrA: SnapshotStateList<Transaction>,
                                  lTrP: SnapshotStateList<Transaction>,
                                  onNavigateToHomeScreen: () -> Unit,
                                  onNavigateToTransactionScreen: () -> Unit,
                                  onNavigateToCategoriesScreen: () -> Unit,
                                  onNavigateToFixedBudgetsScreen: () -> Unit,
                                  onNavigateToBudgetSummaryScreen: () -> Unit,
                                  onNavigateToCalendarScreen: () -> Unit,
                                  onNavigateToGraphsScreen: () -> Unit,
                                  onNavigateToMementosScreen: () -> Unit,
                                  navController: NavController,
                                  budgetSummaryScreenUIState: BudgetSummaryScreenUIState) {
    val daily: MutableState<Boolean> = remember {
        mutableStateOf(budgetSummaryScreenUIState.daily)
    }
    val weekly: MutableState<Boolean> = remember {
        mutableStateOf(budgetSummaryScreenUIState.weekly)
    }
    val monthly: MutableState<Boolean> = remember {
        mutableStateOf(budgetSummaryScreenUIState.monthly)
    }
    val dateMutable: MutableState<String> = remember {
        mutableStateOf(budgetSummaryScreenUIState.date)
    }
    val monthMutable : MutableState<String> = remember {
        mutableStateOf(budgetSummaryScreenUIState.month)
    }
    val buttons: MutableState<Boolean> = remember {
        mutableStateOf(budgetSummaryScreenUIState.buttons)
    }
    val dateButton = remember {
        mutableStateOf(budgetSummaryScreenUIState.dateButton)
    }

    // Obtain the context from your activity or fragment
    val context: Context = LocalContext.current

    // Get the current date
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                onNavigateToHomeScreen,
                onNavigateToTransactionScreen,
                onNavigateToCategoriesScreen,
                onNavigateToFixedBudgetsScreen
            )
        },
        topBar = {
            MainScreenToAppBar( id = R.string.sumar_buget,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))

            Text(text = stringResource(id = R.string.selectare_interval_timp),
                 fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                 style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                                    modifier = Modifier.background(colorResource(R.color.light_cream))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))

            TimeIntervalSegmentedButton(daily = daily, weekly = weekly, monthly = monthly)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))

            //Aici voi face bilantul total al cheltuielilor si veniturilor.
            //Dar pentru inceput le voi lista pe toate fara sa filtrez, asta si pentru ca
            //o sa fie mai relevant probabil cand conectez cu baza de date.
            if (daily.value && !weekly.value && !monthly.value) {
                //Se selecteaza ziua pentru care se vrea bilantul cheltuielilor si veniturilor

                SelectDay(dateMutable, dateButton)
            } else if (weekly.value && !daily.value && !monthly.value) {
                //Se selecteaza saptamana pentru care se vrea bilantul cheltuielilor si veniturilor,
                //prin selectarea unei zile si extragerea saptamanii din care face parte

                SelectWeek(dateMutable, dateButton)
            } else if (monthly.value && !daily.value && !weekly.value) {
                //Se selecteaza luna pentru care se vrea bilantul cheltuielilor si veniturilor
                //prin selectarea unei zile si extragerea lunii din care face parte

                SelectMonth(dateMutable, monthMutable, dateButton)
            } else if (daily.value && weekly.value && monthly.value) {
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                TranzactiiLazyColumn(tranzactii = (lTrP + lTrA).toMutableStateList(), buttons, summary = true, navController = navController)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))

            Text(text = stringResource(id = R.string.bilant),
                 modifier = Modifier.fillMaxWidth().padding(start = dimensionResource(id = R.dimen.margin),
                                                            end = dimensionResource(id = R.dimen.margin))
                                    .weight(1f),
                 fontSize = fontDimensionResource(id = R.dimen.big_text_size),
                 fontWeight = FontWeight.Bold,
                 color = colorResource(id = R.color.medium_green)
            )
        }
    }

    if (dateButton.value) {
        val datePickerDialog =
            android.app.DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
                // Handle the selected date
                val selectedDate: Calendar = Calendar.getInstance()
                selectedDate.set(year1, month1, dayOfMonth1)

                // Perform any necessary operations with the selected date here
                // For example, update a TextView with the selected date
                dateMutable.value =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
            }, year, month, dayOfMonth)

        datePickerDialog.setOnDismissListener {
            dateButton.value = !dateButton.value
        }

        datePickerDialog.show()
    }
}


