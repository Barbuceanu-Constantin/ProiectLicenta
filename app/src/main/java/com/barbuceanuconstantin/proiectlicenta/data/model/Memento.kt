package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.view.charts.BarChartOnMementosScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun InfoMemento(value: Double, startDate: String, endDate: String, category: Int,
               getCategoryName: suspend (Int) -> String) {
    // Remember the category name to avoid recomposition
    var categoryName by remember(category) {
        mutableStateOf("")
    }

    // Launch coroutine to fetch category name
    LaunchedEffect(category) {
        val name = getCategoryName(category)
        if (name.isNotEmpty()) {
            // Update the category name if it's not empty
            categoryName = name
        }
    }

    Column(modifier = Modifier.background(colorResource(id = R.color.light_cream_gray))) {
        Text(
            text = stringResource(id = R.string.category) + ": $categoryName",
            fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.thin_line),
                    start = dimensionResource(id = R.dimen.spacing)
                )
        )

        Text(
            text = stringResource(id = R.string.prag_superior) + " $value",
            fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.thin_line),
                    start = dimensionResource(id = R.dimen.spacing)
                )
        )

        Text(
            text = stringResource(id = R.string.data_inceput) + " $startDate",
            fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.thin_line),
                    start = dimensionResource(id = R.dimen.spacing)
                )
        )

        Text(
            text = stringResource(id = R.string.data_final) + " $endDate",
            fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.thin_line),
                    bottom = dimensionResource(id = R.dimen.thin_line),
                    start = dimensionResource(id = R.dimen.spacing)
                )
        )
    }
}

@Composable
fun MementoBarChart(
    budget: Budgets,
    getCurrentFilling: (Int, Date, Date) -> Double
) {
    val threshold: Double = budget.upperThreshold
    val currentFilling: Double = getCurrentFilling(budget.categoryId, budget.startDate, budget.endDate)

    val chartColors: List<Color> = listOf(
                                            colorResource(id = R.color.light_cream_red),
                                            colorResource(id = R.color.light_cream_green)
    )
    val chartValues: List<Double> = listOf(currentFilling, threshold)

    BarChartOnMementosScreen(chartColors = chartColors, chartValues = chartValues)
}

@Composable
fun MementosLazyColumn(
    lFixedBudgets: List<Budgets>,
    getCategoryName: suspend (Int) -> String,
    getCurrentFilling: (Int, Date, Date) -> Double
) {
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(lFixedBudgets) { index, budget ->
            Card(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
                modifier = Modifier.padding(
                                                start = dimensionResource(id = R.dimen.margin),
                                                end = dimensionResource(id = R.dimen.margin)
                                            )
            ) {
                HeaderBudget(text = budget.name)
                InfoMemento(
                    budget.upperThreshold,
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(budget.startDate),
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(budget.endDate),
                    budget.categoryId,
                    getCategoryName
                )
                MementoBarChart(
                    lFixedBudgets[index],
                    getCurrentFilling = getCurrentFilling
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
        }
    }
}