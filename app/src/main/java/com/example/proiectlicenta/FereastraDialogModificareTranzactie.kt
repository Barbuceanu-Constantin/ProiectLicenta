package com.example.proiectlicenta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.room5_documentatie.R
import kotlin.reflect.KMutableProperty0

class FereastraDialogModificareTranzactie {
    private val showA = mutableStateOf(true)
    private val showP = mutableStateOf(true)
    private val showD = mutableStateOf(true)
    private val showMeniuValute = mutableStateOf(false)
    private val showMeniuSubcategorii = mutableStateOf(false)

    private val meniuValute = MeniuValute()
    private val meniuSubcategorii = MeniuSubcategorii()

    private var listaSubcategoriiActive = subcategoriiPredefiniteActive.values.flatten().toMutableList()
    private var listaSubcategoriiPasive = subcategoriiPredefinitePasive.values.flatten().toMutableList()
    private var listaSubcategoriiDatorii = subcategoriiPredefiniteDatorii.values.flatten().toMutableList()
    private fun adaugareTranzactie(l: MutableList<Tranzactie>,
                                   currency:String, subcategorie:String,
                                   valueSum:String, payee:String, date:String,
                                   description:String) {
        val newTranzactie = Tranzactie(valueSum.toDouble(), currency, description, subcategorie, date, payee)
        l.add(0, newTranzactie)
    }

