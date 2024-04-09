package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

private fun addSubcategory(l: MutableList<Subcategory>, firstLetter:String, filledText:String) {
    val foundSubcategory = l.find{it.name == firstLetter}

    if (foundSubcategory != null) {
        foundSubcategory.items.add(filledText)
    } else {
        val insertionIndex = l.binarySearch { it.name.compareTo(firstLetter) }

        val newSubcategory = Subcategory(name = firstLetter, items = mutableStateListOf(filledText))

        if (insertionIndex < 0) {
            l.add(-insertionIndex - 1, newSubcategory)
        } else {
            l.add(insertionIndex, newSubcategory)
        }
    }
}

@Composable
fun ShowAddSubcategoryScreen(strId: Int, lActive: MutableList<Subcategory>,
                             lPasive: MutableList<Subcategory>, lDatorii: MutableList<Subcategory>,
                             addButton: MutableState<Boolean>
) {
    val specificMessage = stringResource(id = strId)
    val showA: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(true) }

    Column( horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly ) {
        HeaderSelectCategoryOrTransactionWindow(showA, showP, showD)

        Text(text = specificMessage, modifier = Modifier.fillMaxWidth(),
             fontSize = fontDimensionResource(R.dimen.fifty_sp),
             fontWeight = FontWeight.Bold)

        if (!(showA.value && showD.value && showP.value)) {
            var filledText by remember { mutableStateOf("") }

            Spacer(Modifier.height(dimensionResource(id = R.dimen.fifty_sp)))

            OutlinedTextField(
                value = filledText, onValueChange = { filledText = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                label = { Text(text = stringResource(R.string.denumire)) },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ModeEdit,
                        contentDescription = stringResource(id = R.string.add))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(dimensionResource(id = R.dimen.two_hundred)))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    if (filledText != "") {
                        val firstLetter = filledText[0].toString().uppercase()
                        var ok = true

                        if (showA.value && !showP.value && !showD.value) {
                            if (subcategorysPredefinitePasive.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (subcategorysPredefiniteDatorii.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (ok)
                                addSubcategory(lActive, firstLetter, filledText)
                        } else if (showP.value && !showA.value && !showD.value) {
                            if (subcategorysPredefiniteActive.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (subcategorysPredefiniteDatorii.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (ok)
                                addSubcategory(lPasive, firstLetter, filledText)
                        } else if (showD.value && !showA.value && !showP.value) {
                            if (subcategorysPredefiniteActive.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (subcategorysPredefinitePasive.any { entry ->
                                    entry.value.contains(filledText)
                                }) {
                                ok = false
                            }
                            if (ok)
                                addSubcategory(lDatorii, firstLetter, filledText)
                        }
                    }

                    resetButtons(showA, showP, showD)
                    addButton.value = !addButton.value
                }) {
                    Text(stringResource(R.string.confirmare))
                }

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.thirty_dp)))

                Button( onClick = {
                    resetButtons(showA, showP, showD)
                    addButton.value = !addButton.value
                } ) {
                    Text(stringResource(R.string.renuntare))
                }
            }

            Spacer(Modifier.height(dimensionResource(id = R.dimen.ten_dp)))
        } else {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

            WarningNotSelectedCategory()

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.hundred)))

            Row(modifier = Modifier.fillMaxWidth(0.5F), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    resetButtons(showA, showP, showD)
                    addButton.value = !addButton.value
                }, modifier = Modifier.weight(1f)) { Text(stringResource(R.string.renuntare)) }
            }
        }
    }
}