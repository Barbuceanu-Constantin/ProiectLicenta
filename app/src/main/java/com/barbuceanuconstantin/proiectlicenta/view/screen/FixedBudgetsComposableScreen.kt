package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import com.barbuceanuconstantin.proiectlicenta.FloatingActionButtonCustom
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.model.BudgetsLazyColumn
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource

@Composable
fun FixedBudgetsComposableScreen(lFixedBudgets: SnapshotStateList<Budget>) {
    val fab: MutableState<Boolean> = remember { mutableStateOf(false) }
    val buttons: MutableState<Boolean> = remember { mutableStateOf(false)}

    Scaffold(
        floatingActionButton = { FloatingActionButtonCustom(addButton = fab) }
    ) { innerPadding ->
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

            Text(text = stringResource(id = R.string.ecran_bugete_fixe),
                 style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                 ),
                 fontSize = fontDimensionResource(id = R.dimen.seventy_five_sp)
            )

            Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))

            BudgetsLazyColumn(lFixedBudgets, buttons)
        }
    }
}