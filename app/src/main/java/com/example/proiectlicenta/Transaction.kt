package com.example.proiectlicenta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Tranzactie(
    var suma: Double,
    var valuta: String,
    var descriere: String,
    var subcategorie: String,
    var data: String,
    var payee: String
)

@Composable
private fun tranzactie(
    text: String,
    descriere: String,
    data: String,
    payee: String,
    modifier: Modifier = Modifier
) {
    Text (
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(16.dp)
    )
    Text (
        text = payee,
        maxLines = 2
    )
    Text (
        text = "Data : $data",
        maxLines = 2
    )
    Text(
        text = "Descriere : $descriere",
        maxLines = 2
    )
}

@Composable
fun tranzactiiLazyColumn(
    tranzactii: List<Tranzactie>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.height(400.dp)) {
        items(tranzactii) {
            tranzactie -> tranzactie(text = "${tranzactie.subcategorie} ---> ${tranzactie.suma} ${tranzactie.valuta}",
                                    tranzactie.descriere, tranzactie.data, tranzactie.payee)
        }
    }
}