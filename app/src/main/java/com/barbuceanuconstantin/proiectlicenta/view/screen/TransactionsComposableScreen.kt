package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.barbuceanuconstantin.proiectlicenta.FourthButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.ThreeTopButtons
import com.barbuceanuconstantin.proiectlicenta.data.model.Tranzactie
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
fun TransactionsComposableScreen(
    lTrA: SnapshotStateList<Tranzactie>,
    lTrP: SnapshotStateList<Tranzactie>,
    lTrD: SnapshotStateList<Tranzactie>,
) {
    val showA by remember { mutableStateOf(true) }
    val showP by remember { mutableStateOf(true) }
    val showD by remember { mutableStateOf(true) }
    var addButton by remember { mutableStateOf(false) }
    val updateTransactionButton by remember { mutableStateOf(false) }
    val index by remember { mutableIntStateOf(-1) }
    var sem by remember { mutableIntStateOf(-1) }
    if (index != -1 && updateTransactionButton) {
        val dateMutable: MutableState<String> = mutableStateOf("")
        if (sem == 1) {
            dateMutable.value = lTrA[index].data
            TransactionUpdateScreen(
                indexUpdate = index,
                trList = lTrA,
                subcategoriesList = listaSubcategorysActive,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
        if (sem == 2) {
            dateMutable.value = lTrP[index].data
            TransactionUpdateScreen(
                indexUpdate = index,
                trList = lTrP,
                subcategoriesList = listaSubcategorysPasive,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
        if (sem == 3) {
            dateMutable.value = lTrD[index].data
            TransactionUpdateScreen(
                indexUpdate = index,
                trList = lTrD,
                subcategoriesList = listaSubcategorysDatorii,
                updateTransactionButton = updateTransactionButton,
                dateMutable = dateMutable
            )
        }
    } else {
        if (addButton) {
            val dateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = dateTime.format(dateFormatter)
            val date by remember { mutableStateOf(formattedDate) }
            val dateMutable: MutableState<String> = mutableStateOf(date)
            ShowTransactionDialog(onDismissRequest = {addButton = false},
                                  onConfirmation = {addButton = false},
                                  lActive = lTrA, lPasive = lTrP, lDatorii = lTrD,
                                  dateMutable = dateMutable)
        }
        if (!addButton) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { addButton = !addButton }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            ) { innerPadding ->
                Column(modifier = Modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally,) {
                    Spacer(modifier = Modifier.fillMaxHeight(50F / LocalConfiguration.current.screenHeightDp))

                    Row() {
                        ThreeTopButtons(first = showA, second = showP, third = showD, firstId = R.string.active, secondId = R.string.pasive, thirdId = R.string.datorii)
                    }

                    FourthButton(id = R.string.toate_tranzactiile, first = showA, second = showP, third = showD)

                    if (showA && !showP && !showD) {
                        TranzactiiLazyColumn(tranzactii = lTrA, lTrA = lTrA, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem = 1
                    } else if (showP && !showA && !showD) {
                        TranzactiiLazyColumn(tranzactii = lTrP, lTrP = lTrP, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem = 2
                    } else if (showD && !showA && !showP) {
                        TranzactiiLazyColumn(tranzactii = lTrD, lTrD = lTrD, indexState = index, sem = sem, updateScreenButton = updateTransactionButton)
                        sem = 3
                    } else if (showA && showP && showD) {
                        TranzactiiLazyColumn(tranzactii = (lTrA + lTrP + lTrD).toMutableStateList(), lTrA, lTrP, lTrD, indexState = index, sem, updateTransactionButton)
                    }
                }
            }
        }
    }
}

