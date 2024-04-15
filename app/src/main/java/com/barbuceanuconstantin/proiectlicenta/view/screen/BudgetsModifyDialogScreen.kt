@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateButton1 = mutableStateOf(false)
private val dateButton2 = mutableStateOf(false)

private fun addBudget(lFixedBudgets: SnapshotStateList<Budget>, filledText: String, valueSum: String,
                      date1: String, date2: String) {
    val newBudget = Budget(date1, date2, filledText, valueSum.toDouble())
    lFixedBudgets.add(0, newBudget)
}

@RequiresApi(Build.VERSION_CODES.O)
fun isDateAfterOrEqualToCurrent(dateString: String, current: LocalDate): Boolean {
    return try {
        val enteredDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        !enteredDate.isBefore(current)
    } catch (e: Exception) {
        false
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowBudgetDialog(onDismissRequest: () -> Unit, onConfirmation: () -> Unit, lFixedBudgets: SnapshotStateList<Budget>,
                     dateMutable1: MutableState<String>, dateMutable2: MutableState<String>) {
    var filledText by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (dateButton1.value && !dateButton2.value) {
        DatePickerDialog(onDismissRequest = { dateButton1.value = !dateButton1.value },
                         confirmButton = {},
                         dismissButton = {}) {
            Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Calendar(onDateSelected = { selectedDate ->
                    if (isDateAfterOrEqualToCurrent(selectedDate, LocalDate.now())) {
                        dateMutable1.value = selectedDate
                    }
                })

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                OkButton(ok = dateButton1)
            }
        }
    } else if (!dateButton1.value && dateButton2.value) {
        DatePickerDialog(onDismissRequest = {dateButton2.value = !dateButton2.value},
                         confirmButton = {},
                         dismissButton = {}) {
            Column( modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Calendar(onDateSelected = { selectedDate ->
                    if (isDateAfterOrEqualToCurrent(selectedDate, LocalDate.now())) {
                        dateMutable2.value = selectedDate
                    }
                })

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                OkButton(ok = dateButton2)
            }
        }
    } else if (!dateButton1.value && !dateButton2.value) {
        Scaffold { innerPadding ->
            Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                Text(text = stringResource(id = R.string.introduceti_detalii_buget),
                     style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.brown)
                     ),
                     fontSize = fontDimensionResource(id = R.dimen.seventy_five_sp)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                OutlinedTextField(
                    value = filledText, onValueChange = { filledText = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = stringResource(R.string.denumire)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done // Specify imeAction as Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Close the keyboard
                        }
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

                OutlinedTextField(
                    value = valueSum, onValueChange = { valueSum = it },
                    keyboardOptions = KeyboardOptions(
                                                    keyboardType = KeyboardType.Number,
                                                    imeAction = ImeAction.Done),
                    label = { Text(stringResource(id = R.string.introduceti_suma)) },
                    maxLines = 1,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Close the keyboard
                        }
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

                OutlinedTextField(
                    value = dateMutable1.value,
                    enabled = false,
                    onValueChange = {
                        if (isDateAfterOrEqualToCurrent(it, LocalDate.now())) {
                            dateMutable1.value = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = stringResource(id = R.string.data_inceput)) },
                    maxLines = 1,
                    modifier = Modifier.clickable { dateButton1.value = !dateButton1.value },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

                OutlinedTextField(
                    value = dateMutable2.value,
                    enabled = false,
                    onValueChange = {
                        if (isDateAfterOrEqualToCurrent(it, LocalDate.now())) {
                            dateMutable2.value = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = stringResource(id = R.string.data_final)) },
                    maxLines = 1,
                    modifier = Modifier.clickable { dateButton2.value = !dateButton2.value },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.hundred)))

                Row {
                    Button(onClick = {
                        if (filledText != "" && valueSum != "") {
                            addBudget(lFixedBudgets, filledText, valueSum,
                                      dateMutable1.value, dateMutable2.value)
                        }
                        onConfirmation()
                    }) { Text(stringResource(R.string.confirmare)) }

                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.thirty_dp)))

                    Button(onClick = {
                        onDismissRequest()
                    }) { Text(stringResource(R.string.renuntare)) }
                }
            }
        }
    }
}
