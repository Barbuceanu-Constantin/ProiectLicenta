package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.FourthButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton4
import com.barbuceanuconstantin.proiectlicenta.ThreeTopButtons
import com.barbuceanuconstantin.proiectlicenta.data.model.Subcategory
import com.barbuceanuconstantin.proiectlicenta.data.model.SubcategorysLazyColumn

@Composable
fun CategoriesComposableScreen(lSA: MutableList<Subcategory>, lSP: MutableList<Subcategory>,
                               lSD: MutableList<Subcategory>) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }
    val addButton: MutableState<Boolean> = remember { mutableStateOf(false) }

    if (addButton.value) {
        ShowAddSubcategoryScreen(R.string.mesaj_adaugare_subcategory, lSA, lSP, lSD, addButton)
    } else {
        Scaffold(
            floatingActionButton = {
                FloatingActionButtonCustom(addButton = addButton)
            }
        ) { innerPadding ->
            Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer (Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                SegmentedButton4(first = showA, second = showP, third = showD)

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.half_hundred)))

                if (showA.value && !showP.value && !showD.value) {
                    SubcategorysLazyColumn(categorii = lSA, a = true, p = false, d = false)
                } else if (showP.value && !showA.value && !showD.value) {
                    SubcategorysLazyColumn(categorii = lSP, a = false, p = true, d = false)
                } else if (showD.value && !showA.value && !showP.value) {
                    SubcategorysLazyColumn(categorii = lSD, a = false, p = false, d = true)
                } else if (showA.value && showP.value && showD.value) {
                    SubcategorysLazyColumn(categorii = (lSA + lSP + lSD).toMutableList(), a = true, p = true, d = true)
                }
            }
        }
    }
}