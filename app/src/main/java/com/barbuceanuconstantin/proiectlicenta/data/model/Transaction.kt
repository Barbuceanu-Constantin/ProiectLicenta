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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateOf
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
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

data class Transaction(
    var suma: Double,
    var descriere: String,
    var subcategory: String,
    var data: String,
    var payee: String
)

@Composable
private fun Tranzactie(transaction: Transaction, buttons: MutableState<Boolean>) {
    val color: Color =
        if (subcategorysPredefiniteActive[transaction.subcategory.first()]?.contains(transaction.subcategory) == true)
            colorResource(id = R.color.light_cream_yellow)
        else if (subcategorysPredefinitePasive[transaction.subcategory.first()]?.contains(transaction.subcategory) == true)
            colorResource(id = R.color.light_cream_red)
        else if (subcategorysPredefiniteDatorii[transaction.subcategory.first()]?.contains(transaction.subcategory) == true)
            colorResource(id = R.color.light_cream_blue) else colorResource(id = R.color.light_cream_gray)

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.ten_dp)),
            modifier = Modifier
                .combinedClickable(
                    onClick = { },
                    onLongClick = { buttons.value = !buttons.value },
                )
                .padding(start = dimensionResource(id = R.dimen.margin),
                         end = dimensionResource(id = R.dimen.margin))
        ) {
            Column(modifier = Modifier.background(color)) {
                Text(
                    text = "${transaction.subcategory} ---> ${transaction.suma} RON", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = dimensionResource(id = R.dimen.margin))
                )
                Text(
                    text = "${stringResource(id = R.string.furnizor_sau_beneficiar)} : ${transaction.payee}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.eight_dp)
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.data)} : ${transaction.data}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.eight_dp)
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.descriere)} : ${transaction.descriere}",
                    maxLines = 2,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.margin),
                        top = dimensionResource(id = R.dimen.eight_dp)
                    )
                )
            }
        }
    }
}

@Composable
fun TranzactiiLazyColumn(tranzactii: SnapshotStateList<Transaction>, buttons: MutableState<Boolean>,
                         summary: Boolean = false) {
    val modifier = if (summary) Modifier.fillMaxHeight(0.85F) else Modifier.fillMaxHeight().fillMaxWidth()

    LazyColumn(modifier = modifier) {
        items(tranzactii) {
            tranzactie -> Tranzactie(tranzactie, buttons)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
        }
    }

    if (buttons.value) {
        AlertDialog(
            onDismissRequest = {
                buttons.value = !buttons.value
            },
            title = {
                Text(text = stringResource(id = R.string.selectare_actiune))
            },
            text = {
                Text(text = stringResource(id = R.string.mesaj_selectare_actiune),
                     fontSize = fontDimensionResource(id = R.dimen.fourthy_sp))
            },
            confirmButton = {
                Button(
                    onClick = {
                        buttons.value = !buttons.value
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
fun CalendarSummaryTranzactiiLazyColumn(tranzactii: SnapshotStateList<Transaction>,
                                        backButton: MutableState<Boolean>,
                                        incomesOrExpenses: Boolean,
                                        date: MutableState<String>) {

    val modifier: Modifier = Modifier.fillMaxHeight(0.8F)

    val buttons: MutableState<Boolean> = remember { mutableStateOf(false)}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

        if (incomesOrExpenses) {
            //Coloana venituri
            Text(text = stringResource(id = R.string.Venituri) + " " + date.value,
                fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream))
            )
        } else {
            //Coloana cheltuieli
            Text(text = stringResource(id = R.string.Cheltuieli) + " " + date.value,
                fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream))
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

        LazyColumn(modifier = modifier) {
            items(tranzactii) { tranzactie ->
                Tranzactie(tranzactie, buttons)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

        OkButton(ok = backButton)
    }
}
