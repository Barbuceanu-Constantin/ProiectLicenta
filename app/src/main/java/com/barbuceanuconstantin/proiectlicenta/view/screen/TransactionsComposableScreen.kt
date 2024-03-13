package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.addOrDeleteItem
import com.barbuceanuconstantin.proiectlicenta.allSubcategoriesOrTransactions
import com.barbuceanuconstantin.proiectlicenta.data.model.subcategorysLazyColumn
import com.barbuceanuconstantin.proiectlicenta.selectCategoryItemList
import com.barbuceanuconstantin.proiectlicenta.data.model.tranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showTransactionDialog

@Composable
private fun showAddTransactionDialog(
    lTrA: MutableList<Tranzactie>,
    lTrP: MutableList<Tranzactie>,
    lTrD: MutableList<Tranzactie>,
    addButton: MutableState<Boolean>,
    deleteButton: MutableState<Boolean>,
    onDismissRequest: () -> Unit = { addButton.value = false },
    onConfirmation: () -> Unit = { addButton.value = false },
) {
    showTransactionDialog(
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        addDialog = true,
        deleteDialog = false,
        lActive = lTrA,
        lPasive = lTrP,
        lDatorii = lTrD
    )
}
@Composable
private fun showDeleteTransactionDialog(
    lTrA: MutableList<Tranzactie>,
    lTrP: MutableList<Tranzactie>,
    lTrD: MutableList<Tranzactie>,
    addButton: MutableState<Boolean>,
    deleteButton: MutableState<Boolean>,
    onDismissRequest: () -> Unit = { deleteButton.value = false },
    onConfirmation: () -> Unit = { deleteButton.value = false },
) {
    showTransactionDialog(
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        addDialog = false,
        deleteDialog = true,
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
                       deleteButton: MutableState<Boolean>,
                       lTrA: MutableList<Tranzactie>,
                       lTrP: MutableList<Tranzactie>,
                       lTrD: MutableList<Tranzactie>) {
    if (addButton.value) {
        showAddTransactionDialog(lTrA = lTrA, lTrP = lTrP, lTrD = lTrD, addButton = addButton, deleteButton = deleteButton)
    }
    if (!addButton.value && !deleteButton.value) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { addButton.value = !addButton.value }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))
                Row() {
                    selectCategoryItemList(showA = showA, showP = showP, showD = showD)
                }

                allSubcategoriesOrTransactions(id = R.string.toate_tranzactiile, showA = showA, showP = showP, showD = showD)

                if (showA.value && !showP.value && !showD.value) {
                    tranzactiiLazyColumn(tranzactii = lTrA)
                } else if (showP.value && !showA.value && !showD.value) {
                    tranzactiiLazyColumn(tranzactii = lTrP)
                } else if (showD.value && !showA.value && !showP.value) {
                    tranzactiiLazyColumn(tranzactii = lTrD)
                } else if (showA.value && showP.value && showD.value) {
                    tranzactiiLazyColumn(tranzactii = (lTrA + lTrP + lTrD).toMutableList(), 0, lTrA.size, lTrA.size + lTrP.size)
                }
            }
        }
    }
}

