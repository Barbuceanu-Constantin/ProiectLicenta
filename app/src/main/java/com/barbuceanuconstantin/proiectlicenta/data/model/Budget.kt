@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R

data class Budget(
    var start_date: String,
    var end_date: String,
    var name: String,
    var value: Double
)

@Composable
private fun headerBudget(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(245, 170, 110))
            .padding(16.dp)
    )
}

@Composable
fun infoBudget(value: Double, startDate: String, endDate: String) {
    Text(
        text = stringResource(id = R.string.suma_totala) + " $value",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
    Text(
        text = stringResource(id = R.string.data_inceput) + " $startDate",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
    Text(
        text = stringResource(id = R.string.data_final) + " $endDate",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}
@Composable
fun budgetsLazyColumn(lFixedBudgets: SnapshotStateList<Budget>) {
    LazyColumn(
        Modifier
            .fillMaxHeight(700F / LocalConfiguration.current.screenHeightDp)
            .fillMaxWidth(0.8f)
    ) {
        lFixedBudgets.forEach() {
            budget -> this@LazyColumn.stickyHeader {
                headerBudget(text = budget.name)
                infoBudget(budget.value, budget.start_date, budget.end_date)
            }
        }
    }
}
