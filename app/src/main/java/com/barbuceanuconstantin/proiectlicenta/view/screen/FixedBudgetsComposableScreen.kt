package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.BudgetsLazyColumn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ShowAddBudgetDialog(lFixedBudgets: SnapshotStateList<Budget>, fab: MutableState<Boolean>,
                                onDismissRequest: () -> Unit = { fab.value = false },
                                onConfirmation: () -> Unit = { fab.value = false },
) {
    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val dateMutable1: MutableState<String> = mutableStateOf(formattedDate)
    val dateMutable2: MutableState<String> = mutableStateOf(formattedDate)

    ShowBudgetDialog(onDismissRequest = onDismissRequest, onConfirmation = onConfirmation,
                     lFixedBudgets = lFixedBudgets, dateMutable1 = dateMutable1,
                     dateMutable2 = dateMutable2)
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FixedBudgetsComposableScreen(lFixedBudgets: SnapshotStateList<Budget>) {
    val fab: MutableState<Boolean> = remember { mutableStateOf(false) }

    if (fab.value) {
        ShowAddBudgetDialog(lFixedBudgets = lFixedBudgets, fab = fab)
    } else {
        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = { fab.value = !fab.value }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }) { innerPadding ->
            Column(modifier = Modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally,) {
                Spacer(Modifier.fillMaxHeight(40F / LocalConfiguration.current.screenHeightDp))

                Text(text = stringResource(id = R.string.ecran_bugete_fixe),
                     style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        textDecoration = TextDecoration.Underline
                     ),
                     fontSize = 30.sp
                )

                Spacer(Modifier.fillMaxHeight(30F / LocalConfiguration.current.screenHeightDp))

                BudgetsLazyColumn(lFixedBudgets)
            }
        }
    }
}