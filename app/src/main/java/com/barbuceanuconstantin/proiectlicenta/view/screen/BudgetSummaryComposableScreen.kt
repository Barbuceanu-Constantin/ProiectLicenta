package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.data.model.subcategorysLazyColumn
import com.barbuceanuconstantin.proiectlicenta.data.model.summaryTranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.data.model.tranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.fourthButton
import com.barbuceanuconstantin.proiectlicenta.threeTopButtons

@Composable
fun budgetSummaryComposableScreen(daily: MutableState<Boolean>,
                                  weekly: MutableState<Boolean>,
                                  monthly: MutableState<Boolean>,
                                  lTrA: SnapshotStateList<Tranzactie>,
                                  lTrP: SnapshotStateList<Tranzactie>) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))
            Text(
                text = stringResource(id = R.string.selectare_interval_timp),
                fontSize = 20.sp,
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.background(Color(245, 215, 175))
            )
            Spacer(modifier = Modifier.fillMaxHeight(20F / LocalConfiguration.current.screenHeightDp))
            Row() {
                threeTopButtons(first = daily, second = weekly, third = monthly,
                    firstId = R.string.zilnic,
                    secondId = R.string.saptamanal,
                    thirdId = R.string.lunar)
            }
            fourthButton(
                id = R.string.total,
                first = daily,
                second = weekly,
                third = monthly
            )

            //Aici voi face bilantul total al cheltuielilor si veniturilor.
            //Dar pentru inceput le voi lista pe toate fara sa filtrez, asta si pentru ca
            //o sa fie mai relevant probabil cand conectez cu baza de date.
            if (daily.value && !weekly.value && !monthly.value) {
            } else if (weekly.value && !daily.value && !monthly.value) {
            } else if (monthly.value && !daily.value && !weekly.value) {
            } else if (daily.value && weekly.value && monthly.value) {
                summaryTranzactiiLazyColumn(
                    tranzactii = lTrP,
                    first = true,
                    second = false
                )
                summaryTranzactiiLazyColumn(
                    tranzactii = lTrA,
                    first = false,
                    second = true
                )
            }

            Divider(color = Color.Black, thickness = 3.dp)

            Spacer(modifier = Modifier.fillMaxHeight(30F / LocalConfiguration.current.screenHeightDp))

            Text(
                text = stringResource(id = R.string.bilant),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(10, 160, 5)
            )
        }
    }
}

