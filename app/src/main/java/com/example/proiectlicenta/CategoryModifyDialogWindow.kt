package com.example.proiectlicenta

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
import com.example.room5_documentatie.R

class CategoryModifyDialogWindow {
    private val showA = mutableStateOf(true)
    private val showP = mutableStateOf(true)
    private val showD = mutableStateOf(true)

    private fun adaugareSubcategorie(l: MutableList<Subcategorie>, firstLetter:String, filledText:String) {
        val foundSubcategory = l.find{it.name == firstLetter}
        if (foundSubcategory != null) {
            foundSubcategory.items.add(filledText)
        } else {
            val insertionIndex = l.binarySearch { it.name.compareTo(firstLetter) }
            val newSubcategorie = Subcategorie(name = firstLetter, items = mutableListOf(filledText))
            if (insertionIndex < 0) {
                l.add(-insertionIndex - 1, newSubcategorie)
            } else {
                l.add(insertionIndex, newSubcategorie)
            }
        }
    }

    private fun eliminareSubcategorie(l: MutableList<Subcategorie>, firstLetter:String, filledText:String) {
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
        lActive: MutableList<Subcategorie>,
        lPasive: MutableList<Subcategorie>,
        lDatorii: MutableList<Subcategorie>
    ) {
        val specificMessage = stringResource(id = strId)
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier.fillMaxWidth().height(650.dp).padding(10.dp),
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
                            label = { Text(text = "Denumire") },
                            placeholder = { Text(text = "_____") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.ModeEdit,
                                    contentDescription = "Add"
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Category,
                                    contentDescription = "Add"
                                )
                            },
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 50.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            val words = specificMessage.trim().split("\\s+".toRegex())
                            val lastWord = words.last()
                            Button(onClick = {
                                val firstLetter = filledText[0].toString().uppercase()
                                if (lastWord == "adaugati") {
                                    if (showA.value && !showP.value && !showD.value) {
                                        adaugareSubcategorie(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        adaugareSubcategorie(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        adaugareSubcategorie(lDatorii, firstLetter, filledText)
                                    }
                                } else if (lastWord == "eliminati") {
                                    if (showA.value && !showP.value && !showD.value) {
                                        eliminareSubcategorie(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        eliminareSubcategorie(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        eliminareSubcategorie(lDatorii, firstLetter, filledText)
                                    }
                                }
                                showA.value = true
                                showP.value = true
                                showD.value = true
                                onConfirmation()
                            }) { Text(stringResource(R.string.confirmare)) }
                            Button(onClick = {
                                showA.value = true
                                showP.value = true
                                showD.value = true
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