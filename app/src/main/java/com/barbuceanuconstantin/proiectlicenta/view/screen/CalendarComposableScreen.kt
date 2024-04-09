package com.barbuceanuconstantin.proiectlicenta.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.Balanta
import com.barbuceanuconstantin.proiectlicenta.Calendar
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource

@Composable
fun CalendarComposableScreen() {
    val dateMutable: MutableState<String> = remember { mutableStateOf("") }

    Column( horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(dimensionResource(id = R.dimen.eighty_dp)))

        Calendar(onDateSelected = { selectedDate ->
            dateMutable.value = selectedDate // Update the date value
        })

        Spacer(Modifier.height(dimensionResource(id = R.dimen.twenty_dp)))

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
                    text = stringResource(id = R.string.venit_zi_curenta) + " : ",
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
                    text = stringResource(id = R.string.cheltuieli_zi_curenta) + " : ",
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