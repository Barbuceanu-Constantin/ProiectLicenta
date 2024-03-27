package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.barbuceanuconstantin.proiectlicenta.HeaderSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.WarningNotSelectedCategory
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.resetButtons

private val showA = mutableStateOf(true)
private val showP = mutableStateOf(true)
private val showD = mutableStateOf(true)

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

private fun eliminareSubcategory(l: MutableList<Subcategory>, firstLetter:String, filledText:String) {
    val foundSubcategory = l.find{it.name == firstLetter}
    if (foundSubcategory != null) {
        foundSubcategory.items.remove(filledText)
    }
}

@Composable
fun ShowCategoryDialog(onDismissRequest: () -> Unit, onConfirmation: () -> Unit, strId: Int,
                       lActive: MutableList<Subcategory>, lPasive: MutableList<Subcategory>,
                       lDatorii: MutableList<Subcategory>
) {
    val specificMessage = stringResource(id = strId)

    Dialog(onDismissRequest = {
        resetButtons(showA, showP, showD)
        onDismissRequest()
    }) {
        Card(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 650F / LocalConfiguration.current.screenHeightDp), shape = RoundedCornerShape(16.dp),) {
            Column() {
                HeaderSelectCategoryOrTransactionWindow(showA, showP, showD)

                Text(text = specificMessage, modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp, fontWeight = FontWeight.Bold
                )

                if (!(showA.value && showD.value && showP.value)) {
                    var filledText by remember { mutableStateOf("") }

                    Spacer(Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp))

                    OutlinedTextField(
                        value = filledText, onValueChange = { filledText = it },
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                        label = { Text(text = stringResource(R.string.denumire)) },
                        placeholder = { Text(text = stringResource(id = R.string.underscores)) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.ModeEdit,
                                contentDescription = stringResource(id = R.string.add))
                        },
                        trailingIcon = {
                            Icon(imageVector = Icons.Outlined.Category,
                                contentDescription = stringResource(id = R.string.add))
                        }
                    )

                    Spacer(Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp))

                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)) {
                        val words = specificMessage.trim().split("\\s+".toRegex())

                        val lastWord = words.last()

                        Button(onClick = {
                            if (filledText != "") {
                                val firstLetter = filledText[0].toString().uppercase()

                                if (lastWord == "adaugati") {
                                    if (showA.value && !showP.value && !showD.value) {
                                        addSubcategory(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        addSubcategory(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        addSubcategory(lDatorii, firstLetter, filledText)
                                    }
                                } else if (lastWord == "eliminati") {
                                    if (showA.value && !showP.value && !showD.value) {
                                        eliminareSubcategory(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        eliminareSubcategory(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        eliminareSubcategory(lDatorii, firstLetter, filledText)
                                    }
                                }
                            }
                            resetButtons(showA, showP, showD)
                            onConfirmation()
                        }) { Text(stringResource(R.string.confirmare)) }

                        Spacer(Modifier.fillMaxWidth(fraction = 100F / LocalConfiguration.current.screenHeightDp))

                        Button(onClick = {
                            resetButtons(showA, showP, showD)
                            onDismissRequest()
                        }) { Text(stringResource(R.string.renuntare)) }
                    }
                    Spacer(Modifier.fillMaxHeight(0.075f))
                } else {
                    Spacer(modifier = Modifier.fillMaxHeight(fraction = 20F / LocalConfiguration.current.screenHeightDp))

                    WarningNotSelectedCategory()

                    Spacer(modifier = Modifier.fillMaxHeight(fraction = 40F / LocalConfiguration.current.screenHeightDp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(onClick = {
                            resetButtons(showA, showP, showD)
                            onDismissRequest()
                        }, modifier = Modifier.weight(1f)) { Text(stringResource(R.string.renuntare)) }
                    }
                }
            }
        }
    }
}