package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.calendar
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.data.model.summaryTranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.fourthButton
import com.barbuceanuconstantin.proiectlicenta.getStartAndEndDateOfWeek
import com.barbuceanuconstantin.proiectlicenta.intToMonth
import com.barbuceanuconstantin.proiectlicenta.okButton
import com.barbuceanuconstantin.proiectlicenta.threeTopButtons

private val dateButton = mutableStateOf(false)
@Composable
fun selectDay(dateMutable: MutableState<String>) {
    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_zi), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.ziua)} ${dateMutable.value}", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp)
}

@Composable
fun selectWeek(dateMutable: MutableState<String>) {
    val limits = getStartAndEndDateOfWeek(dateMutable.value)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_saptamana), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.saptamana)} (${limits.first} ; ${limits.second})",
        fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp
    )
}
@Composable
fun selectMonth(dateMutable: MutableState<String>, monthMutable: MutableState<String>) {
    val month : Int = (dateMutable.value[5].code - 48) * 10 + (dateMutable.value[6].code - 48)
    intToMonth(month, monthMutable)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_luna), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.luna)} ${monthMutable.value}", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp
    )
}
@Composable
fun budgetSummaryComposableScreen(daily: MutableState<Boolean>,
                                  weekly: MutableState<Boolean>,
                                  monthly: MutableState<Boolean>,
                                  lTrA: SnapshotStateList<Tranzactie>,
                                  lTrP: SnapshotStateList<Tranzactie>,
                                  dateMutable: MutableState<String>,
                                  monthMutable : MutableState<String>) {
    if (dateButton.value) {
        Scaffold() { innerPadding ->
            Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))

                calendar(dateMutable, onDateSelected = { selectedDate ->
                    dateMutable.value = selectedDate // Update the date value
                })

                okButton(ok = dateButton)
            }
        }
    } else {
        Scaffold() { innerPadding ->
            Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))

                Text(text = stringResource(id = R.string.selectare_interval_timp), fontSize = 20.sp, style = TextStyle(
                    fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                    modifier = Modifier.background(colorResource(R.color.light_cream))
                )

                Spacer(modifier = Modifier.fillMaxHeight(20F / LocalConfiguration.current.screenHeightDp))

                Row() {
                    threeTopButtons(first = daily, second = weekly, third = monthly, firstId = R.string.zilnic, secondId = R.string.saptamanal, thirdId = R.string.lunar)
                }

                fourthButton(id = R.string.total, first = daily, second = weekly, third = monthly
                )

                //Aici voi face bilantul total al cheltuielilor si veniturilor.
                //Dar pentru inceput le voi lista pe toate fara sa filtrez, asta si pentru ca
                //o sa fie mai relevant probabil cand conectez cu baza de date.
                if (daily.value && !weekly.value && !monthly.value) {
                    //Se selecteaza ziua pentru care se vrea bilantul cheltuielilor si veniturilor

                    selectDay(dateMutable)
                } else if (weekly.value && !daily.value && !monthly.value) {
                    //Se selecteaza saptamana pentru care se vrea bilantul cheltuielilor si veniturilor,
                    //prin selectarea unei zile si extragerea saptamanii din care face parte

                    selectWeek(dateMutable)
                } else if (monthly.value && !daily.value && !weekly.value) {
                    //Se selecteaza luna pentru care se vrea bilantul cheltuielilor si veniturilor
                    //prin selectarea unei zile si extragerea lunii din care face parte

                    selectMonth(dateMutable, monthMutable)
                } else if (daily.value && weekly.value && monthly.value) {
                    summaryTranzactiiLazyColumn(tranzactii = lTrP, first = true, second = false)

                    summaryTranzactiiLazyColumn(tranzactii = lTrA, first = false, second = true)
                }

                HorizontalDivider(thickness = 3.dp, color = colorResource(id = R.color.black))

                Spacer(modifier = Modifier.fillMaxHeight(30F / LocalConfiguration.current.screenHeightDp))

                Text(text = stringResource(id = R.string.bilant), modifier = Modifier.fillMaxWidth(), fontSize = 40.sp,
                     fontWeight = FontWeight.Bold,
                     color = colorResource(id = R.color.medium_green)
                )
            }
        }
    }
}

