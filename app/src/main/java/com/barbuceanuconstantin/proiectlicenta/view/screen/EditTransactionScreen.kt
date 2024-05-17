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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.di.EditTransactionScreenUIState
import com.barbuceanuconstantin.proiectlicenta.listCategories
import com.barbuceanuconstantin.proiectlicenta.stripTime
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.CategoriesMenu
import com.barbuceanuconstantin.proiectlicenta.warningCompleteAllFields
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun EditTransactionScreen(
    onNavigateToBackScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    editTransactionScreenUIState: EditTransactionScreenUIState,
    updateState: (Boolean, Boolean, Boolean) -> Unit,
    updateCategory: (Int) -> Unit,
    updateValueSum: (String) -> Unit,
    updateDescription: (String) -> Unit,
    updatePayee: (String) -> Unit,
    updateDate: (Date) -> Unit,
    updateReadyToGo: (Boolean) -> Unit,
    updateTransaction: (Transactions) -> Unit,
    updateAlertDialog: (Boolean) -> Unit,
    nullCheckFields: () -> Boolean,
    insertCoroutine: suspend (Transactions) -> Unit,
    updateCoroutine: suspend (Transactions) -> Unit,
    updateCategoryNameSimple: (String, String) -> Unit) {

    val transaction = editTransactionScreenUIState.transaction
    val date = editTransactionScreenUIState.date
    val category = editTransactionScreenUIState.category
    val payee = editTransactionScreenUIState.payee
    val description = editTransactionScreenUIState.description
    val valueSum = editTransactionScreenUIState.valueSum
    val showA = editTransactionScreenUIState.showA
    val showP = editTransactionScreenUIState.showP
    val showD = editTransactionScreenUIState.showD
    val readyToGo: Boolean = editTransactionScreenUIState.readyToGo
    val updateAlertDialogBool: Boolean = editTransactionScreenUIState.alertDialog
    val keyboardController = LocalSoftwareKeyboardController.current

    // Obtain the context from your activity or fragment
    val context: Context = LocalContext.current

    // Get the current date
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

    if (updateAlertDialogBool) {
        warningCompleteAllFields(updateAlertDialog)
    }

    //Trebuie sa si adaug categoria in viewState
    if (transaction == null) {
        //Aici se intra la insert.
        if (readyToGo && nullCheckFields()) {
            LaunchedEffect(Unit) {
                insertCoroutine(
                    Transactions(
                        categoryId = category,
                        value = valueSum.toDouble(),
                        description = description,
                        date = date,
                        payee = payee
                    )
                )
            }
            onNavigateToBackScreen()
        } else if (readyToGo && !nullCheckFields()) {
            updateReadyToGo(false)
            updateAlertDialog(true)
        }
    } else {
        //Aici se intra la update.
        if (readyToGo && nullCheckFields()) {
            transaction.categoryId = category
            transaction.value = valueSum.toDouble()
            transaction.description = description
            transaction.date = date
            transaction.payee = payee
            LaunchedEffect(Unit) {
                updateCoroutine(transaction)
            }
            onNavigateToBackScreen()
        } else if (readyToGo && !nullCheckFields()) {
            updateReadyToGo(false)
            updateAlertDialog(true)
        }
    }

    Scaffold(
        topBar = {
            EditTopAppBar(
                            id = R.string.editare_tranzactii,
                            onNavigateToBackScreen = onNavigateToBackScreen,
                            onNavigateToHomeScreen = onNavigateToHomeScreen
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
            if (transaction == null)
                //Add
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD,
                                                                        updateState = updateState,
                                                                        defaultValueSelected = true)
            else
                //Update
                HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD,
                                                                        updateState = updateState)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            if (showA && !showP && !showD) {
                CategoriesMenu(
                    lSubcategorys = editTransactionScreenUIState.listCategoriesRevenue,
                    listType = listCategories.REVENUES,
                    categoryName = editTransactionScreenUIState.categoryName,
                    updateCategoryNameSimple = updateCategoryNameSimple,
                )
            } else if (showP && !showA && !showD) {
                CategoriesMenu(
                    lSubcategorys = editTransactionScreenUIState.listCategoriesExpenses,
                    listType = listCategories.EXPENSES,
                    categoryName = editTransactionScreenUIState.categoryName,
                    updateCategoryNameSimple = updateCategoryNameSimple,
                )
            } else if (showD && !showA && !showP) {
                CategoriesMenu(
                    lSubcategorys = editTransactionScreenUIState.listCategoriesDebts,
                    listType = listCategories.DEBT,
                    categoryName = editTransactionScreenUIState.categoryName,
                    updateCategoryNameSimple = updateCategoryNameSimple,
                )
            } else {
                CategoriesMenu(
                    lSubcategorys = editTransactionScreenUIState.listCategoriesRevenue,
                    listType = listCategories.REVENUES,
                    categoryName = editTransactionScreenUIState.categoryName,
                    updateCategoryNameSimple = updateCategoryNameSimple,
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            OutlinedTextField(
                value = valueSum,
                onValueChange = { updateValueSum(it) },
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
                onValueChange = { updatePayee(it) },
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
                onValueChange = { updateDescription(it) },
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
                value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date),
                enabled = false,
                onValueChange = {},
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
                                updateDate(stripTime(selectedDate.time))
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

                Button( onClick = {
                                    //Trebuie sa adaug tranzactia in viewState. Plus sa fac update
                                    //pe baza de date.
                                    if (!readyToGo) {
                                        updateReadyToGo(true)
                                    }
                                 },
                        modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.margin)))
                { Text(stringResource(R.string.confirmare)) }

                Spacer(Modifier.width(dimensionResource(id = R.dimen.gap)))

                Button(onClick = {onNavigateToBackScreen()}, modifier = Modifier
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