    @Composable
    private fun avertisment() {
        Text(
            text = "Nu ati selectat categoria principala",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }

    @Composable
    fun showDialog(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        addDialog: Boolean,
        deleteDialog: Boolean,
        lActive: MutableList<Tranzactie>,
        lPasive: MutableList<Tranzactie>,
        lDatorii: MutableList<Tranzactie>
    ) {
        var currency by remember { mutableStateOf("") }
        var subcategorie by remember { mutableStateOf("") }
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier.fillMaxWidth().height(750.dp).padding(10.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.mesaj_selectare_categorie_principala),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if (showA.value) {
                            Button(onClick = {
                                if (showA.value && showP.value && showD.value) {
                                    showP.value = false
                                    showD.value = false
                                } else if (showA.value && !showP.value && !showD.value) {
                                    showP.value = true
                                    showD.value = true
                                    showMeniuValute.value = false
                                    showMeniuSubcategorii.value = false
                                }
                            }) { Text(stringResource(R.string.prescurtareActive)) }
                        }
                        if (showP.value) {
                            Button(onClick = {
                                if (showP.value && showA.value && showD.value) {
                                    showA.value = false
                                    showD.value = false
                                } else if (showP.value && !showA.value && !showD.value) {
                                    showA.value = true
                                    showD.value = true
                                    showMeniuValute.value = false
                                    showMeniuSubcategorii.value = false
                                }
                            }) { Text(stringResource(R.string.prescurtarePasive)) }
                        }
                        if (showD.value) {
                            Button(onClick = {
                                if (showD.value && showA.value && showP.value) {
                                    showA.value = false
                                    showP.value = false
                                } else if (showD.value && !showA.value && !showP.value) {
                                    showA.value = true
                                    showP.value = true
                                    showMeniuValute.value = false
                                    showMeniuSubcategorii.value = false
                                }
                            }) { Text(stringResource(R.string.prescurtareDatorii)) }
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(color = Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                if (!(showA.value && showP.value && showD.value) && !showMeniuValute.value) {
                                    showMeniuSubcategorii.value = !showMeniuSubcategorii.value
                                }
                            }
                        ) { Text(stringResource(R.string.mesaj_selectare_subcategorie)) }
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(color = Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                if (!(showA.value && showP.value && showD.value) && !showMeniuSubcategorii.value) {
                                    showMeniuValute.value = !showMeniuValute.value
                                }
                            }
                        ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
                    }

                    if (!showMeniuValute.value && !showMeniuSubcategorii.value) {
                        if (!(showA.value && showP.value && showD.value)) {
                            Text(
                                text = "Subcategorie : $subcategorie",
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Valuta : $currency",
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            var valueSum by remember { mutableStateOf("") }
                            if (addDialog) {
                                TextField(
                                    value = valueSum,
                                    onValueChange = { valueSum = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    label = { Text("Introduceti suma") },
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }

                            var payee by remember { mutableStateOf("") }
                            TextField(
                                value = payee,
                                onValueChange = { payee = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text("Furnizor/Beneficiar") },
                                maxLines = 2,
                                modifier = Modifier.padding(10.dp)
                            )

                            var date by remember { mutableStateOf("") }
                            var description by remember { mutableStateOf("") }
                            if (addDialog) {
                                TextField(
                                    value = date,
                                    onValueChange = { date = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    label = { Text("Data") },
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )

                                TextField(
                                    value = description,
                                    onValueChange = { description = it },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    label = { Text("Descriere") },
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(onClick = {
                                    if (addDialog) {
                                        if (currency != "" && subcategorie != "" && valueSum != "" && payee != "" && date != "" && description != "") {
                                            if (showA.value && !showP.value && !showD.value) {
                                                adaugareTranzactie(
                                                    lActive,
                                                    currency,
                                                    subcategorie,
                                                    valueSum,
                                                    "Furnizor : $payee",
                                                    date,
                                                    description
                                                )
                                            } else if (showP.value && !showA.value && !showD.value) {
                                                adaugareTranzactie(
                                                    lPasive,
                                                    currency,
                                                    subcategorie,
                                                    valueSum,
                                                    "Beneficiar : $payee",
                                                    date,
                                                    description
                                                )
                                            } else if (showD.value && !showA.value && !showP.value) {
                                                adaugareTranzactie(
                                                    lDatorii,
                                                    currency,
                                                    subcategorie,
                                                    valueSum,
                                                    payee,
                                                    date,
                                                    description
                                                )
                                            }
                                        }
                                    } else {
                                        if (showA.value && !showP.value && !showD.value) {
                                            //eliminareSubcategorie(lActive, firstLetter, filledText)
                                        } else if (showP.value && !showA.value && !showD.value) {
                                            //eliminareSubcategorie(lPasive, firstLetter, filledText)
                                        } else if (showD.value && !showA.value && !showP.value) {
                                            //eliminareSubcategorie(lDatorii, firstLetter, filledText)
                                        }
                                    }
                                    showA.value = true
                                    showP.value = true
                                    showD.value = true
                                    onConfirmation()
                                }) { Text(stringResource(R.string.confirmare)) }

                                Spacer(modifier = Modifier.width(30.dp))

                                Button(onClick = {
                                    showA.value = true
                                    showP.value = true
                                    showD.value = true
                                    onDismissRequest()
                                }) { Text(stringResource(R.string.renuntare)) }
                            }
                        } else { avertisment() }
                    } else {
                        if (showA.value && showP.value && showD.value) {
                            avertisment()
                        } else {
                            if (showMeniuValute.value && !showMeniuSubcategorii.value) {
                                meniuValute.showMenu(currency) {
                                    currency = it
                                }
                            } else if (!showMeniuValute.value && showMeniuSubcategorii.value) {
                                if (showA.value && !showP.value && !showD.value) {
                                    meniuSubcategorii.showMenu(
                                        subcategorie,
                                        lSubcategorii = listaSubcategoriiActive,
                                    ) {
                                        subcategorie = it
                                    }
                                } else if (showP.value && !showA.value && !showD.value) {
                                    meniuSubcategorii.showMenu(
                                        subcategorie,
                                        lSubcategorii = listaSubcategoriiPasive,
                                    ) {
                                        subcategorie = it
                                    }
                                } else if (showD.value && !showA.value && !showP.value) {
                                    meniuSubcategorii.showMenu(
                                        subcategorie,
                                        lSubcategorii = listaSubcategoriiDatorii,
                                    ) {
                                        subcategorie = it
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}