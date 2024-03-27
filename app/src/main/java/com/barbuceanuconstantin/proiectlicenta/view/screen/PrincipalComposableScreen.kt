package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R

@Composable
private fun TotalBalance(revenue: Float, expenses: Float, debt: Float) {
    Column() {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 70f / LocalConfiguration.current.screenHeightDp)
                                .background(color = colorResource(R.color.yellow), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.active) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }

        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 70f / LocalConfiguration.current.screenHeightDp)
                                .background(color = colorResource(id = R.color.red), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.pasive) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 70f / LocalConfiguration.current.screenHeightDp)
                                .background(color = colorResource(R.color.blue), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.datorii) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }

        Spacer(Modifier.fillMaxHeight(20f / LocalConfiguration.current.screenHeightDp))

        HorizontalDivider(thickness = 5.dp, color = colorResource(id = R.color.gray))

        Spacer(Modifier.fillMaxHeight(20f / LocalConfiguration.current.screenHeightDp))

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 70f / LocalConfiguration.current.screenHeightDp)
                                .background(color = colorResource(R.color.medium_green), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.balanta) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }
    }
}
@Composable
fun PrincipalComposableScreen(revenue: Float, expenses: Float, debt: Float) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(100F / LocalConfiguration.current.screenHeightDp))

            TotalBalance(revenue, expenses, debt)
        }
    }
}

