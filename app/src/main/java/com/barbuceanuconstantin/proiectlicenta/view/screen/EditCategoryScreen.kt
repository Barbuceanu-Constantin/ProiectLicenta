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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

@Composable
fun EditCategoryScreen(category: Category? = null,
                       onNavigateToCategoryScreen : () -> Unit,
                       onNavigateToHomeScreen: () -> Unit) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (category != null) {
        if (listaSubcategorysActive.contains(category.name))
            showA.value = true
        else if (listaSubcategorysPasive.contains(category.name))
            showP.value = true
        else if (listaSubcategorysDatorii.contains(category.name))
            showD.value = true
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
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA, showP, showD)

            if (showA.value || showD.value || showP.value) {
                Text(
                    text = stringResource(id = R.string.mesaj_adaugare_subcategory),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.margin),
                            end = dimensionResource(id = R.dimen.margin)
                        ),
                    fontSize = fontDimensionResource(R.dimen.medium_text_size),
                    fontWeight = FontWeight.Bold
                )

                var filledText by remember { mutableStateOf("") }
                if (category != null) filledText = category.name

                Spacer(Modifier.height(dimensionResource(id = R.dimen.middle)))

                OutlinedTextField(
                    value = filledText, onValueChange = { filledText = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = stringResource(R.string.denumire)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ModeEdit,
                            contentDescription = stringResource(id = R.string.add)
                        )
                    },
                    modifier = Modifier.fillMaxWidth().padding(
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
                        Button(onClick = { onNavigateToCategoryScreen() }) {
                            Text(stringResource(R.string.confirmare))
                        }

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.gap)))

                        Button(onClick = { onNavigateToCategoryScreen() }) {
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