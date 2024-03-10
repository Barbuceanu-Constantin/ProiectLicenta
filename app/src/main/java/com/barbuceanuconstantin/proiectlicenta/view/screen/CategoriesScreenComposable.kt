package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.CategoryModifyDialogWindow
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MenuScreensSwipeableTabRows
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.addOrDeleteItem
import com.barbuceanuconstantin.proiectlicenta.selectCategoryItemList
import com.barbuceanuconstantin.proiectlicenta.data.model.subcategorysLazyColumn
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

data class CategoriesScreenComposable(val ctx: Context) {
    companion object {
        private val fereastraDialog = CategoryModifyDialogWindow()
        //private val menuScreensButton = MenuScreensSwipeableTabRows()

        private var showA = mutableStateOf(true)
        private var showP = mutableStateOf(true)
        private var showD = mutableStateOf(true)
        private var addButton = mutableStateOf(false)
        private var deleteButton = mutableStateOf(false)

        private var listaSubcategorysActive = subcategorysPredefiniteActive.map {
            Subcategory(name = it.key.toString(), items = it.value)
        }.toMutableList()
        private var listaSubcategorysPasive = subcategorysPredefinitePasive.map {
            Subcategory(name = it.key.toString(), items = it.value)
        }.toMutableList()
        private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.map {
            Subcategory(name = it.key.toString(), items = it.value)
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
                strId = R.string.mesaj_adaugare_subcategory,
                lActive = listaSubcategorysActive,
                lPasive = listaSubcategorysPasive,
                lDatorii = listaSubcategorysDatorii
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
                strId = R.string.mesaj_eliminare_subcategory,
                lActive = listaSubcategorysActive,
                lPasive = listaSubcategorysPasive,
                lDatorii = listaSubcategorysDatorii
            )
        }

        @Composable
        fun categoriesLayout(modifier: Modifier = Modifier) {
            //menuScreensButton.showMenu()
            if (addButton.value) {
                showAddSubcategoryDialog()
            }
            if (deleteButton.value) {
                showDeleteSubcategoryDialog()
            }
            if (!addButton.value && !deleteButton.value) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp))
                    Row() {
                        selectCategoryItemList(showA = showA, showP = showP, showD = showD)

                        if (showA.value && !showP.value && !showD.value) {
                            subcategorysLazyColumn(categorii = listaSubcategorysActive)
                        } else if (showP.value && !showA.value && !showD.value) {
                            subcategorysLazyColumn(categorii = listaSubcategorysPasive)
                        } else if (showD.value && !showA.value && !showP.value) {
                            subcategorysLazyColumn(categorii = listaSubcategorysDatorii)
                        }
                    }
                    Spacer(modifier.fillMaxHeight(fraction = 100F / LocalConfiguration.current.screenHeightDp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        addOrDeleteItem(addButton = addButton, deleteButton = deleteButton)
                    }
                }
            }
        }
    }
}
