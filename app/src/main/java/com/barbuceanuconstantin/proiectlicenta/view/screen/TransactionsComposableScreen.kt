package com.barbuceanuconstantin.proiectlicenta.view.screen

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.allSubcategoriesOrTransactions
import com.barbuceanuconstantin.proiectlicenta.selectCategoryItemList
import com.barbuceanuconstantin.proiectlicenta.data.model.tranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showTransactionDialog

@Composable
private fun showAddTransactionDialog(
    lTrA: SnapshotStateList<Tranzactie>,
    lTrP: SnapshotStateList<Tranzactie>,
    lTrD: SnapshotStateList<Tranzactie>,
    addButton: MutableState<Boolean>,
    onDismissRequest: () -> Unit = { addButton.value = false },
    onConfirmation: () -> Unit = { addButton.value = false },
) {
    showTransactionDialog(
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        lActive = lTrA,
        lPasive = lTrP,
        lDatorii = lTrD
    )
}

@Composable
fun transactionsComposableScreen(showA: MutableState<Boolean>,
                                 showP: MutableState<Boolean>,
                                 showD: MutableState<Boolean>,
                                 addButton: MutableState<Boolean>,
                                 lTrA: SnapshotStateList<Tranzactie>,
                                 lTrP: SnapshotStateList<Tranzactie>,
                                 lTrD: SnapshotStateList<Tranzactie>,
                                 index: MutableState<Int>,
                                 sem: MutableState<Int>) {
    if (index.value != -1) {
        if (sem.value == 1)
            transactionUpdateScreen(indexUpdate = index.value, trList = lTrA)
        if (sem.value == 2)
            transactionUpdateScreen(indexUpdate = index.value, trList = lTrP)
        if (sem.value == 3)
            transactionUpdateScreen(indexUpdate = index.value, trList = lTrD)
    } else {
        if (addButton.value) {
            showAddTransactionDialog(lTrA = lTrA, lTrP = lTrP, lTrD = lTrD, addButton = addButton)
        }
        if (!addButton.value) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { addButton.value = !addButton.value }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))
                    Row() {
                        selectCategoryItemList(showA = showA, showP = showP, showD = showD)
                    }

                    allSubcategoriesOrTransactions(
                        id = R.string.toate_tranzactiile,
                        showA = showA,
                        showP = showP,
                        showD = showD
                    )

                    if (showA.value && !showP.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTrA, indexState = index, sem = sem)
                        sem.value = 1
                    } else if (showP.value && !showA.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTrP, indexState = index, sem = sem)
                        sem.value = 2
                    } else if (showD.value && !showA.value && !showP.value) {
                        tranzactiiLazyColumn(tranzactii = lTrD, indexState = index, sem = sem)
                        sem.value = 3
                    } else if (showA.value && showP.value && showD.value) {
                        tranzactiiLazyColumn(
                            tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(),
                            lTrA,
                            lTrP,
                            lTrD,
                            indexState = index,
                            sem
                        )
                    }
                }
            }
        }
    }
}

