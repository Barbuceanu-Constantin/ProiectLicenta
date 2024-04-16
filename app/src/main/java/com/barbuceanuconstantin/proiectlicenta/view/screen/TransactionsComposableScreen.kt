package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.SegmentedButton4
import com.barbuceanuconstantin.proiectlicenta.data.model.Transaction
import com.barbuceanuconstantin.proiectlicenta.data.model.TranzactiiLazyColumn
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var listaSubcategorysActive = subcategorysPredefiniteActive.values.flatten().toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.values.flatten().toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.values.flatten().toMutableList()

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionsComposableScreen(lTrA: SnapshotStateList<Transaction>,
                                 lTrP: SnapshotStateList<Transaction>,
                                 lTrD: SnapshotStateList<Transaction>) {
    val showA: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showP: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showD: MutableState<Boolean> = remember { mutableStateOf(false) }
    val addButton: MutableState<Boolean> = remember { mutableStateOf(false) }
    val updateTransactionButton: MutableState<Boolean> = remember { mutableStateOf(false) }
    val index: MutableState<Int> = remember { mutableIntStateOf(-1) }
    val sem: MutableState<Int> = remember { mutableIntStateOf(-1) }

    if (index.value != -1 && updateTransactionButton.value) {
        val dateMutable: MutableState<String> = remember { mutableStateOf("") }
        if (sem.value == 1) {
            dateMutable.value = lTrA[index.value].data
//            TransactionUpdateScreen(
//                indexUpdate = index.value,
//                trList = lTrA,
//                subcategoriesList = listaSubcategorysActive,
//                updateTransactionButton = updateTransactionButton,
//                dateMutable = dateMutable
//            )
        }
        if (sem.value == 2) {
            dateMutable.value = lTrP[index.value].data
//            TransactionUpdateScreen(
//                indexUpdate = index.value,
//                trList = lTrP,
//                subcategoriesList = listaSubcategorysPasive,
//                updateTransactionButton = updateTransactionButton,
//                dateMutable = dateMutable
//            )
        }
        if (sem.value == 3) {
            dateMutable.value = lTrD[index.value].data
//            TransactionUpdateScreen(
//                indexUpdate = index.value,
//                trList = lTrD,
//                subcategoriesList = listaSubcategorysDatorii,
//                updateTransactionButton = updateTransactionButton,
//                dateMutable = dateMutable
//            )
        }
    } else {
        if (addButton.value) {
            val dateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = dateTime.format(dateFormatter)
            val date by remember { mutableStateOf(formattedDate) }
            val dateMutable: MutableState<String> = remember { mutableStateOf(date) }
            val showAB: MutableState<Boolean> = remember { mutableStateOf(true) }
            val showPB: MutableState<Boolean> = remember { mutableStateOf(true) }
            val showDB: MutableState<Boolean> = remember { mutableStateOf(true) }
            /*ShowTransactionDialog(onDismissRequest = { addButton.value = false },
                                  onConfirmation = {addButton.value = false},
                                  lActive = lTrA, lPasive = lTrP, lDatorii = lTrD,
                                  dateMutable = dateMutable, showAB = showAB,
                                  showPB = showPB, showDB = showDB)*/
        }
        if (!addButton.value) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButtonCustom(addButton = addButton)
                }
            ) { innerPadding ->
                Column( modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.half_hundred)))

                    SegmentedButton4(first = showA, second = showP, third = showD)

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.half_hundred)))

                    if (showA.value && !showP.value && !showD.value) {
                        TranzactiiLazyColumn(tranzactii = lTrA, lTrA = lTrA, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 1
                    } else if (showP.value && !showA.value && !showD.value) {
                        TranzactiiLazyColumn(tranzactii = lTrP, lTrP = lTrP, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 2
                    } else if (showD.value && !showA.value && !showP.value) {
                        TranzactiiLazyColumn(tranzactii = lTrD, lTrD = lTrD, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem.value = 3
                    } else if (showA.value && showP.value && showD.value) {
                        TranzactiiLazyColumn(tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(),
                                             lTrA, lTrP, lTrD, indexState = index, sem, updateTransactionButton)
                    }
                }
            }
        }
    }
}

