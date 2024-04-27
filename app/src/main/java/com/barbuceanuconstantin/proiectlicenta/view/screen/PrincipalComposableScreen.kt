package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.barbuceanuconstantin.proiectlicenta.Balance
import com.barbuceanuconstantin.proiectlicenta.BottomNavigationBar
import com.barbuceanuconstantin.proiectlicenta.MainScreenToAppBar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MoreScreensMenu

@Composable
private fun TotalBalance() {
    Column {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin)),
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                         end = dimensionResource(id = R.dimen.margin))) {
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.thin_line),
                color = colorResource(id = R.color.gray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.upper_middle))
                    .background(color = colorResource(R.color.light_cream_yellow))
            ) {
                Text(
                    text = stringResource(id = R.string.active) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.medium_line))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.upper_middle))
                    .background(color = colorResource(R.color.light_cream_red))
            ) {
                Text(
                    text = stringResource(id = R.string.pasive) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.medium_line))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.upper_middle))
                    .background(color = colorResource(R.color.light_cream_blue))
            ) {
                Text(
                    text = stringResource(id = R.string.datorii) + " : ",
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.medium_line))
                        .align(Alignment.CenterStart),
                    fontSize = fontDimensionResource(id = R.dimen.medium_text_size)
                )
            }
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.thin_line),
                color = colorResource(id = R.color.gray)
            )
        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

        Balance()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalComposableScreen(onNavigateToEditTransactionScreen: (index : Int) -> Unit,
                              onNavigateToHomeScreen: () -> Unit,
                              onNavigateToTransactionScreen: () -> Unit,
                              onNavigateToCategoriesScreen: () -> Unit,
                              onNavigateToFixedBudgetsScreen: () -> Unit,
                              onNavigateToBudgetSummaryScreen: () -> Unit,
                              onNavigateToCalendarScreen: () -> Unit,
                              onNavigateToGraphsScreen: () -> Unit) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val options = listOf(
        stringResource(id = R.string.Venituri),
        stringResource(id = R.string.Cheltuieli),
        stringResource(id = R.string.Datorii)
    )

    Scaffold(
                bottomBar = {
                                BottomNavigationBar(
                                                    onNavigateToHomeScreen,
                                                    onNavigateToTransactionScreen,
                                                    onNavigateToCategoriesScreen,
                                                    onNavigateToFixedBudgetsScreen
                                )
                },
                topBar = {
                    MainScreenToAppBar( id = R.string.acasa,
                                        onNavigateToBudgetSummaryScreen = onNavigateToBudgetSummaryScreen,
                                        onNavigateToCalendarScreen = onNavigateToCalendarScreen,
                                        onNavigateToGraphsScreen = onNavigateToGraphsScreen)
                }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.middle)))

            TotalBalance()

            Spacer(Modifier.height(dimensionResource(id = R.dimen.almost_hundred)))

            Text(
                text = stringResource(id = R.string.adaugare_tranzactie) + " : ",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )

            Spacer(Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = {
                            selectedIndex = index
                            onNavigateToEditTransactionScreen(selectedIndex)
                        },
                        selected = index == selectedIndex,
                        modifier = Modifier.height(dimensionResource(id = R.dimen.upper_middle))
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

