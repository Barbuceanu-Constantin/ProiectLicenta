package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie


@Composable
fun transactionUpdateScreen(indexUpdate: Int, trList: SnapshotStateList<Tranzactie>) {
    val tr = trList[indexUpdate]
    Column {
        Text(tr.data)
        Text(tr.descriere)
        Text(tr.payee)
    }
}