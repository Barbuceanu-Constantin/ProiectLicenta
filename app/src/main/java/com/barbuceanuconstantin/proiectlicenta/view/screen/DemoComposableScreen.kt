package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import com.barbuceanuconstantin.proiectlicenta.di.DemoScreenUIState
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun DemoComposableScreen(
    onNavigateToHomeScreen: () -> Unit,
    demoScreenUIState: DemoScreenUIState,
    onInitCategoryLists: () -> Unit,
    onDeleteTables: () -> Unit,
    updateTablesForDemo: () -> Unit,
    getMainCategoryCount: () -> Int
) {
    var isClicked1 by remember { mutableStateOf(false) }
    var isClicked2 by remember { mutableStateOf(false) }
    var isClicked3 by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(id = R.dimen.margin),
                end = dimensionResource(id = R.dimen.margin)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.mesaj_bun_venit),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.big_text_size),
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.purple_200),
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.gap)))

            Text(
                text = stringResource(id = R.string.mesaj_alegere_varianta_demo),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.big_text_size),
                    color = colorResource(id = R.color.purple_200)
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.gap)))

            Box (contentAlignment = Alignment.Center) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.height(dimensionResource(id = R.dimen.upper_middle)),
                        onClick = {
                            if (!isClicked2 && !isClicked3) {
                                isClicked1 = true
                                onDeleteTables()
                                onInitCategoryLists()
                                updateTablesForDemo()
                                onNavigateToHomeScreen()
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.demo),
                            style = TextStyle(
                                fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle)))

                    Button(
                        modifier = Modifier.height(dimensionResource(id = R.dimen.upper_middle)),
                        onClick = {
                            if (!isClicked1 && !isClicked3) {
                                isClicked2 = true
                                onDeleteTables()
                                onInitCategoryLists()
                                onNavigateToHomeScreen()
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.pornire_de_la_zero),
                            style = TextStyle(
                                fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle)))

                    Button(
                        modifier = Modifier.height(dimensionResource(id = R.dimen.upper_middle)),
                        onClick = {
                            if (!isClicked1 && !isClicked2) {
                                isClicked3 = true
                                if (getMainCategoryCount() == 0)
                                    onInitCategoryLists()
                                onNavigateToHomeScreen()
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.continuare),
                            style = TextStyle(
                                fontSize = fontDimensionResource(id = R.dimen.normal_text_size),
                            )
                        )
                    }
                }
            }
        }
    }
}