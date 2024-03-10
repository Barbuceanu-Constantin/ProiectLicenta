package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.TransactionModifyDialogWindow
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MenuScreensSwipeableTabRows
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
import com.barbuceanuconstantin.proiectlicenta.addOrDeleteItem
import com.barbuceanuconstantin.proiectlicenta.lTrA
import com.barbuceanuconstantin.proiectlicenta.lTrD
import com.barbuceanuconstantin.proiectlicenta.lTrP
import com.barbuceanuconstantin.proiectlicenta.selectCategoryItemList
import com.barbuceanuconstantin.proiectlicenta.data.model.tranzactiiLazyColumn

data class TransactionsScreenComposable(val ctx: Context) {
    private val fereastraDialog = TransactionModifyDialogWindow()
    private val menuScreensButton = MenuScreensSwipeableTabRows()

    private var showA = mutableStateOf(true)
    private var showP = mutableStateOf(true)
    private var showD = mutableStateOf(true)
    private var addButton = mutableStateOf(false)
    private var deleteButton = mutableStateOf(false)

    private var lTranzactiiActive: MutableList<Tranzactie> = lTrA
    private var lTranzactiiPasive: MutableList<Tranzactie> = lTrP
    private var lTranzactiiDatorii: MutableList<Tranzactie> = lTrD

    private val dismissAddButton: () -> Unit = { addButton.value = false }
    private val confirmationAddButton: () -> Unit = { addButton.value = false }
    private val dismissDeleteButton: () -> Unit = { deleteButton.value = false }
    private val confirmationDeleteButton: () -> Unit = { deleteButton.value = false }
    @Composable
    private fun showAddTransactionDialog(
        onDismissRequest: () -> Unit = dismissAddButton,
        onConfirmation: () -> Unit = confirmationAddButton,
    ) {
        fereastraDialog.showDialog(
            onDismissRequest = onDismissRequest,
            onConfirmation = onConfirmation,
            addDialog = true,
            deleteDialog = false,
            lActive = lTranzactiiActive,
            lPasive = lTranzactiiPasive,
            lDatorii = lTranzactiiDatorii
        )
    }

    @Composable
    private fun showDeleteTransactionDialog(
        onDismissRequest: () -> Unit = dismissDeleteButton,
        onConfirmation: () -> Unit = confirmationDeleteButton,
    ) {
        fereastraDialog.showDialog(
            onDismissRequest = onDismissRequest,
            onConfirmation = onConfirmation,
            addDialog = false,
            deleteDialog = true,
            lActive = lTranzactiiActive,
            lPasive = lTranzactiiPasive,
            lDatorii = lTranzactiiDatorii
        )
    }
    @Composable
    fun transactionsLayout(modifier: Modifier = Modifier) {
        menuScreensButton.showMenu()
        if (addButton.value) { showAddTransactionDialog() }
        if (deleteButton.value) { showDeleteTransactionDialog() }
        if (!addButton.value && !deleteButton.value) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(100F / LocalConfiguration.current.screenHeightDp ))
                Row() {
                    selectCategoryItemList(showA = showA, showP = showP, showD = showD)
                    if (showA.value && !showP.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTranzactiiActive)
                    } else if (showP.value && !showA.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTranzactiiPasive)
                    } else if (showD.value && !showA.value && !showP.value) {
                        tranzactiiLazyColumn(tranzactii = lTranzactiiDatorii)
                    }
                }
                Spacer(modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp))
                Row(horizontalArrangement = Arrangement.Center) {
                    addOrDeleteItem(addButton = addButton, deleteButton = deleteButton)
                }
            }
        }
    }
}
