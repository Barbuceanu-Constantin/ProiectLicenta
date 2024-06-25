package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.view.charts.BarChartOnGraphsScreen
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.view.charts.LineChartGraph
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.view.charts.PieDonutChartGraph
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.view.charts.StackedBarChartGraph
import com.barbuceanuconstantin.proiectlicenta.di.GraphsScreenUIState
import com.barbuceanuconstantin.proiectlicenta.view.charts.LineChartGraphAverages
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.Menu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphsComposableScreen(modifier: Modifier = Modifier,
                           onNavigateToHomeScreen: () -> Unit,
                           onNavigateToTransactionScreen: () -> Unit,
                           onNavigateToCategoriesScreen: () -> Unit,
                           onNavigateToFixedBudgetsScreen: () -> Unit,
                           onNavigateToBudgetSummaryScreen: () -> Unit,
                           onNavigateToCalendarScreen: () -> Unit,
                           onNavigateToGraphsScreen: () -> Unit,
                           onNavigateToMementosScreen: () -> Unit,
                           graphsScreenUIState: GraphsScreenUIState,
                           updateGraphInterval: (String) -> Unit,
                           updateChartTypeChoice: (String) -> Unit,
                           updateMonth: (String) -> Unit,
                           updateMetricsGlobal: () -> Unit,
                           updateMetricsMonth: (String) -> Unit,
                           updateMonthComparisonType: (String) -> Unit,
                           updateMonthsListSum: (List<String>) -> Triple<List<Double>, List<Double>, List<Double>>,
                           updateCategory: (String) -> Unit,
                           getCategoryMonthAverages: (List<String>, Int) -> List<Float>,
                           getId: (String, String) -> Int) {
    val lChoices = listOf(
        stringResource(id = R.string.selectie_globala),
        stringResource(id = R.string.selectie_lunara),
        stringResource(id = R.string.comparatie_luni)
    )
    val lChartTypeChoices = listOf(
        stringResource(id = R.string.pie_chart),
        stringResource(id = R.string.donut_chart),
        stringResource(id = R.string.bar_chart)
    )
    val lMonth = listOf(
        stringResource(id = R.string.ianuarie),
        stringResource(id = R.string.februarie),
        stringResource(id = R.string.martie),
        stringResource(id = R.string.aprilie),
        stringResource(id = R.string.mai),
        stringResource(id = R.string.iunie),
        stringResource(id = R.string.iulie),
        stringResource(id = R.string.august),
        stringResource(id = R.string.septembrie),
        stringResource(id = R.string.octombrie),
        stringResource(id = R.string.noiembrie),
        stringResource(id = R.string.decembrie)
    )
    val lMonthsComparison = listOf(
        stringResource(id = R.string.stacked_chart),
        stringResource(id = R.string.line_chart),
        stringResource(id = R.string.media_lunara)
    )
    val lCategories = graphsScreenUIState.allCategories

    val graphName = graphsScreenUIState.graphChoice
    val chartType = graphsScreenUIState.chartType
    val month = graphsScreenUIState.month
    val monthComparisonType = graphsScreenUIState.monthComparisonChartType
    val category = graphsScreenUIState.category
    var lRevenues: List<Double>
    var lExpenses: List<Double>
    var lDebt: List<Double>

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
            MainScreenToAppBar( id = R.string.grafice,
                                onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                onNavigateToGraphsScreen = onNavigateToGraphsScreen,
                                onNavigateToMementosScreen = onNavigateToMementosScreen
            )
        }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            Menu(update = updateGraphInterval,
                 lChoices = lChoices,
                 value = graphName,
                 label = stringResource(id = R.string.selectare_graph_interval))

            if (graphName == stringResource(id = R.string.selectie_globala)) {
                updateMetricsGlobal()

                val chartColors = listOf(
                    colorResource(id = R.color.light_cream_yellow),
                    colorResource(id = R.color.light_cream_red),
                    colorResource(id = R.color.light_cream_blue)
                )

                val chartValues = listOf(
                    graphsScreenUIState.revenuesSum,
                    graphsScreenUIState.expensesSum,
                    graphsScreenUIState.debtSum
                )

                Menu(update = updateChartTypeChoice,
                    lChoices = lChartTypeChoices,
                    value = chartType,
                    label = stringResource(id = R.string.selectare_graph_name))

                if (chartType == "Pie Chart") {
                    PieDonutChartGraph(chartColors, chartValues, false)
                } else if (chartType == "Donut Chart") {
                    PieDonutChartGraph(chartColors, chartValues, true)
                } else if (chartType == "Bar Chart") {
                    BarChartOnGraphsScreen(chartColors, chartValues)
                }
            } else if (graphName == stringResource(id = R.string.selectie_lunara)) {
                Menu(update = updateMonth,
                    lChoices = lMonth,
                    value = month,
                    label = stringResource(id = R.string.selectare_luna))

                if (month != "") {
                    updateMetricsMonth(month)

                    val chartColors = listOf(
                        colorResource(id = R.color.light_cream_yellow),
                        colorResource(id = R.color.light_cream_red),
                        colorResource(id = R.color.light_cream_blue)
                    )

                    val chartValues = listOf(
                        graphsScreenUIState.revenuesSum,
                        graphsScreenUIState.expensesSum,
                        graphsScreenUIState.debtSum
                    )

                    Menu(update = updateChartTypeChoice,
                        lChoices = lChartTypeChoices,
                        value = chartType,
                        label = stringResource(id = R.string.selectare_graph_name))

                    if (chartType == "Pie Chart") {
                        PieDonutChartGraph(chartColors, chartValues, false)
                    } else if (chartType == "Donut Chart") {
                        PieDonutChartGraph(chartColors, chartValues, true)
                    } else if (chartType == "Bar Chart") {
                        BarChartOnGraphsScreen(chartColors, chartValues)
                    }
                }
            } else if (graphName == stringResource(id = R.string.comparatie_luni)) {
                Menu(update = updateMonthComparisonType,
                    lChoices = lMonthsComparison,
                    value = monthComparisonType,
                    label = stringResource(id = R.string.selectare_graph_name))

                if (monthComparisonType == "Line Chart") {
                    val triple = updateMonthsListSum(lMonth)
                    lRevenues = triple.first
                    lExpenses = triple.second
                    lDebt = triple.third

                    LineChartGraph(
                        lRevenues,
                        lExpenses,
                        lDebt
                    )
                } else if (monthComparisonType == "Stacked Chart") {
                    val triple = updateMonthsListSum(lMonth)
                    lRevenues = triple.first
                    lExpenses = triple.second
                    lDebt = triple.third
                    StackedBarChartGraph(
                        lRevenues,
                        lExpenses,
                        lDebt
                    )
                } else if (monthComparisonType == "Media lunara pe categorii") {
                    Menu(update = updateCategory,
                        lChoices = lCategories,
                        value = category,
                        label = stringResource(id = R.string.selectare_categorie),
                        categories = true,
                        revenueCategories = graphsScreenUIState.revenueCategories,
                        expenseCategories = graphsScreenUIState.expenseCategories,
                        debtCategories = graphsScreenUIState.debtCategories)

                    var idCategory : Int
                    if (graphsScreenUIState.revenueCategories.contains(category)) {
                        idCategory = getId(category, "Active")
                        val listAverages = getCategoryMonthAverages(lMonth, idCategory)
                        LineChartGraphAverages(
                            listAverages,
                            "Active"
                        )
                    } else if (graphsScreenUIState.expenseCategories.contains(category)) {
                        idCategory = getId(category, "Pasive")
                        val listAverages = getCategoryMonthAverages(lMonth, idCategory)
                        LineChartGraphAverages(
                            listAverages,
                            "Pasive"
                        )
                    } else {
                        idCategory = getId(category, "Datorii")
                        val listAverages = getCategoryMonthAverages(lMonth, idCategory)
                        LineChartGraphAverages(
                            listAverages,
                            "Datorii"
                        )
                    }
                }
            }
        }
    }
}

