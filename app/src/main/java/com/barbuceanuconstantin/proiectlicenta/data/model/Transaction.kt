@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.returnToBudgetSummaryIndex
import com.barbuceanuconstantin.proiectlicenta.returnToCalendarIndex
import com.barbuceanuconstantin.proiectlicenta.returnToTransactionIndex
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tranzactie(
    transaction: Transactions,
    buttons: Boolean,
    index: Int,
    id: MutableState<Int>,
    updateStateButtons: (Boolean) -> Unit = { _ -> },
) {

    val modifier = Modifier
        .combinedClickable(
            onClick = { },
            onLongClick = {
                updateStateButtons(!buttons)
                id.value = index
            },
        )
        .padding(
            start = dimensionResource(id = R.dimen.margin),
            end = dimensionResource(id = R.dimen.margin)
        )

    val color: Color =
        if (subcategorysPredefiniteActive.contains(transaction.categoryName))
            colorResource(id = R.color.light_cream_yellow)
        else if (subcategorysPredefinitePasive.contains(transaction.categoryName))
            colorResource(id = R.color.light_cream_red)
        else if (subcategorysPredefiniteDatorii.contains(transaction.categoryName))
            colorResource(id = R.color.light_cream_blue) else colorResource(id = R.color.light_cream_gray)

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
            modifier = modifier
        ) {
            Column(modifier = Modifier.background(color)) {
                Text(
                    text = "${transaction.categoryName} ---> ${transaction.value} RON", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = dimensionResource(id = R.dimen.margin))
                )
                Text(
                    text = "${stringResource(id = R.string.furnizor_sau_beneficiar)} : ${transaction.payee}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.spacing)
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.data)} : ${transaction.date}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.spacing)
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.descriere)} : ${transaction.description}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.spacing)
                    )
                )
            }
        }
    }
}

@Composable
fun TranzactiiLazyColumn(
    tranzactii: SnapshotStateList<Transactions>,
    buttons: Boolean,
    navController: NavController,
    summary: Boolean = false,
    updateStateButtons: (Boolean) -> Unit = { _ -> }
) {
    val modifier =  if (summary)
                        Modifier.fillMaxHeight(0.9F)
                    else
                        Modifier.fillMaxHeight().fillMaxWidth()

    val id: MutableState<Int> = remember { mutableIntStateOf(-1) }

    LazyColumn(modifier = modifier) {
        itemsIndexed(tranzactii) {
            index, tranzactie -> Tranzactie(tranzactie, buttons, index, id, updateStateButtons)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
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
                val transactionObj = tranzactii[id.value]
                Button(
                    onClick = {
                        updateStateButtons(false)
                        val gson: Gson = GsonBuilder().create()
                        val transactionJson = gson.toJson(transactionObj)

                        if (!summary) {
                            navController.navigate(
                                "editTransactionScreen/$returnToTransactionIndex?transaction={transaction}"
                                    .replace(oldValue = "{transaction}", newValue = transactionJson)
                            )
                        } else {
                            navController.navigate(
                                "editTransactionScreen/$returnToBudgetSummaryIndex?transaction={transaction}"
                                    .replace(oldValue = "{transaction}", newValue = transactionJson)
                            )
                        }
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

@Composable
fun CalendarSummaryTranzactiiLazyColumn(
    tranzactii: SnapshotStateList<Transactions>,
    incomesOrExpenses: Boolean,
    date: String,
    buttons: Boolean,
    updateButtons: (Boolean) -> Unit,
    updateIncomesExpenses: (Boolean, Boolean) -> Unit,
    navController: NavController
) {
    val modifier: Modifier = Modifier.fillMaxHeight(0.9F)

    val id: MutableState<Int> = remember { mutableIntStateOf(-1) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

        if (incomesOrExpenses) {
            //Coloana venituri
            Text(text = stringResource(id = R.string.Venituri) + " " + date,
                fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream))
            )

            FadingArrowIcon()

        } else {
            //Coloana cheltuieli
            Text(text = stringResource(id = R.string.Cheltuieli) + " " + date,
                fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream))
            )

            FadingArrowIcon()
        }

        LazyColumn(modifier = modifier) {
            itemsIndexed(tranzactii) { index, tranzactie ->
                Tranzactie(tranzactie, buttons, updateStateButtons = updateButtons, id = id, index = index)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

        OkButton(updateIncomesExpenses = updateIncomesExpenses)
    }

    if (buttons) {
        AlertDialog(
            onDismissRequest = {
                updateButtons(false)
            },
            title = {
                Text(text = stringResource(id = R.string.selectare_actiune))
            },
            text = {
                Text(text = stringResource(id = R.string.mesaj_selectare_actiune),
                    fontSize = fontDimensionResource(id = R.dimen.normal_text_size))
            },
            confirmButton = {
                val transactionObj = tranzactii[id.value]
                Button(
                    onClick = {
                        updateButtons(false)
                        val gson: Gson = GsonBuilder().create()
                        val transactionJson = gson.toJson(transactionObj)

                        navController.navigate(
                            "editTransactionScreen/$returnToCalendarIndex?transaction={transaction}"
                                .replace(oldValue = "{transaction}", newValue = transactionJson)
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
                        updateButtons(false)
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
