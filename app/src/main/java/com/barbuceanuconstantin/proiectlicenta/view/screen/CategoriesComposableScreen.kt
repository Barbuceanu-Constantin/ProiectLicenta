package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.allSubcategoriesOrTransactions
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.selectCategoryItemList
import com.barbuceanuconstantin.proiectlicenta.data.model.subcategorysLazyColumn
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showCategoryDialog

@Composable
private fun showAddSubcategoryDialog(
    lSA: MutableList<Subcategory>,
    lSP: MutableList<Subcategory>,
    lSD: MutableList<Subcategory>,
    addButton: MutableState<Boolean>,
    deleteButton: MutableState<Boolean>,
    onDismissRequest: () -> Unit = { addButton.value = false },
    onConfirmation: () -> Unit = { addButton.value = false },
) {
    showCategoryDialog(
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        strId = R.string.mesaj_adaugare_subcategory,
        lActive = lSA,
        lPasive = lSP,
        lDatorii = lSD
    )
}

@Composable
fun categoriesComposableScreen(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>,
                     addButton: MutableState<Boolean>, deleteButton: MutableState<Boolean>,
                     lSA: MutableList<Subcategory>, lSP: MutableList<Subcategory>, lSD: MutableList<Subcategory>) {
    if (addButton.value) {
        showAddSubcategoryDialog(lSA = lSA, lSP = lSP, lSD = lSD, addButton = addButton, deleteButton = deleteButton)
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
                Spacer(Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp))
                Row() {
                    selectCategoryItemList(showA = showA, showP = showP, showD = showD)
                }

                allSubcategoriesOrTransactions(id = R.string.toate_subcategoriile, showA = showA, showP = showP, showD = showD)

                if (showA.value && !showP.value && !showD.value) {
                    subcategorysLazyColumn(categorii = lSA)
                } else if (showP.value && !showA.value && !showD.value) {
                    subcategorysLazyColumn(categorii = lSP)
                } else if (showD.value && !showA.value && !showP.value) {
                    subcategorysLazyColumn(categorii = lSD)
                } else if (showA.value && showP.value && showD.value) {
                    subcategorysLazyColumn(categorii = (lSA + lSP + lSD).toMutableList(), 0, lSA.size, lSA.size + lSP.size)
                }
            }
        }
    }
}

