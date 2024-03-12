package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.widget.CalendarView
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.barbuceanuconstantin.proiectlicenta.R

@Composable
fun calendarScreenLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var date by remember {
            mutableStateOf("")
        }
        Spacer(Modifier.fillMaxHeight(10F / LocalConfiguration.current.screenHeightDp))
        AndroidView(factory = { CalendarView(it) },
            update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    date = "$day - ${month + 1} - $year"
                }
            },
            modifier = Modifier.background(color = Color(250, 230, 200))
        )
        Spacer(Modifier.fillMaxHeight(10F / LocalConfiguration.current.screenHeightDp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(240, 200, 80), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.venit_zi_curenta) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 20.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(210, 20, 20), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.cheltuieli_zi_curenta) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 20.sp
            )
        }
        HorizontalDivider(thickness = 5.dp, color = Color.Gray)
        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 60f / LocalConfiguration.current.screenHeightDp)
                .background(color = Color(60, 205, 40), shape = CutCornerShape(0.5f))
        ) {
            Text(
                text = stringResource(id = R.string.balanta) + " : ",
                modifier = Modifier.padding(start = 10.dp).align(Alignment.CenterStart),
                fontSize = 20.sp
            )
        }
    }
}

