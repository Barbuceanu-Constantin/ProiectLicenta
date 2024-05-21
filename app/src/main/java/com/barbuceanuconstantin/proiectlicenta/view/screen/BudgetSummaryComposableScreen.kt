package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.IntToMonth
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.TimeIntervalSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.getStartAndEndDateOfWeek
import com.barbuceanuconstantin.proiectlicenta.di.BudgetSummaryScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun SelectDay(
    date: String,
    updateStateDateButton: (Boolean) -> Unit
) {
    Button(onClick = { updateStateDateButton(true) }) {
        Text(text = stringResource(id = R.string.selectare_zi), fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.ziua)} $date", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = fontDimensionResource(R.dimen.medium_text_size))
}

@Composable
fun SelectWeek(
    date: String,
    updateStateDateButton: (Boolean) -> Unit,
    updateListsWeek: (Date, Date) -> Unit
) {
    val limits = getStartAndEndDateOfWeek(date)
    val startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(limits.first)
    val endDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(limits.second)
    updateListsWeek(startDate!!, endDate!!)

    Button(onClick = { updateStateDateButton(true) }) {
        Text(text = stringResource(id = R.string.selectare_saptamana),
             fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.saptamana)} (${limits.first} ; ${limits.second})",
        fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center, fontSize = fontDimensionResource(R.dimen.medium_text_size)
    )
}
@Composable
fun SelectMonth(
    date: String,
    monthMutable: String,
    updateStateDateButton: (Boolean) -> Unit,
    updateStateMonth: (String) -> Unit,
    updateListsMonth: (Date, Date) -> Unit
) {
    val month : Int = (date[5].code - 48) * 10 + (date[6].code - 48)
    val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val startOfMonth = parsedDate.with(TemporalAdjusters.firstDayOfMonth())
    val endOfMonth = parsedDate.with(TemporalAdjusters.lastDayOfMonth())
    val startOfMonthDate = Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant())
    val endOfMonthDate = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant())

    updateListsMonth(startOfMonthDate!!, endOfMonthDate!!)
    IntToMonth(month, updateStateMonth)

    Button(onClick = { updateStateDateButton(true) }) {
        Text(text = stringResource(id = R.string.selectare_luna),
             fontSize = fontDimensionResource(id = R.dimen.medium_text_size))
    }

    Text(text = "${stringResource(id = R.string.luna)} $monthMutable",
         fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(),
         textAlign = TextAlign.Center, fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetSummaryComposableScreen(
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToTransactionScreen: () -> Unit,
    onNavigateToCategoriesScreen: () -> Unit,
    onNavigateToFixedBudgetsScreen: () -> Unit,
    onNavigateToBudgetSummaryScreen: () -> Unit,
    onNavigateToCalendarScreen: () -> Unit,
    onNavigateToGraphsScreen: () -> Unit,
    onNavigateToMementosScreen: () -> Unit,
    navController: NavController,
    budgetSummaryScreenUIState: BudgetSummaryScreenUIState,
    updateStateDateButton: (Boolean) -> Unit,
    updateStateMonth: (String) -> Unit,
    updateStateTimeInterval: (Boolean, Boolean, Boolean) -> Unit,
    updateStateDate: (String) -> Unit,
    updateStateButtons: () -> Unit,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit,
    updateListsBasedOnDay: (Date) -> Unit,
    updateListsFull: () -> Unit,
    updateListsInterval: (Date, Date) -> Unit) {

    val daily: Boolean = budgetSummaryScreenUIState.daily
    val weekly: Boolean = budgetSummaryScreenUIState.weekly
    val monthly: Boolean = budgetSummaryScreenUIState.monthly
    var date: String = budgetSummaryScreenUIState.date
    val monthMutable : String = budgetSummaryScreenUIState.month

    val buttons: Boolean = budgetSummaryScreenUIState.buttons
    val dateButton: Boolean = budgetSummaryScreenUIState.dateButton
    val idUpdate: Int = budgetSummaryScreenUIState.idUpdate

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
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
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

            TimeIntervalSegmentedButton(updateStateTimeInterval = updateStateTimeInterval)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))

            //Aici voi face bilantul total al cheltuielilor si veniturilor.
            if (daily && !weekly && !monthly) {

                //Se selecteaza ziua pentru care se vrea bilantul cheltuielilor si veniturilor
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
                    ?.let { updateListsBasedOnDay(it) }
                SelectDay(date, updateStateDateButton)
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
                FadingArrowIcon(budgetSummary = true)
                TranzactiiLazyColumn(
                    tranzactii = (budgetSummaryScreenUIState.expensesTransactions + budgetSummaryScreenUIState.revenueTransactions),
                    buttons = buttons,
                    summary = true,
                    navController = navController,
                    updateStateButtons = updateStateButtons,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    modifier = Modifier.fillMaxHeight(0.85F)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))

            } else if (weekly && !daily && !monthly) {

                //Se selecteaza saptamana pentru care se vrea bilantul cheltuielilor si veniturilor,
                //prin selectarea unei zile si extragerea saptamanii din care face parte

                SelectWeek(date, updateStateDateButton, updateListsInterval)
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
                FadingArrowIcon(budgetSummary = true)
                TranzactiiLazyColumn(
                    tranzactii = (budgetSummaryScreenUIState.expensesTransactions + budgetSummaryScreenUIState.revenueTransactions),
                    buttons = buttons,
                    summary = true,
                    navController = navController,
                    updateStateButtons = updateStateButtons,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    modifier = Modifier.fillMaxHeight(0.85F)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))

            } else if (monthly && !daily && !weekly) {

                //Se selecteaza luna pentru care se vrea bilantul cheltuielilor si veniturilor
                //prin selectarea unei zile si extragerea lunii din care face parte

                SelectMonth(date = date,
                            monthMutable = monthMutable,
                            updateStateDateButton =  updateStateDateButton,
                            updateStateMonth = updateStateMonth,
                            updateListsMonth = updateListsInterval)
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
                FadingArrowIcon(budgetSummary = true)
                TranzactiiLazyColumn(
                    tranzactii = (budgetSummaryScreenUIState.expensesTransactions + budgetSummaryScreenUIState.revenueTransactions),
                    buttons = buttons,
                    summary = true,
                    navController = navController,
                    updateStateButtons = updateStateButtons,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    modifier = Modifier.fillMaxHeight(0.85F)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))

            } else if (daily && weekly && monthly) {

                updateListsFull()
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))
                FadingArrowIcon(budgetSummary = true)
                TranzactiiLazyColumn(
                    tranzactii = (budgetSummaryScreenUIState.expensesTransactions + budgetSummaryScreenUIState.revenueTransactions),
                    buttons = buttons,
                    summary = true,
                    navController = navController,
                    updateStateButtons = updateStateButtons,
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD,
                    deleteById = deleteById,
                    updateUpdateId = updateUpdateId,
                    idUpdate = idUpdate,
                    modifier = Modifier.fillMaxHeight(0.9F)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
                HorizontalDivider(thickness = dimensionResource(id = R.dimen.very_thin_line), color = colorResource(id = R.color.gray))

            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))

            Text(text = stringResource(id = R.string.bilant) + budgetSummaryScreenUIState.balance,
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(
                         start = dimensionResource(id = R.dimen.margin),
                         end = dimensionResource(id = R.dimen.margin)
                     )
                     .weight(1f),
                 fontSize = fontDimensionResource(id = R.dimen.big_text_size),
                 fontWeight = FontWeight.Bold,
                 color = colorResource(id = R.color.medium_green)
            )
        }
    }

    if (dateButton) {
        val datePickerDialog =
            android.app.DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
                // Handle the selected date
                val selectedDate: Calendar = Calendar.getInstance()
                selectedDate.set(year1, month1, dayOfMonth1)

                // Perform any necessary operations with the selected date here
                // For example, update a TextView with the selected date
                date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                updateStateDate(date)
            }, year, month, dayOfMonth)

        datePickerDialog.setOnDismissListener {
            updateStateDateButton(false)
        }

        datePickerDialog.show()
        updateStateDateButton(false)
    }
}


