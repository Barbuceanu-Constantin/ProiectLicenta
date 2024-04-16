@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalComposeUiApi::class
)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
//import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuCurrencies
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.ShowMenuSubcategories
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()
@Composable
fun EditTransactionScreen(transaction: Transaction? = null) {
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
    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }

    if (transaction == null) showA.value = true

    val (item1, item2, item3) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Obtain the context from your activity or fragment
    val context: Context = LocalContext.current

    // Get the current date
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

    Scaffold { innerPadding ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)) {
            if (transaction == null)
                //Add
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD,
                                                                        defaultValueSelected = true)
            else
                //Update
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
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(stringResource(id = R.string.introduceti_suma)) },
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.margin),
                                end = dimensionResource(id = R.dimen.margin)
                            )
                            .weight(0.3f)
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))

                    OutlinedTextField(
                        value = payee,
                        onValueChange = { payee = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        label = { Text(text = stringResource(id = R.string.furnizor_sau_beneficiar)) },
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(start = dimensionResource(id = R.dimen.margin),
                                                                   end = dimensionResource(id = R.dimen.margin))
                                                          .weight(0.3f)
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
                        modifier = Modifier.fillMaxWidth().focusRequester(item3)
                                            .padding(start = dimensionResource(id = R.dimen.margin),
                                                     end = dimensionResource(id = R.dimen.margin))
                                            .weight(0.3f)
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
                                                    end = dimensionResource(id = R.dimen.margin))
                            .weight(0.3f),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    )

                    Row(modifier = Modifier.fillMaxWidth().weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom) {

                        Button(onClick = {}, modifier = Modifier.weight(1f).padding(start = dimensionResource(
                            id = R.dimen.margin
                        )))
                        { Text(stringResource(R.string.confirmare)) }

                        Spacer(Modifier.width(dimensionResource(id = R.dimen.thirty_dp)))

                        Button(onClick = {}, modifier = Modifier.weight(1f).padding(end = dimensionResource(
                            id = R.dimen.margin
                        )))
                        { Text(stringResource(R.string.renuntare)) }
                    }
                }
            }
        }
    }

    if (dateButton.value) {
        val datePickerDialog = DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
            // Handle the selected date
            val selectedDate: Calendar = Calendar.getInstance()
            selectedDate.set(year1, month1, dayOfMonth1)

            // Perform any necessary operations with the selected date here
            // For example, update a TextView with the selected date
            dateMutable.value = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(selectedDate.time)
            dateButton.value = false
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }
}