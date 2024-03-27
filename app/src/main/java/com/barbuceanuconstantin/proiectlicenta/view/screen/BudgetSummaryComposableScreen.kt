@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.FourthButton
import com.barbuceanuconstantin.proiectlicenta.IntToMonth
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.ThreeTopButtons
import com.barbuceanuconstantin.proiectlicenta.data.model.SummaryTranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.getStartAndEndDateOfWeek

private val dateButton = mutableStateOf(false)
@Composable
fun SelectDay(dateMutable: MutableState<String>) {
    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_zi), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.ziua)} ${dateMutable.value}", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp)
}

@Composable
fun SelectWeek(dateMutable: MutableState<String>) {
    val limits = getStartAndEndDateOfWeek(dateMutable.value)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_saptamana), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.saptamana)} (${limits.first} ; ${limits.second})",
        fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp
    )
}
@Composable
fun SelectMonth(dateMutable: MutableState<String>, monthMutable: MutableState<String>) {
    val month : Int = (dateMutable.value[5].code - 48) * 10 + (dateMutable.value[6].code - 48)
    IntToMonth(month, monthMutable)

    Button(onClick = { dateButton.value = !dateButton.value }) {
        Text(text = stringResource(id = R.string.selectare_luna), fontSize = 20.sp)
    }

    Text(text = "${stringResource(id = R.string.luna)} ${monthMutable.value}", fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp
    )
}

@Composable
fun BudgetSummaryComposableScreen(daily: MutableState<Boolean>,
                                  weekly: MutableState<Boolean>,
                                  monthly: MutableState<Boolean>,
                                  lTrA: SnapshotStateList<Tranzactie>,
                                  lTrP: SnapshotStateList<Tranzactie>,
                                  dateMutable: MutableState<String>,
                                  monthMutable : MutableState<String>) {
    if (dateButton.value) {
        DatePickerDialog(onDismissRequest = {dateButton.value = false},
                         confirmButton = {},
                         dismissButton = {}) {
            Column(modifier = Modifier.fillMaxSize()) {
                Calendar(onDateSelected = {
                    selectedDate -> dateMutable.value = selectedDate // Update the date value
                })

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OkButton(ok = dateButton) // Confirm button
                }
            }
        }
    } else {
        Scaffold() { innerPadding ->
            Column( modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
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
                    ThreeTopButtons(
                        first = daily, second = weekly, third = monthly,
                                    firstId = R.string.zilnic, secondId = R.string.saptamanal, thirdId = R.string.lunar)
                }

                FourthButton(id = R.string.total, first = daily, second = weekly, third = monthly)

                //Aici voi face bilantul total al cheltuielilor si veniturilor.
                //Dar pentru inceput le voi lista pe toate fara sa filtrez, asta si pentru ca
                //o sa fie mai relevant probabil cand conectez cu baza de date.
                if (daily.value && !weekly.value && !monthly.value) {
                    //Se selecteaza ziua pentru care se vrea bilantul cheltuielilor si veniturilor

                    SelectDay(dateMutable)
                } else if (weekly.value && !daily.value && !monthly.value) {
                    //Se selecteaza saptamana pentru care se vrea bilantul cheltuielilor si veniturilor,
                    //prin selectarea unei zile si extragerea saptamanii din care face parte

                    SelectWeek(dateMutable)
                } else if (monthly.value && !daily.value && !weekly.value) {
                    //Se selecteaza luna pentru care se vrea bilantul cheltuielilor si veniturilor
                    //prin selectarea unei zile si extragerea lunii din care face parte

                    SelectMonth(dateMutable, monthMutable)
                } else if (daily.value && weekly.value && monthly.value) {
                    SummaryTranzactiiLazyColumn(tranzactii = lTrP, first = true, second = false)

                    SummaryTranzactiiLazyColumn(tranzactii = lTrA, first = false, second = true)
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

