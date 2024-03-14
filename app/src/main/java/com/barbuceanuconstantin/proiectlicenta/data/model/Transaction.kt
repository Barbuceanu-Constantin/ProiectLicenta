package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

data class Tranzactie(
    var suma: Double,
    var valuta: String,
    var descriere: String,
    var subcategory: String,
    var data: String,
    var payee: String
)

@Composable
private fun tranzactie(
    subcategory: String,
    value: Double,
    currency: String,
    descriere: String,
    data: String,
    payee: String,
    onDeleteItem: () -> Unit,
    update: () -> Unit
) {
    val color: Color =  if (subcategorysPredefiniteActive[subcategory.first()]?.contains(subcategory) == true) Color.Yellow
                        else if (subcategorysPredefinitePasive[subcategory.first()]?.contains(subcategory) == true) Color.Red
                        else if (subcategorysPredefiniteDatorii[subcategory.first()]?.contains(subcategory) == true) Color(100, 200, 240) else Color.Gray
    Text (
        text = "${subcategory} ---> ${value} (${currency})",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
    )
    Row() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Text(
                text = payee,
                maxLines = 2
            )
            Text(
                text = "${stringResource(id = R.string.data)} : $data",
                maxLines = 2
            )
            Text(
                text = "${stringResource(id = R.string.descriere)} : $descriere",
                maxLines = 2
            )
        }
        IconButton(onClick = update, modifier = Modifier.fillMaxSize(fraction = 1f).weight(1f)) {
            Icon(Icons.Filled.Update, contentDescription = "Favorite", tint = Color.Black)
        }
        IconButton(onClick = onDeleteItem, modifier = Modifier.fillMaxSize(fraction = 1f).weight(1f)) {
            Icon(Icons.Filled.Delete, contentDescription = "Favorite", tint = Color.Black)
        }
    }
}

@Composable
fun tranzactiiLazyColumn(
    tranzactii: SnapshotStateList<Tranzactie>,
    lTrA: SnapshotStateList<Tranzactie>? = null,
    lTrP: SnapshotStateList<Tranzactie>? = null,
    lTrD: SnapshotStateList<Tranzactie>? = null,
    indexState: MutableState<Int>,
    sem: MutableState<Int>
) {
    var index = 0
    LazyColumn(
        Modifier.fillMaxHeight(700F / LocalConfiguration.current.screenHeightDp).fillMaxWidth(0.8f)) {
        items(tranzactii) {
            tranzactie -> tranzactie(tranzactie.subcategory, tranzactie.suma, tranzactie.valuta,
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
                                        indexState.value = index
                                        if (lTrA != null && lTrP != null && lTrD != null) {
                                            if (lTrA.contains(tranzactie)) sem.value = 1
                                            if (lTrP.contains(tranzactie)) sem.value = 2
                                            if (lTrD.contains(tranzactie)) sem.value = 3
                                            if (index >= lTrA.size + lTrP.size) {
                                                indexState.value -= (lTrA.size + lTrP.size)
                                            }
                                            else if (index >= lTrA.size) {
                                                indexState.value -= lTrA.size
                                            }
                                        }
                                    })
            index += 1
        }
    }
}