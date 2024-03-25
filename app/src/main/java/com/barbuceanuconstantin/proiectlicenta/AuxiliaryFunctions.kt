package com.barbuceanuconstantin.proiectlicenta

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import java.util.Locale

//gpt
fun getStartAndEndDateOfWeek(dateString: String): Pair<String, String> {
    // Parse the input date string
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateString)

    // Initialize a Calendar instance and set it to the parsed date
    val calendar = Calendar.getInstance()
    calendar.time = date

    // Set the calendar to the first day of the week (usually Sunday)
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

    // Get the start date of the week
    val startDate = dateFormat.format(calendar.time)

    // Move the calendar to the end of the week (add 6 days)
    calendar.add(Calendar.DAY_OF_WEEK, 6)

    // Get the end date of the week
    val endDate = dateFormat.format(calendar.time)

    return Pair(startDate, endDate)
}
@Composable
fun intToMonth(month : Int, monthMutable : MutableState<String>) {
    when (month) {
        1 -> monthMutable.value = stringResource(id = R.string.ianuarie)
        2 -> monthMutable.value = stringResource(id = R.string.februarie)
        3 -> monthMutable.value = stringResource(id = R.string.martie)
        4 -> monthMutable.value = stringResource(id = R.string.aprilie)
        5 -> monthMutable.value = stringResource(id = R.string.mai)
        6 -> monthMutable.value = stringResource(id = R.string.iunie)
        7 -> monthMutable.value = stringResource(id = R.string.iulie)
        8 -> monthMutable.value = stringResource(id = R.string.august)
        9 -> monthMutable.value = stringResource(id = R.string.septembrie)
        10 -> monthMutable.value = stringResource(id = R.string.octombrie)
        11 -> monthMutable.value = stringResource(id = R.string.noiembrie)
        12 -> monthMutable.value = stringResource(id = R.string.decembrie)
    }
}
fun resetButtons(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>) {
    showA.value = true
    showP.value = true
    showD.value = true
}
@Composable
fun okButton(ok: MutableState<Boolean>, space: Boolean = true) {
    if (space)
        Spacer(Modifier.fillMaxHeight(50f / LocalConfiguration.current.screenHeightDp))
    else
        Spacer(Modifier.fillMaxHeight(10f / LocalConfiguration.current.screenHeightDp))

    val buttonWidthFraction = 0.3f
    Button(onClick = { ok.value = !ok.value },
        modifier = Modifier
            .fillMaxHeight(120f / LocalConfiguration.current.screenHeightDp)
            .fillMaxWidth(buttonWidthFraction)) {
        Text(text = stringResource(id = R.string.ok), fontSize = 20.sp)
    }
}
@Composable
fun calendar(date: MutableState<String>, onDateSelected: (String) -> Unit) {
    AndroidView(
        factory = { CalendarView(it) },
        update = {
            it.setOnDateChangeListener { calendarView, year, month, day ->
                val formattedDate = "$year-${(month + 1) / 10}${(month + 1) % 10}-$day"
                onDateSelected(formattedDate) // Call the callback function
            }
        },
        modifier = Modifier
            .background(color = Color(250, 230, 200))
            .border(width = 10.dp, color = Color(20, 100, 10))
    )
}
@Composable
fun headerSelectCategoryOrTransactionWindow(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>) {
    Text(text = stringResource(R.string.mesaj_selectare_categorie_principala),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
    Spacer(Modifier.fillMaxHeight(fraction = 5F / LocalConfiguration.current.screenHeightDp))
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        threeTopButtons(first = showA, second = showP, third = showD, shortName = true,
                        firstId = R.string.prescurtareActive,
                        secondId = R.string.prescurtarePasive,
                        thirdId = R.string.prescurtareDatorii)
    }
    Spacer(Modifier.fillMaxHeight(fraction = 15F / LocalConfiguration.current.screenHeightDp))
}
@Composable
fun warningNotSelectedCategory() {
    Text(
        text = stringResource(id = R.string.avertisment_neselectare_categorie),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
}

@Composable
fun fourthButton(id: Int, first: MutableState<Boolean>,
                 second: MutableState<Boolean>,
                 third: MutableState<Boolean>) {
    val containerColor: Color
    val contentColor: Color
    if (first.value && second.value && third.value) {
        containerColor = Color(70, 10, 110)
        contentColor = Color.White
    } else {
        containerColor = Color(220, 190, 245)
        contentColor = Color.Black
    }
    Spacer(Modifier.fillMaxHeight(fraction = 10F / LocalConfiguration.current.screenHeightDp))
    Button(onClick = {
        first.value = true
        second.value = true
        third.value = true
    }, modifier = Modifier, colors = ButtonColors(containerColor = containerColor, contentColor = contentColor, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)) {
        Text(text = stringResource(id = id), fontSize = 20.sp)
    }
    Spacer(Modifier.fillMaxHeight(fraction = 25F / LocalConfiguration.current.screenHeightDp))
}

@Composable
fun threeTopButtons(first: MutableState<Boolean>, second: MutableState<Boolean>,
                    third: MutableState<Boolean>, shortName: Boolean = false,
                    firstId: Int, secondId: Int, thirdId: Int
) {
    val modifier: Modifier = Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp)

    val containerColor1 = Color(70, 10, 110)
    val contentColor1: Color = Color.White
    val containerColor2 = Color(220, 190, 245)
    val contentColor2: Color = Color.Black
    val aContainer : Color
    val aContent : Color
    val pContainer : Color
    val pContent : Color
    val dContainer : Color
    val dContent : Color
    if (first.value && !second.value && !third.value) {
        aContainer = containerColor1
        aContent = contentColor1
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor2
        dContent = contentColor2
    } else if (second.value && !first.value && !third.value) {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor1
        pContent = contentColor1
        dContainer = containerColor2
        dContent = contentColor2
    } else if (third.value && !first.value && !second.value) {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor1
        dContent = contentColor1
    } else {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor2
        dContent = contentColor2
    }

    Button(onClick = {
                        first.value = true
                        second.value = false
                        third.value = false
    }, modifier = modifier, colors = ButtonColors(containerColor = aContainer, contentColor = aContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)) {
        if (!shortName)
            Text(text = stringResource(id = firstId), fontSize = 20.sp)
        else
            Text(text = stringResource(id = firstId))
    }

    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))

    Button(
        onClick = {
                        second.value = true
                        first.value = false
                        third.value = false
        },
        modifier = modifier,  colors = ButtonColors(containerColor = pContainer, contentColor = pContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)
    ) {
        if (!shortName)
            Text(text = stringResource(id = secondId), fontSize = 20.sp)
        else
            Text(text = stringResource(id = secondId))
    }

    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))

    Button(
        onClick = {
                        third.value = true
                        first.value = false
                        second.value = false
        },
        modifier = modifier,   colors = ButtonColors(containerColor = dContainer, contentColor = dContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)
    ) {
        if (!shortName)
            Text(text = stringResource(id = thirdId), fontSize = 20.sp)
        else
            Text(text = stringResource(id = thirdId))
    }
}