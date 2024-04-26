@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.CategoriesMenu
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

var listaSubcategorysActive = subcategorysPredefiniteActive.toMutableList()
var listaSubcategorysPasive = subcategorysPredefinitePasive.toMutableList()
var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.toMutableList()
@Composable
fun EditTransactionScreen(onNavigateToHomeScreen : () -> Unit,
                          transaction: Transaction? = null,
                          index: Int = 0) {
    var subcategory by remember { mutableStateOf("") }
    var payee by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }

    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val date by remember { mutableStateOf(formattedDate) }
    val dateMutable: MutableState<String> = remember { mutableStateOf(date) }

    if (transaction != null) {
        subcategory = transaction.subcategory
        payee = transaction.payee
        valueSum = transaction.suma.toString()
        description = transaction.descriere
        dateMutable.value = transaction.data

        if (listaSubcategorysActive.contains(transaction.subcategory))
            showA.value = true
        else if (listaSubcategorysPasive.contains(transaction.subcategory))
            showP.value = true
        else if (listaSubcategorysDatorii.contains(transaction.subcategory))
            showD.value = true
    } else {
        when (index) {
            0 -> showA.value = true
            1 -> showP.value = true
            2 -> showD.value = true
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    // Obtain the context from your activity or fragment
    val context: Context = LocalContext.current

    // Get the current date
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

    Scaffold(
        topBar = {
            EditTopAppBar(id = R.string.editare_tranzactii)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
            if (transaction == null)
                //Add
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD,
                                                                        defaultValueSelected = true)
            else
                //Update
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            if (showA.value && !showP.value && !showD.value) {
                CategoriesMenu(
                    lSubcategorys = listaSubcategorysActive,
                    subcategory = subcategory
                ) {
                    subcategory = it
                }
            } else if (showP.value && !showA.value && !showD.value) {
                CategoriesMenu(
                    lSubcategorys = listaSubcategorysPasive,
                    subcategory = subcategory
                ) {
                    subcategory = it
                }
            } else if (showD.value && !showA.value && !showP.value) {
                CategoriesMenu(
                    lSubcategorys = listaSubcategorysDatorii,
                    subcategory = subcategory
                ) {
                    subcategory = it
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

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
                    ),
                suffix = { Text ("RON") },
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

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
                    .clickable {
                        val datePickerDialog =
                            DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
                                // Handle the selected date
                                val selectedDate: Calendar = Calendar.getInstance()
                                selectedDate.set(year1, month1, dayOfMonth1)

                                // Perform any necessary operations with the selected date here
                                // For example, update a TextView with the selected date
                                dateMutable.value =
                                    SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(
                                        selectedDate.time
                                    )
                            }, year, month, dayOfMonth)

                        datePickerDialog.show()
                    }
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    ),
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

                Button(onClick = {onNavigateToHomeScreen()}, modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.margin)))
                { Text(stringResource(R.string.confirmare)) }

                Spacer(Modifier.width(dimensionResource(id = R.dimen.gap)))

                Button(onClick = {onNavigateToHomeScreen()}, modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = dimensionResource(
                            id = R.dimen.margin
                        )
                    ))
                { Text(stringResource(R.string.renuntare)) }
            }

            Spacer(Modifier.height(dimensionResource(id = R.dimen.medium_line)))
        }
    }
}