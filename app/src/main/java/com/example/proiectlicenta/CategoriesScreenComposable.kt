package com.example.proiectlicenta

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    selectCategoryItemList(showA = showA, showP = showP, showD = showD, modifier = modifier)

                    if (showA.value && !showP.value && !showD.value) {
                        subcategoriiLazyColumn(categorii = listaSubcategoriiActive)
                    } else if (showP.value && !showA.value && !showD.value) {
                        subcategoriiLazyColumn(categorii = listaSubcategoriiPasive)
                    } else if (showD.value && !showA.value && !showP.value) {
                        subcategoriiLazyColumn(categorii = listaSubcategoriiDatorii)
                    }
                }
                Row(
                    modifier = modifier.padding(top = 100.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    addOrDeleteItem(addButton = addButton, deleteButton = deleteButton)
                }
            }
        }
    }
}
