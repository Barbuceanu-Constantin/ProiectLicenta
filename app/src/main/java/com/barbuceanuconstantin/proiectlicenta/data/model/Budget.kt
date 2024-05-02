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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.google.gson.Gson
import com.google.gson.GsonBuilder

data class Budget(
    val id: Int,
    var startDate: String,
    var endDate: String,
    var name: String,
    var value: Double,
    var category: String
)

@Composable
private fun HeaderBudget(text: String) {
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
                    .weight(1f))
        }
    }
}

@Composable
fun InfoBudget(value: Double, startDate: String, endDate: String, category: String) {
    Column(modifier = Modifier.background(colorResource(id = R.color.light_cream_gray))) {
        Text(
            text = stringResource(id = R.string.category) + ": $category",
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BudgetsLazyColumn(lFixedBudgets: SnapshotStateList<Budget>,
                      buttons: MutableState<Boolean>,
                      navController: NavController,
                      updateStateButtons: (Boolean) -> Unit
) {
    val id: MutableState<Int> = remember { mutableIntStateOf(-1) }

    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(lFixedBudgets) { index, budget ->
            Card(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                            end = dimensionResource(id = R.dimen.margin))
                                    .combinedClickable(
                                                        onClick = { },
                                                        onLongClick = {
                                                                        buttons.value = !buttons.value
                                                                        updateStateButtons(buttons.value)
                                                                        id.value = index
                                                                      },
                                                    )
            ) {
                HeaderBudget(text = budget.name)
                InfoBudget(budget.value, budget.startDate, budget.endDate, budget.category)
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
        }
    }

    if (buttons.value) {
        AlertDialog(
            onDismissRequest = {
                buttons.value = !buttons.value
                updateStateButtons(buttons.value)
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
                        buttons.value = !buttons.value
                        updateStateButtons(buttons.value)
                        val gson: Gson = GsonBuilder().create()
                        val budgetJson = gson.toJson(budgetObj)
                        navController.navigate("editBudgetScreen?budget={budget}"
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
                Button(
                    onClick = {
                        buttons.value = !buttons.value
                        updateStateButtons(buttons.value)
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