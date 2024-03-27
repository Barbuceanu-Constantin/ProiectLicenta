@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
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
private fun HeaderBudget(text: String, onDeleteItem: () -> Unit) {
    Column() {
        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))

        Row(modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.yellow))) {
            Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                 modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f)
            )

            IconButton(onClick = onDeleteItem, modifier = Modifier.fillMaxSize().weight(1f)
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = colorResource(id = R.color.dark_blue))
            }
        }

        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
    }
}

@Composable
fun InfoBudget(value: Double, startDate: String, endDate: String) {
    Text(text = stringResource(id = R.string.prag_superior) + " $value", fontSize = 16.sp, fontWeight = FontWeight.Bold,
         modifier = Modifier.fillMaxWidth().padding(16.dp))

    Text(text = stringResource(id = R.string.data_inceput) + " $startDate", fontSize = 16.sp, fontWeight = FontWeight.Bold,
         modifier = Modifier.fillMaxWidth().padding(16.dp)
    )

    Text(text = stringResource(id = R.string.data_final) + " $endDate", fontSize = 16.sp, fontWeight = FontWeight.Bold,
         modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}
@Composable
fun BudgetsLazyColumn(lFixedBudgets: SnapshotStateList<Budget>) {
    LazyColumn(Modifier.fillMaxHeight(700F / LocalConfiguration.current.screenHeightDp).fillMaxWidth(0.8f)) {
        lFixedBudgets.forEach() {
            budget -> this@LazyColumn.stickyHeader {
                HeaderBudget(text = budget.name, onDeleteItem = {lFixedBudgets.remove(budget)})
                InfoBudget(budget.value, budget.start_date, budget.end_date)
            }
        }
    }
}