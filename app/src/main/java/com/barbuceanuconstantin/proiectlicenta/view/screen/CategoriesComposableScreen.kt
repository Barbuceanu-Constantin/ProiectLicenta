package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton3
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.data.model.SubcategorysLazyColumn

@Composable
fun CategoriesComposableScreen(lSA: MutableList<Category>, lSP: MutableList<Category>,
                               lSD: MutableList<Category>) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }
    val addButton: MutableState<Boolean> = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonCustom(addButton = addButton)
        }
    ) { innerPadding ->
        Column( modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer (Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

            SegmentedButton3(first = showA, second = showP, third = showD)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.thirty_dp)))

            if (showA.value && !showP.value && !showD.value) {
                SubcategorysLazyColumn(categorii = lSA)
            } else if (showP.value && !showA.value && !showD.value) {
                SubcategorysLazyColumn(categorii = lSP)
            } else if (showD.value && !showA.value && !showP.value) {
                SubcategorysLazyColumn(categorii = lSD)
            } else if (showA.value && showP.value && showD.value) {
                SubcategorysLazyColumn(categorii = (lSA + lSP + lSD).toMutableList())
            } else if (showA.value && showP.value && !showD.value) {
                SubcategorysLazyColumn(categorii = (lSA + lSP).toMutableList())
            } else if (showA.value && !showP.value && showD.value) {
                SubcategorysLazyColumn(categorii = (lSA + lSD).toMutableList())
            } else if (!showA.value && showP.value && showD.value) {
                SubcategorysLazyColumn(categorii = (lSP + lSD).toMutableList())
            }
        }
    }
}