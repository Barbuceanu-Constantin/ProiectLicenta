package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R

private var sumRevenue: Float = 0f;
private var sumExpenses: Float = 0f;
private var sumDebt: Float = 0f;

@Composable
private fun totalBalance() {
    Column(modifier = Modifier) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 120f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(240, 200, 80), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.active) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 25.sp
            )
        }
        Spacer(Modifier.fillMaxHeight(30f / LocalConfiguration.current.screenHeightDp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 120f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(210, 20, 20), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.pasive) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 25.sp
            )
        }
        Spacer(Modifier.fillMaxHeight(30f / LocalConfiguration.current.screenHeightDp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 120f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(40, 105, 200), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.datorii) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 25.sp
            )
        }
        Spacer(Modifier.fillMaxHeight(20f / LocalConfiguration.current.screenHeightDp))
        HorizontalDivider(thickness = 5.dp, color = Color.Gray)
        Spacer(Modifier.fillMaxHeight(50f / LocalConfiguration.current.screenHeightDp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 120f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(60, 205, 40), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.balanta) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun principalScreenLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))
        totalBalance()
    }
}

