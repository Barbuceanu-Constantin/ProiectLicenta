package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.barbuceanuconstantin.proiectlicenta.Balanta
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.lTrA
import com.barbuceanuconstantin.proiectlicenta.lTrD
import com.barbuceanuconstantin.proiectlicenta.lTrP
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
private fun TotalBalance() {
    Column {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin))) {
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.five_dp),
                color = colorResource(id = R.color.gray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.sixty_dp))
                    .background(color = colorResource(R.color.light_cream_yellow))
            ) {
                Text(
                    text = stringResource(id = R.string.active) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.ten_dp))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.fifty_sp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.sixty_dp))
                    .background(color = colorResource(R.color.light_cream_red))
            ) {
                Text(
                    text = stringResource(id = R.string.pasive) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.ten_dp))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.fifty_sp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.sixty_dp))
                    .background(color = colorResource(R.color.light_cream_blue))
            ) {
                Text(
                    text = stringResource(id = R.string.datorii) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.ten_dp))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.fifty_sp)
                )
            }
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.five_dp),
                color = colorResource(id = R.color.gray)
            )
        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

        Balanta()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrincipalComposableScreen() {
    val addButton: MutableState<Boolean> = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }
    val options = listOf(
        stringResource(id = R.string.Venituri),
        stringResource(id = R.string.Cheltuieli),
        stringResource(id = R.string.Datorii)
    )

    if (addButton.value) {
        val dateTime = LocalDateTime.now()
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = dateTime.format(dateFormatter)
        val date by remember { mutableStateOf(formattedDate) }
        val dateMutable: MutableState<String> = remember { mutableStateOf(date) }
        val showAB: MutableState<Boolean> = remember { mutableStateOf(true) }
        val showPB: MutableState<Boolean> = remember { mutableStateOf(true) }
        val showDB: MutableState<Boolean> = remember { mutableStateOf(true) }
        when (selectedIndex) {
            0 -> {
                showPB.value = false
                showDB.value = false
            }
            1 -> {
                showAB.value = false
                showDB.value = false
            }
            2 -> {
                showAB.value = false
                showPB.value = false
            }
        }
        ShowTransactionDialog(onDismissRequest = { addButton.value = false },
            onConfirmation = {addButton.value = false},
            lActive = lTrA, lPasive = lTrP, lDatorii = lTrD,
            dateMutable = dateMutable, showAB = showAB,
            showPB = showPB, showDB = showDB)
    } else {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(Modifier.height(dimensionResource(id = R.dimen.eighty_dp)))

                TotalBalance()

                Spacer(Modifier.height(dimensionResource(id = R.dimen.one_half_hundred)))

                Text(
                    text = stringResource(id = R.string.adaugare_tranzactie) + " : ",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = fontDimensionResource(id = R.dimen.fifty_sp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                )

                Spacer(Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

                SingleChoiceSegmentedButtonRow {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size
                            ),
                            onClick = {
                                selectedIndex = index
                                addButton.value = true
                            },
                            selected = index == selectedIndex,
                            modifier = Modifier.height(dimensionResource(id = R.dimen.sixty_dp))
                        ) {
                            Row {
                                Text(text = label)
                            }
                        }
                    }
                }
            }
        }
    }
}

