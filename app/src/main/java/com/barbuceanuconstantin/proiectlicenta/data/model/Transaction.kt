package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R

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
) {
    Text (
        text = "${subcategory} ---> ${value} (${currency})",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    )
    Row() {
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
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
        IconButton(onClick = { /**/ }, modifier = Modifier.fillMaxSize(fraction = 1f).weight(1f)) {
            Icon(Icons.Filled.Update, contentDescription = "Favorite", tint = Color.Black)
        }
        IconButton(onClick = { /**/ }, modifier = Modifier.fillMaxSize(fraction = 1f).weight(1f)) {
            Icon(Icons.Filled.Delete, contentDescription = "Favorite", tint = Color.Black)
        }
    }
}

@Composable
fun tranzactiiLazyColumn(
    tranzactii: List<Tranzactie>,
    a: Int = -1,
    p: Int = -1,
    d: Int = -1
) {
    val index = remember { mutableStateOf(0) }
    LazyColumn(
        Modifier
            .fillMaxHeight(700F / LocalConfiguration.current.screenHeightDp)
            .fillMaxWidth(0.8f)) {
        items(tranzactii) {
            tranzactie -> tranzactie(tranzactie.subcategory, tranzactie.suma, tranzactie.valuta,
                                    tranzactie.descriere, tranzactie.data, tranzactie.payee)
            index.value += 1
        }
    }
}