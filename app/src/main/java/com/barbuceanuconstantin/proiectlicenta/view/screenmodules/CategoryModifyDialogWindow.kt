package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.headerSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.warningNotSelectedCategory

class CategoryModifyDialogWindow {
    private val showA = mutableStateOf(true)
    private val showP = mutableStateOf(true)
    private val showD = mutableStateOf(true)

    private fun adaugareSubcategory(l: MutableList<Subcategory>, firstLetter:String, filledText:String) {
        val foundSubcategory = l.find{it.name == firstLetter}
        if (foundSubcategory != null) {
            foundSubcategory.items.add(filledText)
        } else {
            val insertionIndex = l.binarySearch { it.name.compareTo(firstLetter) }
            val newSubcategory = Subcategory(name = firstLetter, items = mutableListOf(filledText))
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
    fun showDialog(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        strId: Int,
        lActive: MutableList<Subcategory>,
        lPasive: MutableList<Subcategory>,
        lDatorii: MutableList<Subcategory>
    ) {
        val specificMessage = stringResource(id = strId)
        Dialog(onDismissRequest = {
            resetButtons(showA, showP, showD)
            onDismissRequest()
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column() {
                    headerSelectCategoryOrTransactionWindow(showA, showP, showD)

                    Text(
                        text = specificMessage,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    if (!(showA.value && showD.value && showP.value)) {
                        var filledText by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            value = filledText, onValueChange = { filledText = it },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right),
                            label = { Text(text = stringResource(R.string.denumire)) },
                            placeholder = { Text(text = stringResource(id = R.string.underscores)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.ModeEdit,
                                    contentDescription = stringResource(id = R.string.add)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Category,
                                    contentDescription = stringResource(id = R.string.add)
                                )
                            },
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 50.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            val words = specificMessage.trim().split("\\s+".toRegex())
                            val lastWord = words.last()
                            Button(onClick = {
                                val firstLetter = filledText[0].toString().uppercase()
                                if (lastWord == "adaugati") {
                                    if (showA.value && !showP.value && !showD.value) {
                                        adaugareSubcategory(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        adaugareSubcategory(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        adaugareSubcategory(lDatorii, firstLetter, filledText)
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
                                resetButtons(showA, showP, showD)
                                onConfirmation()
                            }) { Text(stringResource(R.string.confirmare)) }
                            Button(onClick = {
                                resetButtons(showA, showP, showD)
                                onDismissRequest()
                            }) { Text(stringResource(R.string.renuntare)) }
                        }
                    } else {
                        Spacer(modifier = Modifier.height(20.dp))
                        warningNotSelectedCategory()
                    }
                }
            }
        }
    }
}