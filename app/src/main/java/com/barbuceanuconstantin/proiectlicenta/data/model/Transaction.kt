package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

class Tranzactie(
    var suma: Double,
    var valuta: String,
    var descriere: String,
    var subcategory: String,
    var data: String,
    var payee: String
)

@Composable
private fun Tranzactie( subcategory: String, value: Double, currency: String, descriere: String,
                        data: String, payee: String, onDeleteItem: () -> Unit, update: () -> Unit,
                        buttons: Boolean = true) {
    val color: Color =
        if (subcategorysPredefiniteActive[subcategory.first()]?.contains(subcategory) == true)
            colorResource(id = R.color.light_cream_yellow)
        else if (subcategorysPredefinitePasive[subcategory.first()]?.contains(subcategory) == true)
            colorResource(id = R.color.light_cream_red)
        else if (subcategorysPredefiniteDatorii[subcategory.first()]?.contains(subcategory) == true)
            colorResource(id = R.color.light_cream_blue) else colorResource(id = R.color.light_cream_gray)

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.ten_dp))
        ) {
            Column {
                Text(
                    text = "${subcategory} ---> ${value} (${currency})", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier
                        .fillMaxWidth()
                        .background(color)
                        .padding(start = dimensionResource(id = R.dimen.margin))
                )
                Text(
                    text = "${stringResource(id = R.string.furnizor_sau_beneficiar)} : $payee",
                    maxLines = 2,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                                top = dimensionResource(id = R.dimen.eight_dp))
                )
                Text(
                    text = "${stringResource(id = R.string.data)} : $data",
                    maxLines = 2,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                                top = dimensionResource(id = R.dimen.eight_dp))
                )
                Text(
                    text = "${stringResource(id = R.string.descriere)} : $descriere",
                    maxLines = 2,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                                top = dimensionResource(id = R.dimen.eight_dp))
                )
                if (buttons) {
                    Row {
                        IconButton(
                            onClick = update, modifier = Modifier
                                .fillMaxSize(fraction = 1f)
                                .weight(1f)
                        ) {
                            Icon(
                                Icons.Filled.Colorize, contentDescription = "Update",
                                tint = colorResource(id = R.color.black)
                            )
                        }

                        IconButton(
                            onClick = onDeleteItem, modifier = Modifier
                                .fillMaxSize(fraction = 1f)
                                .weight(1f)
                        ) {
                            Icon(
                                Icons.Filled.Delete, contentDescription = "Delete",
                                tint = colorResource(id = R.color.black)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TranzactiiLazyColumn(tranzactii: SnapshotStateList<Tranzactie>,
                         lTrA: SnapshotStateList<Tranzactie>? = null,
                         lTrP: SnapshotStateList<Tranzactie>? = null,
                         lTrD: SnapshotStateList<Tranzactie>? = null,
                         indexState: MutableState<Int>, sem: MutableState<Int>,
                         updateScreenButton: MutableState<Boolean>) {
    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
        items(tranzactii) {
            tranzactie -> Tranzactie(tranzactie.subcategory, tranzactie.suma, tranzactie.valuta,
                                    tranzactie.descriere, tranzactie.data, tranzactie.payee,
                                    onDeleteItem = {
                                        tranzactii.remove(tranzactie)

                                        if (lTrA != null) {
                                            if (lTrA.contains(tranzactie)) {
                                                lTrA.remove(tranzactie)
                                            }
                                        }

                                        if (lTrP != null) {
                                            if (lTrP.contains(tranzactie)) {
                                                lTrP.remove(tranzactie)
                                            }
                                        }

                                        if (lTrD != null) {
                                            if (lTrD.contains(tranzactie)) {
                                                lTrD.remove(tranzactie)
                                            }
                                        }
                                    },
                                    update = {
                                        if (lTrA != null) {
                                            if (lTrA.contains(tranzactie)) {
                                                sem.value = 1
                                                indexState.value = lTrA.indexOf(tranzactie)
                                            }
                                        }
                                        if (lTrP != null) {
                                            if (lTrP.contains(tranzactie)) {
                                                sem.value = 2
                                                indexState.value = lTrP.indexOf(tranzactie)
                                            }
                                        }
                                        if (lTrD != null) {
                                            if (lTrD.contains(tranzactie)) {
                                                sem.value = 3
                                                indexState.value = lTrD.indexOf(tranzactie)
                                            }
                                        }

                                        updateScreenButton.value = !updateScreenButton.value
                                    })
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
        }
    }
}

@Composable
fun SummaryTranzactiiLazyColumn(tranzactii: SnapshotStateList<Tranzactie>) {

    val modifier: Modifier = Modifier.fillMaxHeight(0.8F)

    LazyColumn(modifier = modifier) {
        items(tranzactii) {
                tranzactie -> Tranzactie(
                                        tranzactie.subcategory, tranzactie.suma, tranzactie.valuta,
                                        tranzactie.descriere, tranzactie.data, tranzactie.payee,
                                        onDeleteItem = { },
                                        update = { },
                                        buttons = false
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
        }
    }
}

@Composable
fun CalendarSummaryTranzactiiLazyColumn(tranzactii: SnapshotStateList<Tranzactie>,
                                        backButton: MutableState<Boolean>,
                                        incomesOrExpenses: Boolean,
                                        date: MutableState<String>) {

    val modifier: Modifier = Modifier.fillMaxHeight(0.8F)

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
                Tranzactie(
                    tranzactie.subcategory, tranzactie.suma, tranzactie.valuta,
                    tranzactie.descriere, tranzactie.data, tranzactie.payee,
                    onDeleteItem = { },
                    update = { },
                    buttons = false
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

        OkButton(ok = backButton)
    }
}
