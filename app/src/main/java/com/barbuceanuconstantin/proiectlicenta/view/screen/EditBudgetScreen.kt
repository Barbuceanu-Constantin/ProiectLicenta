@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.CategoriesMenu
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun isDateAfterOrEqualToCurrent(dateString: String, current: LocalDate): Boolean {
    return try {
        val enteredDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        !enteredDate.isBefore(current)
    } catch (e: Exception) {
        false
    }
}
@Composable
fun EditBudgetScreen(onNavigateToFixedBudgetsScreen : () -> Unit,
                     onNavigateToHomeScreen: () -> Unit,
                     budget: Budgets? = null) {
    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val dateMutable1: MutableState<String> = remember { mutableStateOf(formattedDate) }
    val dateMutable2: MutableState<String> = remember { mutableStateOf(formattedDate) }
    val openWarningDialog: MutableState<Boolean> = remember { mutableStateOf(false) }

    var filledText by remember { mutableStateOf("") }
    var valueSum by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (budget != null) {
        dateMutable1.value = budget.startDate
        dateMutable2.value = budget.endDate
        filledText = budget.name
        category = budget.category
        valueSum = budget.upperTreshold.toString()
    }

    // Obtain the context from your activity or fragment
    val context: Context = LocalContext.current

    // Get the current date
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

    Scaffold(
        topBar = {
            EditTopAppBar(
                            id = R.string.editare_bugete,
                            onNavigateToBackScreen = onNavigateToFixedBudgetsScreen,
                            onNavigateToHomeScreen = onNavigateToHomeScreen
                        )
        }
    ) { innerPadding ->
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.gap)))

            Text(text = stringResource(id = R.string.introduceti_detalii_buget),
                 style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.brown)
                 ),
                 fontSize = fontDimensionResource(id = R.dimen.big_text_size),
                 textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle)))

            OutlinedTextField(
                value = filledText, onValueChange = { filledText = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                label = { Text(text = stringResource(R.string.denumire)) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            CategoriesMenu(
                lSubcategorys = listaSubcategorysPasive,
                subcategory = category
            ) {
                category = it
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            OutlinedTextField(
                value = valueSum, onValueChange = { valueSum = it },
                keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Done,
                                                ),
                label = { Text(stringResource(id = R.string.introduceti_suma)) },
                maxLines = 1,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide() // Close the keyboard
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            OutlinedTextField(
                value = dateMutable1.value,
                enabled = false,
                onValueChange = {
                    //Am facut in asa fel incat sa nu poti seta un buget
                    //cu data mai mica decat data curenta.
                    if (isDateAfterOrEqualToCurrent(it, LocalDate.now())) {
                        dateMutable1.value = it
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = stringResource(id = R.string.data_inceput)) },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val datePickerDialog =
                            android.app.DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
                                // Handle the selected date
                                val selectedDate: Calendar = Calendar.getInstance()
                                selectedDate.set(year1, month1, dayOfMonth1)

                                dateMutable1.value =
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                                        selectedDate.time
                                    )
                            }, year, month, dayOfMonth)

                        datePickerDialog.show()
                    }
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
                ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            OutlinedTextField(
                value = dateMutable2.value,
                enabled = false,
                onValueChange = {
                    //Am facut in asa fel incat sa nu poti seta un buget
                    //cu data mai mica decat data curenta.
                    if (isDateAfterOrEqualToCurrent(it, LocalDate.now())) {
                        dateMutable2.value = it
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = stringResource(id = R.string.data_final)) },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val dateString: String = dateMutable1.value
                        val formatter: DateTimeFormatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val localDate: LocalDate = LocalDate.parse(dateString, formatter)
                        val datePickerDialog =

                            android.app.DatePickerDialog(context, { _, year1, month1, dayOfMonth1 ->
                                // Handle the selected date
                                val selectedDate: Calendar = Calendar.getInstance()
                                selectedDate.set(year1, month1, dayOfMonth1)

                                // Perform any necessary operations with the selected date here
                                if (isDateAfterOrEqualToCurrent(
                                        SimpleDateFormat(
                                            "yyyy-MM-dd",
                                            Locale.getDefault()
                                        ).format(selectedDate.time), LocalDate.now()
                                    )
                                ) {
                                    if (isDateAfterOrEqualToCurrent(
                                            SimpleDateFormat(
                                                "yyyy-MM-dd",
                                                Locale.getDefault()
                                            ).format(selectedDate.time), localDate
                                        )
                                    ) {
                                        dateMutable2.value = SimpleDateFormat(
                                            "yyyy-MM-dd",
                                            Locale.getDefault()
                                        ).format(selectedDate.time)
                                    } else {
                                        openWarningDialog.value = true
                                    }
                                } else {
                                    openWarningDialog.value = true
                                }
                            }, year, month, dayOfMonth)

                        datePickerDialog.show()
                    }
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

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom) {
                Button(onClick = {
                                    //La confirmare trebuie ca data de final sa fie dupa data
                                    //de inceput.a
                                    var dateString: String = dateMutable1.value
                                    val formatter: DateTimeFormatter =
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                    val localDate: LocalDate = LocalDate.parse(dateString, formatter)
                                    dateString = dateMutable2.value
                                    if (isDateAfterOrEqualToCurrent(dateString, localDate)) {
                                        onNavigateToFixedBudgetsScreen()
                                    } else {
                                        openWarningDialog.value = true
                                    }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = dimensionResource(id = R.dimen.margin))
                    ) { Text(stringResource(R.string.confirmare)) }

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.gap)))

                Button( onClick = { onNavigateToFixedBudgetsScreen() },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = dimensionResource(id = R.dimen.margin))
                    ) { Text(stringResource(R.string.renuntare)) }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
        }
    }

    if (openWarningDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openWarningDialog.value = false // Dismiss the dialog when the user clicks outside the dialog or on the back button
            },
            title = {
                Text(text = stringResource(id = R.string.avertisment_data))
            },
            text = {
                Text(text = stringResource(id = R.string.avertisment_data_continut))
            },
            confirmButton = {
                Button(
                    onClick = {
                        openWarningDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}
