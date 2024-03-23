package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.calendar
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.headerSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.okButton
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showMenuCurrencies
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showMenuSubcategories
import com.barbuceanuconstantin.proiectlicenta.warningNotSelectedCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val showMeniuValute = mutableStateOf(false)
private val showAB = mutableStateOf(true)
private val showPB = mutableStateOf(true)
private val showDB = mutableStateOf(true)
private val showMeniuSubcategorys = mutableStateOf(false)

private val dateButton = mutableStateOf(false)

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()
private fun adaugareTranzactie(l: SnapshotStateList<Tranzactie>,
                               currency:String, subcategory:String,
                               valueSum:String, payee:String, date:String,
                               description:String) {
    val newTranzactie = Tranzactie(valueSum.toDouble(), currency, description, subcategory, date, payee)
    l.add(0, newTranzactie)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun showTransactionDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    lActive: SnapshotStateList<Tranzactie>,
    lPasive: SnapshotStateList<Tranzactie>,
    lDatorii: SnapshotStateList<Tranzactie>
) {
    var currency by remember { mutableStateOf("") }
    var subcategory by remember { mutableStateOf("") }
    var payee by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }

    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    var date by remember { mutableStateOf(formattedDate) }

    var description by remember { mutableStateOf("") }

    if (dateButton.value) {
        Column( horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.fillMaxHeight(100F / LocalConfiguration.current.screenHeightDp))

            val dateMutable: MutableState<String> = mutableStateOf(date)
            calendar(dateMutable)
            date = dateMutable.value

            okButton(ok = dateButton)
        }
    } else {
        Scaffold() { innerPadding ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(innerPadding)
            ) {
                headerSelectCategoryOrTransactionWindow(showAB, showPB, showDB)
                Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
                Box(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (!(showAB.value && showPB.value && showDB.value) && !showMeniuValute.value && !showMeniuSubcategorys.value) {
                                showMeniuSubcategorys.value = !showMeniuSubcategorys.value
                            }
                        }
                    ) { Text(stringResource(R.string.mesaj_selectare_subcategory)) }
                }
                Box(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (!(showAB.value && showPB.value && showDB.value) && !showMeniuSubcategorys.value && !showMeniuValute.value) {
                                showMeniuValute.value = !showMeniuValute.value
                            }
                        }
                    ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
                }

                if (!showMeniuValute.value && !showMeniuSubcategorys.value) {
                    if (!(showAB.value && showPB.value && showDB.value)) {
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))
                        Text(
                            text = "${stringResource(id = R.string.subcategory)} : $subcategory",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))
                        Text(
                            text = "${stringResource(id = R.string.valuta)} : $currency",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TextField(
                                value = valueSum,
                                onValueChange = { valueSum = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                label = { Text(stringResource(id = R.string.introduceti_suma)) },
                                maxLines = 2,
                            )

                            TextField(
                                value = payee,
                                onValueChange = { payee = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text(text = stringResource(id = R.string.furnizor_sau_beneficiar)) },
                                maxLines = 2,
                            )

                            Spacer(Modifier.fillMaxHeight(30f / LocalConfiguration.current.screenHeightDp))
                            Button(
                                onClick = { dateButton.value = !dateButton.value }
                            ) { Text(stringResource(R.string.data)) }
                            Spacer(Modifier.fillMaxHeight(30f / LocalConfiguration.current.screenHeightDp))

                            TextField(
                                value = date,
                                onValueChange = { date = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text(text = stringResource(id = R.string.data)) },
                                maxLines = 2,
                            )

                            TextField(
                                value = description,
                                onValueChange = { description = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text(text = stringResource(id = R.string.descriere)) },
                                maxLines = 2,
                            )

                            Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(onClick = {
                                    if (currency != "" && subcategory != "" && valueSum != "" && payee != "" && date != "" && description != "") {
                                        if (showAB.value && !showPB.value && !showDB.value) {
                                            adaugareTranzactie(
                                                lActive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Furnizor : $payee",
                                                date,
                                                description
                                            )
                                        } else if (showPB.value && !showAB.value && !showDB.value) {
                                            adaugareTranzactie(
                                                lPasive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Beneficiar : $payee",
                                                date,
                                                description
                                            )
                                        } else if (showDB.value && !showAB.value && !showPB.value) {
                                            adaugareTranzactie(
                                                lDatorii,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                payee,
                                                date,
                                                description
                                            )
                                        }
                                    }
                                    resetButtons(showAB, showPB, showDB)
                                    onConfirmation()
                                }) { Text(stringResource(R.string.confirmare)) }

                                Spacer(modifier = Modifier.fillMaxWidth(30f / LocalConfiguration.current.screenWidthDp))

                                Button(onClick = {
                                    resetButtons(showAB, showPB, showDB)
                                    onDismissRequest()
                                }) { Text(stringResource(R.string.renuntare)) }
                            }
                        }
                    } else {
                        warningNotSelectedCategory()
                    }
                } else {
                    if (showAB.value && showPB.value && showDB.value) {
                        warningNotSelectedCategory()
                    } else {
                        if (showMeniuValute.value && !showMeniuSubcategorys.value) {
                            showMenuCurrencies(showMeniuValute = showMeniuValute) {
                                currency = it
                            }
                        } else if (!showMeniuValute.value && showMeniuSubcategorys.value) {
                            if (showAB.value && !showPB.value && !showDB.value) {
                                showMenuSubcategories(
                                    lSubcategorys = listaSubcategorysActive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showPB.value && !showAB.value && !showDB.value) {
                                showMenuSubcategories(
                                    lSubcategorys = listaSubcategorysPasive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showDB.value && !showAB.value && !showPB.value) {
                                showMenuSubcategories(
                                    lSubcategorys = listaSubcategorysDatorii,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}