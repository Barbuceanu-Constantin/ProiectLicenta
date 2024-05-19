package com.barbuceanuconstantin.proiectlicenta.view.screen

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
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.barbuceanuconstantin.proiectlicenta.EditTopAppBar
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindowSegmentedButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.di.EditCategoryScreenUIState
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.warningCategoryNameAlreadyExists
import com.barbuceanuconstantin.proiectlicenta.warningCompleteAllFields

fun checkForExistence(name: String, list: List<Categories>): Boolean {
    return list.any { it.name == name }
}

@Composable
fun EditCategoryScreen(
                        onNavigateToCategoryScreen : () -> Unit,
                        onNavigateToHomeScreen: () -> Unit,
                        editCategoryScreenUIState: EditCategoryScreenUIState,
                        updateState: (Boolean, Boolean, Boolean) -> Unit,
                        onStateChangedFilledText: (String) -> Unit,
                        onAddCategory: (Categories) -> Unit,
                        insertCoroutine: suspend (Categories) -> Unit,
                        updateCoroutine: suspend (Categories) -> Unit,
                        updateReadyToGo: (Boolean) -> Unit,
                        updateAlertDialog: (Boolean) -> Unit,
                        nullCheckFields: () -> Boolean,
                        updateAlertAlreadyExistDialog: (Boolean) -> Unit
) {
    val filledText: String = editCategoryScreenUIState.filledText
    val showA: Boolean = editCategoryScreenUIState.showA
    val showP: Boolean = editCategoryScreenUIState.showP
    val showD: Boolean = editCategoryScreenUIState.showD
    val readyToGo: Boolean = editCategoryScreenUIState.readyToGo
    val category = editCategoryScreenUIState.category
    val updateAlertDialogBool: Boolean = editCategoryScreenUIState.alertDialog
    val updateAlertAlreadyExistDialogBool: Boolean = editCategoryScreenUIState.alertAlreadyExistDialog
    val keyboardController = LocalSoftwareKeyboardController.current

    if (updateAlertDialogBool) {
        warningCompleteAllFields(updateAlertDialog)
    } else if (updateAlertAlreadyExistDialogBool) {
        warningCategoryNameAlreadyExists(updateAlertAlreadyExistDialog)
    }

    //Trebuie sa si adaug categoria in viewState
    if (category == null) {
        //Aici se intra la insert.
        if (readyToGo) {
            if (showA) {
                LaunchedEffect(Unit) {
                    insertCoroutine(
                        Categories(
                            name = filledText,
                            mainCategory = "Active"
                        )
                    )
                }
            } else if (showP) {
                LaunchedEffect(Unit) {
                    insertCoroutine(
                        Categories(
                            name = filledText,
                            mainCategory = "Pasive"
                        )
                    )
                }

            } else {
                //showD == true
                LaunchedEffect(Unit) {
                    insertCoroutine(
                        Categories(
                            name = filledText,
                            mainCategory = "Datorii"
                        )
                    )
                }
            }
            onNavigateToCategoryScreen()
        }
    } else {
        //Aici se intra la update.
        if (readyToGo) {
            category.name = filledText
            if (category.mainCategory == "Active") {
                LaunchedEffect(Unit) {
                    updateCoroutine(category)
                }
            }
            if (category.mainCategory == "Pasive") {
                LaunchedEffect(Unit) {
                    updateCoroutine(category)
                }
            }
            if (category.mainCategory == "Datorii") {
                LaunchedEffect(Unit) {
                    updateCoroutine(category)
                }

            }
            onNavigateToCategoryScreen()
        }
    }

    Scaffold (
        topBar = {
            EditTopAppBar(
                            id = R.string.editare_categorii,
                            onNavigateToBackScreen = onNavigateToCategoryScreen,
                            onNavigateToHomeScreen = onNavigateToHomeScreen
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD, updateState)

            if (showA || showD || showP) {
                Text(
                    text =  if (category != null)
                                stringResource(id = R.string.mesaj_modificare_subcategory)
                            else
                                stringResource(id = R.string.mesaj_adaugare_subcategory),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.margin),
                            end = dimensionResource(id = R.dimen.margin)
                        ),
                    fontSize = fontDimensionResource(R.dimen.medium_text_size),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(dimensionResource(id = R.dimen.middle)))

                OutlinedTextField(
                    value = filledText, onValueChange = { onStateChangedFilledText(it) },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = stringResource(R.string.denumire)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ModeEdit,
                            contentDescription = stringResource(id = R.string.add)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.margin),
                            end = dimensionResource(id = R.dimen.margin)
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Close the keyboard
                        }
                    )
                )

                Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button( onClick = {
                                    if (!readyToGo) {
                                        if (!nullCheckFields())
                                            updateAlertDialog(true)
                                        else if (showA) {
                                            if (checkForExistence(
                                                    filledText,
                                                    editCategoryScreenUIState.revenueCategories
                                                )
                                            ) {
                                                updateAlertAlreadyExistDialog(true)
                                            } else {
                                                updateReadyToGo(true)
                                            }
                                        } else if (showP) {
                                            if (checkForExistence(
                                                    filledText,
                                                    editCategoryScreenUIState.expensesCategories
                                                )
                                            ) {
                                                updateAlertAlreadyExistDialog(true)
                                            } else {
                                                updateReadyToGo(true)
                                            }
                                        } else {
                                            if (checkForExistence(
                                                    filledText,
                                                    editCategoryScreenUIState.debtCategories
                                                )
                                            ) {
                                                updateAlertAlreadyExistDialog(true)
                                            } else {
                                                updateReadyToGo(true)
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = dimensionResource(id = R.dimen.margin))) {
                            Text(stringResource(R.string.confirmare))
                        }

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.gap)))

                        Button( onClick = { onNavigateToCategoryScreen() },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = dimensionResource(id = R.dimen.margin))) {
                            Text(stringResource(R.string.renuntare))
                        }
                    }
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.medium_line)))
            } else {
                WarningNotSelectedCategory()

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(horizontalArrangement = Arrangement.Center)
                    {
                        Button(onClick = { onNavigateToCategoryScreen() }) { Text(stringResource(R.string.renuntare)) }
                    }
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.medium_line)))
            }
        }
    }
}