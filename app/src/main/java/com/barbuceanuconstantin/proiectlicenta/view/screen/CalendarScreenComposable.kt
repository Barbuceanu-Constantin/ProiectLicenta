package com.barbuceanuconstantin.proiectlicenta.view.screen

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun calendarScreenLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var date by remember {
            mutableStateOf("")
        }
        Spacer(Modifier.fillMaxHeight(20F / LocalConfiguration.current.screenHeightDp))
        AndroidView(factory = { CalendarView(it) },
            update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    date = "$day - ${month + 1} - $year"
                }
            },
            modifier = Modifier.background(color = Color(250, 230, 200))
        )
        Text(text = date)
    }
}

