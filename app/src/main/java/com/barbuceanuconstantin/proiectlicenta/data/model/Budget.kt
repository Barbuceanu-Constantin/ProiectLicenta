package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.di.FixedBudgetsScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.navigation.editBudgetScreenFullPath
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun CoroutineScope.launchDeleteBudgetById(
    delete: (Int) -> Unit,
    id: Int
) = launch {
    delete(id)
}

fun runDeleteBudgetById(
    delete: (Int) -> Unit,
    id: Int
) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchDeleteBudgetById(delete, id)
    }
}
@Composable
fun HeaderBudget(text: String) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.brown_light_cream))) {
            Text(text = text, fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                 fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = dimensionResource(id = R.dimen.thin_line),
                        bottom = dimensionResource(id = R.dimen.thin_line),
                        start = dimensionResource(id = R.dimen.spacing)
                    )
                    .weight(1f),
                 color = colorResource(R.color.black)
            )
        }
    }
}

@Composable
fun InfoBudget(value: Double, startDate: String, endDate: String, category: Int,
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
                ),
            color = colorResource(R.color.black)
        )

        Text(
            text = stringResource(id = R.string.prag_superior) + " $value" + " RON",
            fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.thin_line),
                    start = dimensionResource(id = R.dimen.spacing)
                ),
            color = colorResource(R.color.black)
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
                ),
            color = colorResource(R.color.black)
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
                ),
            color = colorResource(R.color.black)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BudgetsLazyColumn(
    lFixedBudgets: List<Budgets>,
    buttons: Boolean,
    navController: NavController,
    updateStateButtons: (Boolean) -> Unit,
    deleteByIdCoroutine: (Int) -> Unit,
    getCategoryName: suspend (Int) -> String,
    getTotalRevenues: (Date, Date) -> Unit,
    state: FixedBudgetsScreenUIState
) {
    val id: MutableState<Int> = remember { mutableIntStateOf(-1) }

    if (lFixedBudgets.isNotEmpty()) {
        var totalBudgetsSum = 0.0
        var lowestDate = lFixedBudgets[0].startDate
        var highestDate = lFixedBudgets[0].endDate
        lFixedBudgets.forEach { budget ->
            totalBudgetsSum += budget.upperThreshold
            if (budget.startDate < lowestDate)
                lowestDate = budget.startDate
            if (budget.endDate > highestDate)
                highestDate = budget.endDate
        }
        getTotalRevenues(lowestDate, highestDate)

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val lowestDateString = formatter.format(lowestDate)
        val highestDateString = formatter.format(highestDate)

        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.margin),
                    end = dimensionResource(id = R.dimen.margin)
                )
        ) {
            Column(
                modifier = Modifier.background(color = colorResource(id = R.color.light_cream_green))
            ) {
                Text(text = stringResource(id = R.string.suma_totala_bugete) + " " + totalBudgetsSum.toString() + " RON",
                     fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                     fontWeight = FontWeight.Bold,
                     color = colorResource(R.color.black)
                )
                if (state.revenues > totalBudgetsSum) {
                    Text(
                        text = stringResource(id = R.string.suma_totala_venituri) + " " + state.revenues.toString() + " RON",
                        fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black)
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.suma_totala_venituri) + " " + state.revenues.toString(),
                        fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.red)
                    )
                    Text(
                        text = stringResource(id = R.string.avertisment_venituri_bugete),
                        fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.red)
                    )
                }

                Text(text = stringResource(id = R.string.interval_total_timp) + "\n" + lowestDateString + " <-> " + highestDateString,
                    fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.black)
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
    }

    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(lFixedBudgets) { index, budget ->
            Card(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
                    .combinedClickable(
                        onClick = { },
                        onLongClick = {
                            updateStateButtons(!buttons)
                            id.value = index
                        },
                    )
            ) {
                HeaderBudget(text = budget.name)
                InfoBudget(
                            budget.upperThreshold,
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(budget.startDate),
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(budget.endDate),
                            budget.categoryId,
                            getCategoryName
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
        }
    }

    if (buttons) {
        AlertDialog(
            onDismissRequest = {
                updateStateButtons(false)
            },
            title = {
                Text(text = stringResource(id = R.string.selectare_actiune))
            },
            text = {
                Text(text = stringResource(id = R.string.mesaj_selectare_actiune),
                    fontSize = fontDimensionResource(id = R.dimen.normal_text_size))
            },
            confirmButton = {
                val budgetObj = lFixedBudgets[id.value]
                Button(
                    onClick = {
                        updateStateButtons(false)
                        val gson: Gson = GsonBuilder().create()
                        val budgetJson = gson.toJson(budgetObj)
                        navController.navigate(
                            editBudgetScreenFullPath
                            .replace(oldValue = "{budget}", newValue = budgetJson)
                        )
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.modificare))
                        Icon(
                            Icons.Filled.Colorize, contentDescription = "Delete",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            },
            dismissButton = {
                val budgetObj = lFixedBudgets[id.value]
                Button(
                    onClick = {
                        runDeleteBudgetById(deleteByIdCoroutine, budgetObj.id)
                        updateStateButtons(false)
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.stergere))
                        Icon(
                            Icons.Filled.Delete, contentDescription = "Update",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            }
        )
    }
}