package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.fourthButton
import com.barbuceanuconstantin.proiectlicenta.threeTopButtons
import com.barbuceanuconstantin.proiectlicenta.data.model.tranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun transactionsComposableScreen(showA: MutableState<Boolean>,
                                 showP: MutableState<Boolean>,
                                 showD: MutableState<Boolean>,
                                 addButton: MutableState<Boolean>,
                                 lTrA: SnapshotStateList<Tranzactie>,
                                 lTrP: SnapshotStateList<Tranzactie>,
                                 lTrD: SnapshotStateList<Tranzactie>,
                                 index: MutableState<Int>,
                                 sem: MutableState<Int>,
                                 updateTransactionButton: MutableState<Boolean>) {
    if (index.value != -1 && updateTransactionButton.value) {
        val dateMutable: MutableState<String> = mutableStateOf("")
        if (sem.value == 1) {
            dateMutable.value = lTrA[index.value].data
            transactionUpdateScreen(
                indexUpdate = index.value,
                trList = lTrA,
                subcategoriesList = listaSubcategorysActive,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
        if (sem.value == 2) {
            dateMutable.value = lTrP[index.value].data
            transactionUpdateScreen(
                indexUpdate = index.value,
                trList = lTrP,
                subcategoriesList = listaSubcategorysPasive,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
        if (sem.value == 3) {
            dateMutable.value = lTrD[index.value].data
            transactionUpdateScreen(
                indexUpdate = index.value,
                trList = lTrD,
                subcategoriesList = listaSubcategorysDatorii,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
    } else {
        if (addButton.value) {
            val dateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = dateTime.format(dateFormatter)
            val date by remember { mutableStateOf(formattedDate) }
            val dateMutable: MutableState<String> = mutableStateOf(date)
            showTransactionDialog(onDismissRequest = { addButton.value = false },
                                  onConfirmation = {addButton.value = false},
                                  lActive = lTrA, lPasive = lTrP, lDatorii = lTrD,
                                  dateMutable = dateMutable)
        }
        if (!addButton.value) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { addButton.value = !addButton.value }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            ) { innerPadding ->
                Column(modifier = Modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally,) {
                    Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))

                    Row() {
                        threeTopButtons(first = showA, second = showP, third = showD, firstId = R.string.active, secondId = R.string.pasive, thirdId = R.string.datorii)
                    }

                    fourthButton(id = R.string.toate_tranzactiile, first = showA, second = showP, third = showD)

                    if (showA.value && !showP.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTrA, lTrA = lTrA, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 1
                    } else if (showP.value && !showA.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTrP, lTrP = lTrP, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 2
                    } else if (showD.value && !showA.value && !showP.value) {
                        tranzactiiLazyColumn(tranzactii = lTrD, lTrD = lTrD, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 3
                    } else if (showA.value && showP.value && showD.value) {
                        tranzactiiLazyColumn(tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(),
                                             lTrA, lTrP, lTrD, indexState = index, sem, updateTransactionButton
                        )
                    }
                }
            }
        }
    }
}

