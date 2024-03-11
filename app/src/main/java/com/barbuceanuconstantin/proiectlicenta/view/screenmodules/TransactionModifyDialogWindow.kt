package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.headerSelectCategoryOrTransactionWindow
import com.barbuceanuconstantin.proiectlicenta.resetButtons
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import com.barbuceanuconstantin.proiectlicenta.warningNotSelectedCategory

private val showA = mutableStateOf(true)
private val showP = mutableStateOf(true)
private val showD = mutableStateOf(true)
private val showMeniuValute = mutableStateOf(false)
private val showMeniuSubcategorys = mutableStateOf(false)

private val meniuValute = MeniuValute()
private val meniuSubcategorys = MeniuSubcategorys()

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()
private fun adaugareTranzactie(l: MutableList<Tranzactie>,
                               currency:String, subcategory:String,
                               valueSum:String, payee:String, date:String,
                               description:String) {
    val newTranzactie = Tranzactie(valueSum.toDouble(), currency, description, subcategory, date, payee)
    l.add(0, newTranzactie)
}

@Composable
fun showTransactionDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    addDialog: Boolean,
    deleteDialog: Boolean,
    lActive: MutableList<Tranzactie>,
    lPasive: MutableList<Tranzactie>,
    lDatorii: MutableList<Tranzactie>
) {
    var currency by remember { mutableStateOf("") }
    var subcategory by remember { mutableStateOf("") }
    Dialog(onDismissRequest = {
        resetButtons(showA, showP, showD)
        onDismissRequest()
    }) {
        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(750f / LocalConfiguration.current.screenHeightDp).padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                headerSelectCategoryOrTransactionWindow(showA, showP, showD)
                Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
                Box(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (!(showA.value && showP.value && showD.value) && !showMeniuValute.value && !showMeniuSubcategorys.value) {
                                showMeniuSubcategorys.value = !showMeniuSubcategorys.value
                            }
                        }
                    ) { Text(stringResource(R.string.mesaj_selectare_subcategory)) }
                }
                Box(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (!(showA.value && showP.value && showD.value) && !showMeniuSubcategorys.value && !showMeniuValute.value) {
                                showMeniuValute.value = !showMeniuValute.value
                            }
                        }
                    ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
                }

                if (!showMeniuValute.value && !showMeniuSubcategorys.value) {
                    if (!(showA.value && showP.value && showD.value)) {
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))
                        Text(
                            text = "${stringResource(id = R.string.subcategory)} : $subcategory",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))
                        Text(
                            text = "${stringResource(id = R.string.valuta)} : $currency",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.fillMaxHeight(5f / LocalConfiguration.current.screenHeightDp))

                        var valueSum by remember { mutableStateOf("") }
                        if (addDialog) {
                            TextField(
                                value = valueSum,
                                onValueChange = { valueSum = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                label = { Text(stringResource(id = R.string.introduceti_suma)) },
                                maxLines = 2,
                            )
                        }

                        var payee by remember { mutableStateOf("") }
                        TextField(
                            value = payee,
                            onValueChange = { payee = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = { Text(text = stringResource(id = R.string.furnizor_sau_beneficiar)) },
                            maxLines = 2,
                        )

                        var date by remember { mutableStateOf("") }
                        var description by remember { mutableStateOf("") }
                        if (addDialog) {
                            TextField(
                                value = date,
                                onValueChange = { date = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text(text = stringResource(id = R.string.data)) },
                                maxLines = 2,
                            )
                            TextField(
                                value = description,
                                onValueChange = { description = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = { Text(text = stringResource(id = R.string.descriere)) },
                                maxLines = 2,
                            )
                        }
                        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {
                                if (addDialog) {
                                    if (currency != "" && subcategory != "" && valueSum != "" && payee != "" && date != "" && description != "") {
                                        if (showA.value && !showP.value && !showD.value) {
                                            adaugareTranzactie(
                                                lActive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Furnizor : $payee",
                                                date,
                                                description
                                            )
                                        } else if (showP.value && !showA.value && !showD.value) {
                                            adaugareTranzactie(
                                                lPasive,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                "Beneficiar : $payee",
                                                date,
                                                description
                                            )
                                        } else if (showD.value && !showA.value && !showP.value) {
                                            adaugareTranzactie(
                                                lDatorii,
                                                currency,
                                                subcategory,
                                                valueSum,
                                                payee,
                                                date,
                                                description
                                            )
                                        }
                                    }
                                } else {
                                    if (showA.value && !showP.value && !showD.value) {
                                        //eliminareSubcategory(lActive, firstLetter, filledText)
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        //eliminareSubcategory(lPasive, firstLetter, filledText)
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        //eliminareSubcategory(lDatorii, firstLetter, filledText)
                                    }
                                }
                                resetButtons(showA, showP, showD)
                                onConfirmation()
                            }) { Text(stringResource(R.string.confirmare)) }

                            Spacer(modifier = Modifier.fillMaxWidth(30f / LocalConfiguration.current.screenWidthDp))

                            Button(onClick = {  resetButtons(showA, showP, showD)
                                                onDismissRequest()
                            }) { Text(stringResource(R.string.renuntare)) }
                        }
                    } else { warningNotSelectedCategory() }
                } else {
                    if (showA.value && showP.value && showD.value) {
                        warningNotSelectedCategory()
                    } else {
                        if (showMeniuValute.value && !showMeniuSubcategorys.value) {
                            meniuValute.showMenu(currency, showMeniuValute) {
                                currency = it
                            }
                        } else if (!showMeniuValute.value && showMeniuSubcategorys.value) {
                            if (showA.value && !showP.value && !showD.value) {
                                meniuSubcategorys.showMenu(
                                    subcategory,
                                    lSubcategorys = listaSubcategorysActive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showP.value && !showA.value && !showD.value) {
                                meniuSubcategorys.showMenu(
                                    subcategory,
                                    lSubcategorys = listaSubcategorysPasive,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            } else if (showD.value && !showA.value && !showP.value) {
                                meniuSubcategorys.showMenu(
                                    subcategory,
                                    lSubcategorys = listaSubcategorysDatorii,
                                    showMeniuSubcategorys
                                ) {
                                    subcategory = it
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}