package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SwipeCard
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource

data class Budget(
    var start_date: String,
    var end_date: String,
    var name: String,
    var value: Double
)

@Composable
private fun HeaderBudget(text: String, onDeleteItem: () -> Unit) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.brown_cream))) {
            Text(text = text, fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
                 fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        dimensionResource(id = R.dimen.eight_dp)
                    )
                    .weight(1f))

            IconButton(onClick = onDeleteItem, modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = colorResource(id = R.color.dark_blue))
            }
        }
    }
}

@Composable
fun InfoBudget(value: Double, startDate: String, endDate: String) {
    Column(modifier = Modifier.background(colorResource(id = R.color.light_cream_gray))) {
        Text(
            text = stringResource(id = R.string.prag_superior) + " $value",
            fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin))
        )

        Text(
            text = stringResource(id = R.string.data_inceput) + " $startDate",
            fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin))
        )

        Text(
            text = stringResource(id = R.string.data_final) + " $endDate",
            fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin))
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BudgetsLazyColumn(lFixedBudgets: SnapshotStateList<Budget>) {
    LazyColumn(Modifier.fillMaxSize()) {
        lFixedBudgets.forEach() { budget ->
            this@LazyColumn.stickyHeader {
                SwipeCard (onSwipeLeft = { lFixedBudgets.remove(budget) },
                           onSwipeRight = { lFixedBudgets.remove(budget) }){
                    Card(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.ten_dp)),
                    ) {
                        HeaderBudget(
                            text = budget.name,
                            onDeleteItem = { lFixedBudgets.remove(budget) })
                        InfoBudget(budget.value, budget.start_date, budget.end_date)
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))
            }
        }
    }
}