@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuCurrencies
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuSubcategories

private val showMeniuValute1 = mutableStateOf(false)
private val showMeniuSubcategorys1 = mutableStateOf(false)
private val dateButton = mutableStateOf(false)

@Composable
fun TransactionUpdateScreen(indexUpdate: Int, trList: SnapshotStateList<Tranzactie>, updateTransactionButton: MutableState<Boolean>,
                            subcategoriesList: MutableList<String>, dateMutable: MutableState<String>) {
    val tr = trList[indexUpdate]
    //Log.e("asdsad","recompozitie")
    var currency by remember { mutableStateOf(tr.valuta) }
    var subcategory by remember { mutableStateOf(tr.subcategory) }
    var payee by remember { mutableStateOf(tr.payee) }
    var valueSum by remember { mutableStateOf(tr.suma.toString()) }
    var description by remember { mutableStateOf(tr.descriere) }

    if (dateButton.value) {
        DatePickerDialog(onDismissRequest = {dateButton.value = false},
                         confirmButton = {},
                         dismissButton = {}) {
            Column(modifier = Modifier.fillMaxSize()) {
                Calendar(onDateSelected = { selectedDate ->
                    dateMutable.value = selectedDate // Update the date value
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
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(innerPadding),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                ShowMenuSubcategories(okButton = false, lSubcategorys = subcategoriesList,
                                      showMeniuSubcategorys = showMeniuSubcategorys1) {
                    subcategory = it
                }

                OutlinedTextField(value = subcategory, onValueChange = { subcategory = it },
                                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                  label = { Text(stringResource(id = R.string.subcategory)) }, maxLines = 2)

                ShowMenuCurrencies(showMeniuValute = showMeniuValute1, okButton = false) {
                    currency = it
                }

                OutlinedTextField(
                    value = currency,
                    onValueChange = { currency = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(id = R.string.valuta)) },
                    maxLines = 2
                )

                Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))

                OutlinedTextField(
                    value = payee,
                    onValueChange = { payee = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(id = R.string.furnizor_sau_beneficiar)) },
                    maxLines = 2
                )

                Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))

                OutlinedTextField(
                    value = dateMutable.value,
                    enabled = false,
                    onValueChange = { dateMutable.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(id = R.string.data)) },
                    maxLines = 2,
                    modifier = Modifier.clickable { dateButton.value = !dateButton.value },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )

                OutlinedTextField(
                    value = valueSum,
                    onValueChange = { valueSum = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(id = R.string.introduceti_suma)) },
                    maxLines = 2
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(id = R.string.descriere)) },
                    maxLines = 2
                )
                
                trList[indexUpdate].valuta = currency
                trList[indexUpdate].payee = payee
                trList[indexUpdate].subcategory = subcategory
                trList[indexUpdate].data = dateMutable.value
                trList[indexUpdate].descriere = description
                if (valueSum != "")
                    trList[indexUpdate].suma = valueSum.toDouble()

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                OkButton(ok = updateTransactionButton)
            }
        }
    }
}