@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuCurrencies
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuSubcategories
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()
@Composable
fun AddTransactionScreen(lActive: SnapshotStateList<Tranzactie>,
                         lPasive: SnapshotStateList<Tranzactie>,
                         lDatorii: SnapshotStateList<Tranzactie>
) {
    var currency by remember { mutableStateOf("") }
    var subcategory by remember { mutableStateOf("") }
    var payee by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val showMeniuValute = remember { mutableStateOf(false) }
    val showMeniuSubcategorys = remember { mutableStateOf(false) }

    val dateButton = remember { mutableStateOf(false) }
    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val date by remember { mutableStateOf(formattedDate) }
    val dateMutable: MutableState<String> = remember { mutableStateOf(date) }
    val showA: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(true) }

    val (item1, item2, item3) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    if (dateButton.value) {
        DatePickerDialog(onDismissRequest = {dateButton.value = false},
                         confirmButton = {}, dismissButton = {}) {
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
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD)

                Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                if (!(showA.value && showP.value && showD.value)) {
                    if (showA.value && !showP.value && !showD.value) {
                        ShowMenuSubcategories(
                            lSubcategorys = listaSubcategorysActive,
                            showMeniuSubcategorys
                        ) {
                            subcategory = it
                        }
                    } else if (showP.value && !showA.value && !showD.value) {
                        ShowMenuSubcategories(
                            lSubcategorys = listaSubcategorysPasive,
                            showMeniuSubcategorys
                        ) {
                            subcategory = it
                        }
                    } else if (showD.value && !showA.value && !showP.value) {
                        ShowMenuSubcategories(
                            lSubcategorys = listaSubcategorysDatorii,
                            showMeniuSubcategorys
                        ) {
                            subcategory = it
                        }
                    }

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                    ShowMenuCurrencies(showMeniuValute = showMeniuValute) {
                        currency = it
                    }

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = valueSum,
                            onValueChange = { valueSum = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done          // Specify imeAction as Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.moveFocus(FocusDirection.Next) }
                            ),
                            label = { Text(stringResource(id = R.string.introduceti_suma)) },
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth().focusRequester(item1)
                                                              .focusProperties {
                                                                    next = item2
                                                                    right = item2
                                                              }
                                                              .padding(start = dimensionResource(id = R.dimen.margin),
                                                                       end = dimensionResource(id = R.dimen.margin))
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        OutlinedTextField(
                            value = payee,
                            onValueChange = { payee = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.moveFocus(FocusDirection.Next) }
                            ),
                            label = { Text(text = stringResource(id = R.string.furnizor_sau_beneficiar)) },
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth().focusRequester(item2)
                                                              .focusProperties {
                                                                  next = item3
                                                                  right = item3
                                                              }
                                                              .padding(start = dimensionResource(id = R.dimen.margin),
                                                                       end = dimensionResource(id = R.dimen.margin))
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            keyboardOptions = KeyboardOptions(
                                                    keyboardType = KeyboardType.Text,
                                                    imeAction = ImeAction.Done,
                                                    capitalization = KeyboardCapitalization.Sentences
                                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() }
                            ),
                            label = { Text(text = stringResource(id = R.string.descriere)) },
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth().focusRequester(item3).padding(start = dimensionResource(id = R.dimen.margin),
                                                                                             end = dimensionResource(id = R.dimen.margin))
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                        OutlinedTextField(
                            value = dateMutable.value,
                            enabled = false,
                            onValueChange = {
                                dateMutable.value = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = { Text(text = stringResource(id = R.string.data)) },
                            maxLines = 1,
                            modifier = Modifier
                                .clickable { dateButton.value = !dateButton.value }
                                .fillMaxWidth().padding(start = dimensionResource(id = R.dimen.margin),
                                                        end = dimensionResource(id = R.dimen.margin)),
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledBorderColor = MaterialTheme.colorScheme.outline,
                                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.hundred)))

                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {

                            Button(onClick = {}) { Text(stringResource(R.string.confirmare)) }

                            Spacer(Modifier.width(dimensionResource(id = R.dimen.thirty_dp)))

                            Button(onClick = {}) { Text(stringResource(R.string.renuntare)) }
                        }
                    }
                } else if (showA.value && showP.value && showD.value) {
                    Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
                    WarningNotSelectedCategory()
                }
            }
        }
    }
}