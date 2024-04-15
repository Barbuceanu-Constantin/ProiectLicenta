@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuCurrencies
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuSubcategories

private val showMeniuValute = mutableStateOf(false)
private val showMeniuSubcategorys = mutableStateOf(false)
private val dateButton = mutableStateOf(false)

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()
private fun adaugareTranzactie(l: SnapshotStateList<Tranzactie>, currency:String, subcategory:String,
                               valueSum:String, payee:String, date:String, description:String) {
    val newTranzactie = Tranzactie(valueSum.toDouble(), currency, description, subcategory, date, payee)
    l.add(0, newTranzactie)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowTransactionDialog(onDismissRequest: () -> Unit, onConfirmation: () -> Unit,
                          lActive: SnapshotStateList<Tranzactie>, lPasive: SnapshotStateList<Tranzactie>,
                          lDatorii: SnapshotStateList<Tranzactie>, dateMutable: MutableState<String>,
                          showAB: MutableState<Boolean>, showPB: MutableState<Boolean>, showDB: MutableState<Boolean>
) {
    var currency by remember { mutableStateOf("") }
    var subcategory by remember { mutableStateOf("") }
    var payee by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (dateButton.value) {
        DatePickerDialog(onDismissRequest = {dateButton.value = false},
            confirmButton = {},
            dismissButton = {}) {
                Column( modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    Calendar(onDateSelected = { selectedDate -> dateMutable.value = selectedDate })

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                    OkButton(ok = dateButton)
                }
        }
    } else {
        Scaffold { innerPadding ->
            Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showAB, showPB, showDB)

                Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row{
                        Button(
                            onClick = {
                                if (!(showAB.value && showPB.value && showDB.value) && !showMeniuValute.value && !showMeniuSubcategorys.value) {
                                    showMeniuSubcategorys.value = !showMeniuSubcategorys.value
                                }
                            }
                        ) { Text(stringResource(R.string.mesaj_selectare_subcategory)) }

                        Spacer(Modifier.width(dimensionResource(id = R.dimen.ten_dp)))

                        Button(
                            onClick = {
                                if (!(showAB.value && showPB.value && showDB.value) && !showMeniuSubcategorys.value && !showMeniuValute.value) {
                                    showMeniuValute.value = !showMeniuValute.value
                                }
                            }
                        ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
                    }
                }

                if (!showMeniuValute.value && !showMeniuSubcategorys.value) {
                    if (!(showAB.value && showPB.value && showDB.value)) {
                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        Text(text = "${stringResource(id = R.string.subcategory)} : $subcategory", fontWeight = FontWeight.SemiBold,
                             modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        Text(text = "${stringResource(id = R.string.valuta)} : $currency", fontWeight = FontWeight.SemiBold,
                             modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            OutlinedTextField(
                                value = valueSum,
                                onValueChange = { valueSum = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done // Specify imeAction as Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                ),
                                label = { Text(stringResource(id = R.string.introduceti_suma)) },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                            OutlinedTextField(
                                value = payee,
                                onValueChange = { payee = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                ),
                                label = { Text(text = stringResource(id = R.string.furnizor_sau_beneficiar)) },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                            OutlinedTextField(
                                    value = dateMutable.value,
                                    enabled = false,
                                    onValueChange = { dateMutable.value = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    label = { Text(text = stringResource(id = R.string.data)) },
                                    maxLines = 1,
                                    modifier = Modifier.clickable { dateButton.value = !dateButton.value }
                                                        .fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                            )

                            Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                            OutlinedTextField(
                                value = description,
                                onValueChange = { description = it },
                                keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Text,
                                                        imeAction = ImeAction.Done
                                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                ),
                                label = { Text(text = stringResource(id = R.string.descriere)) },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(dimensionResource(id = R.dimen.hundred)))

                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center) {

                                Button(onClick = {
                                    if (currency != "" && subcategory != "" && valueSum != "" && payee != "" && dateMutable.value != "" && description != "") {
                                        if (showAB.value && !showPB.value && !showDB.value) {
                                            adaugareTranzactie(
                                                lActive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Furnizor -> $payee",
                                                dateMutable.value,
                                                description
                                            )
                                        } else if (showPB.value && !showAB.value && !showDB.value) {
                                            adaugareTranzactie(
                                                lPasive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Beneficiar -> $payee",
                                                dateMutable.value,
                                                description
                                            )
                                        } else if (showDB.value && !showAB.value && !showPB.value) {
                                            adaugareTranzactie(
                                                lDatorii,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                payee,
                                                dateMutable.value,
                                                description
                                            )
                                        }
                                    }
                                    resetButtons(showAB, showPB, showDB)
                                    onConfirmation()
                                }) { Text(stringResource(R.string.confirmare)) }

                                Spacer(Modifier.width(dimensionResource(id = R.dimen.thirty_dp)))

                                Button(onClick = {
                                    resetButtons(showAB, showPB, showDB)
                                    onDismissRequest()
                                }) { Text(stringResource(R.string.renuntare)) }
                            }
                        }
                    } else {
                        Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
                        WarningNotSelectedCategory()
                    }
                } else {
                    if (showAB.value && showPB.value && showDB.value) {
                        Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
                        WarningNotSelectedCategory()
                    } else {
                        Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

                        if (showMeniuValute.value && !showMeniuSubcategorys.value) {
                            ShowMenuCurrencies(showMeniuValute = showMeniuValute) {
                                currency = it
                            }
                        } else if (!showMeniuValute.value && showMeniuSubcategorys.value) {
                            if (showAB.value && !showPB.value && !showDB.value) {
                                ShowMenuSubcategories(
                                    lSubcategorys = listaSubcategorysActive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showPB.value && !showAB.value && !showDB.value) {
                                ShowMenuSubcategories(
                                    lSubcategorys = listaSubcategorysPasive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showDB.value && !showAB.value && !showPB.value) {
                                ShowMenuSubcategories(
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