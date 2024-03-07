package com.example.proiectlicenta

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.room5_documentatie.R

data class TransactionsScreenComposable(val ctx: Context) {
    private val fereastraDialog = FereastraDialogModificareTranzactie()
    private val menuScreensButton = MenuScreensSwipeableTabRows()

    private var showA = mutableStateOf(true)
    private var showP = mutableStateOf(true)
    private var showD = mutableStateOf(true)
    private var addButton = mutableStateOf(false)
    private var deleteButton = mutableStateOf(false)

    private val dismissAddButton: () -> Unit = { addButton.value = false }
    private val confirmationAddButton: () -> Unit = { addButton.value = false }
    private val dismissDeleteButton: () -> Unit = { deleteButton.value = false }
    private val confirmationDeleteButton: () -> Unit = { deleteButton.value = false }

    private var lTranzactiiActive: MutableList<Tranzactie> = mutableListOf()
    private var lTranzactiiPasive: MutableList<Tranzactie> = mutableListOf()
    private var lTranzactiiDatorii: MutableList<Tranzactie> = mutableListOf()

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
                modifier = modifier.fillMaxWidth().padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Column(
                            verticalArrangement = Arrangement.spacedBy(80.dp),
                    ) {
                        if (showA.value) {
                            Button(onClick = {
                                if (showA.value && showP.value && showD.value) {
                                    showP.value = false
                                    showD.value = false
                                } else if (showA.value && !showP.value && !showD.value) {
                                    showP.value = true
                                    showD.value = true
                                }
                            }, modifier = modifier.height(75.dp)) {
                                Text(
                                    text = stringResource(id = R.string.active_short),
                                    fontSize = 20.sp
                                )
                            }
                        }
                        if (showP.value) {
                            Button(
                                onClick = {
                                    if (showP.value && showA.value && showD.value) {
                                        showA.value = false
                                        showD.value = false
                                    } else if (showP.value && !showA.value && !showD.value) {
                                        showA.value = true
                                        showD.value = true
                                    }
                                },
                                modifier = modifier.height(75.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.pasive_short),
                                    fontSize = 20.sp
                                )
                            }
                        }
                        if (showD.value) {
                            Button(
                                onClick = {
                                    if (showD.value && showA.value && showP.value) {
                                        showA.value = false
                                        showP.value = false
                                    } else if (showD.value && !showA.value && !showP.value) {
                                        showA.value = true
                                        showP.value = true
                                    }
                                },
                                modifier = modifier.height(75.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.datorii_short),
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    if (showA.value && !showP.value && !showD.value) {
                        tranzactiiLazyColumn(tranzactii = lTranzactiiActive)
                    }
                }
                Row(
                    modifier = modifier.padding(top = 100.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (!addButton.value) {
                                addButton.value = true
                            }
                        },
                        shape = CircleShape,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(80.dp))

                    Button(
                        onClick = {
                            if (!deleteButton.value) {
                                deleteButton.value = true
                            }
                        },
                        shape = CircleShape,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
