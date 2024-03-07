package com.example.proiectlicenta

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.room5_documentatie.R

data class CategoriesScreenComposable(val ctx: Context) {
    private val fereastraDialog = FereastraDialogModificareCategorii()
    private val menuScreensButton = MenuScreensSwipeableTabRows()

    private var showA = mutableStateOf(true)
    private var showP = mutableStateOf(true)
    private var showD = mutableStateOf(true)
    private var addButton = mutableStateOf(false)
    private var deleteButton = mutableStateOf(false)

    private var listaSubcategoriiActive= subcategoriiPredefiniteActive.map {
        Subcategorie(name = it.key.toString(), items = it.value)
    }.toMutableList()

    private var listaSubcategoriiPasive = subcategoriiPredefinitePasive.map {
        Subcategorie(name = it.key.toString(), items = it.value)
    }.toMutableList()

    private var listaSubcategoriiDatorii = subcategoriiPredefiniteDatorii.map {
        Subcategorie(name = it.key.toString(), items = it.value)
    }.toMutableList()

    private val dismissAddButton: () -> Unit = { addButton.value = false }
    private val confirmationAddButton: () -> Unit = { addButton.value = false }
    private val dismissDeleteButton: () -> Unit = { deleteButton.value = false }
    private val confirmationDeleteButton: () -> Unit = { deleteButton.value = false }

    @Composable
    private fun showAddSubcategoryDialog(
        onDismissRequest: () -> Unit = dismissAddButton,
        onConfirmation: () -> Unit = confirmationAddButton,
    ) {
        fereastraDialog.showDialog(
            onDismissRequest = onDismissRequest,
            onConfirmation = onConfirmation,
            strId = R.string.mesaj_adaugare_subcategorie,
            lActive = listaSubcategoriiActive,
            lPasive = listaSubcategoriiPasive,
            lDatorii = listaSubcategoriiDatorii
        )
    }

    @Composable
    private fun showDeleteSubcategoryDialog(
        onDismissRequest: () -> Unit = dismissDeleteButton,
        onConfirmation: () -> Unit = confirmationDeleteButton,
    ) {
        fereastraDialog.showDialog(
            onDismissRequest = onDismissRequest,
            onConfirmation = onConfirmation,
            strId = R.string.mesaj_eliminare_subcategorie,
            lActive = listaSubcategoriiActive,
            lPasive = listaSubcategoriiPasive,
            lDatorii = listaSubcategoriiDatorii
        )
    }

    @Composable
    fun categoriesLayout(modifier: Modifier = Modifier) {
        menuScreensButton.showMenu()
        if (addButton.value) { showAddSubcategoryDialog() }
        if (deleteButton.value) { showDeleteSubcategoryDialog() }
        if (!addButton.value && !deleteButton.value) {
            Column(
                modifier = modifier.fillMaxWidth().padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(35.dp)
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
                        }, modifier = modifier.height(75.dp)) { Text(stringResource(R.string.active)) }
                        if (showA.value && !showP.value && !showD.value) {
                            subcategoriiLazyColumn(categorii = listaSubcategoriiActive)
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
                        ) { Text(stringResource(R.string.pasive)) }
                        if (showP.value && !showA.value && !showD.value) {
                            subcategoriiLazyColumn(categorii = listaSubcategoriiPasive)
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
                        ) { Text(stringResource(R.string.datorii)) }
                        if (showD.value && !showA.value && !showP.value) {
                            subcategoriiLazyColumn(categorii = listaSubcategoriiDatorii)
                        }
                    }
                }
                Row(
                    modifier = modifier.padding(top = 100.dp),
                    horizontalArrangement = Arrangement.spacedBy(80.dp)
                ) {
                    Button(
                        onClick = {
                            if (!addButton.value) { addButton.value = true }
                        },
                        shape = CircleShape,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Button(
                        onClick = {
                            if (!deleteButton.value) { deleteButton.value = true }
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
