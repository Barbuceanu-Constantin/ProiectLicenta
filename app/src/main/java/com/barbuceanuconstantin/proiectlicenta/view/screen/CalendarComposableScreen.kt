package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.calendar

@Composable
fun calendarComposableScreen(dateMutable: MutableState<String>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.fillMaxHeight(10F / LocalConfiguration.current.screenHeightDp))

        calendar(onDateSelected = { selectedDate ->
            dateMutable.value = selectedDate // Update the date value
        })

        Spacer(Modifier.fillMaxHeight(10F / LocalConfiguration.current.screenHeightDp))

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp)
                                .background(color = colorResource(R.color.yellow), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.venit_zi_curenta) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp).background(color = colorResource(id = R.color.red), shape = CutCornerShape(0.5f))) {
            Text(text = stringResource(id = R.string.cheltuieli_zi_curenta) + " : ",
                 modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                 fontSize = 20.sp
            )
        }

        HorizontalDivider(thickness = 5.dp, color = colorResource(id = R.color.gray))

        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))

        Box(modifier =  Modifier.fillMaxWidth().
                        fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp).
                        background(color = colorResource(R.color.medium_green), shape = CutCornerShape(0.5f))
        ) {
            Text(text = stringResource(id = R.string.balanta) + " : ", modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart), fontSize = 20.sp)
        }
    }
}

