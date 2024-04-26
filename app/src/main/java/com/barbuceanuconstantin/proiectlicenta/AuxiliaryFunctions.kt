@file:OptIn(ExperimentalMaterial3Api::class)

package com.barbuceanuconstantin.proiectlicenta

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.CalendarView
import androidx.annotation.DimenRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MoreScreensMenu
import java.util.Locale

@Composable
fun MainScreenToAppBar(id: Int, scrollBehavior: TopAppBarScrollBehavior? = null) {
    var actualScrollBehavior = scrollBehavior
    if (actualScrollBehavior == null) {
        actualScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(id = id))
        },
        actions = {
            MoreScreensMenu()
        },
        scrollBehavior = actualScrollBehavior
    )
}

@Composable
fun EditTopAppBar(id : Int) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(id = id),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.inapoi)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.acasa)
                )
            }
        }
    )
}

@Composable
fun Balance() {
    Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin)),
         modifier = Modifier.padding(dimensionResource(id = R.dimen.margin))) {
        HorizontalDivider(
            thickness = dimensionResource(id = R.dimen.thin_line),
            color = colorResource(id = R.color.gray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.upper_middle))
                .background(color = colorResource(R.color.light_cream_green))
        ) {
            Text(
                text = stringResource(id = R.string.balanta) + " : ",
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
}

@Composable
fun fontDimensionResource(@DimenRes id: Int): TextUnit {
    val dpValue = dimensionResource(id = id).value
    val spValue = LocalDensity.current.run {
        dpValue.toSp()
    }
    return spValue
}

@Composable
fun FloatingActionButtonCustom(navigateAction: () -> Unit) {
    FloatingActionButton(onClick = { navigateAction() },
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = dimensionResource(id = R.dimen.medium_line),
            pressedElevation = dimensionResource(id = R.dimen.margin),
            hoveredElevation = dimensionResource(id = R.dimen.spacing),
            focusedElevation = dimensionResource(id = R.dimen.very_thin_line)
        )) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}
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
fun IntToMonth(month : Int, monthMutable : MutableState<String>) {
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
fun OkButton(ok: MutableState<Boolean>, id: Int = R.string.ok) {
    val buttonWidthFraction = if (id == R.string.ok) 0.3f else 0.5f

    Button( onClick = { ok.value = !ok.value },
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.ok_button_height))
                .fillMaxWidth(buttonWidthFraction)) {
        Text(text = stringResource(id = id), fontSize = 20.sp)
    }
}

@Composable
fun Calendar(onDateSelected: (String) -> Unit) {
    AndroidView(
        factory = { CalendarView(it) },
        update = {
            it.setOnDateChangeListener { _, year, month, day ->
                val formattedDate = "$year-${(month + 1) / 10}${(month + 1) % 10}-$day"
                onDateSelected(formattedDate) // Call the callback function
            }
        },
        modifier = Modifier
            .background(color = colorResource(R.color.light_cream))
            .border(width = 10.dp, color = colorResource(id = R.color.dark_green))
            .fillMaxWidth()
    )
}

@Composable
fun TimeIntervalSegmentedButton(daily: MutableState<Boolean>,
                                weekly: MutableState<Boolean>,
                                monthly: MutableState<Boolean>) {
    var selectedIndex1 by remember { mutableStateOf(-1) }
    var selectedIndex2 by remember { mutableStateOf(0) }

    val options1 = listOf(
        stringResource(id = R.string.zilnic),
        stringResource(id = R.string.saptamanal),
        stringResource(id = R.string.lunar),
    )

    val options2 = listOf(
        stringResource(id = R.string.toate),
    )

    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
            ) {
                options1.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options1.size
                        ),
                        onClick = {
                            selectedIndex1 = index
                            when (selectedIndex1) {
                                0 -> {
                                    daily.value = true
                                    weekly.value = false
                                    monthly.value = false
                                    selectedIndex2 = -1
                                }

                                1 -> {
                                    weekly.value = true
                                    daily.value = false
                                    monthly.value = false
                                    selectedIndex2 = -1
                                }

                                2 -> {
                                    monthly.value = true
                                    daily.value = false
                                    weekly.value = false
                                    selectedIndex2 = -1
                                }
                            }
                        },
                        selected = index == selectedIndex1
                    ) {
                        Row {
                            Text(text = label)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.medium_line)))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
            ) {
                options2.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options2.size
                        ),
                        onClick = {
                            selectedIndex2 = index
                            when (selectedIndex2) {
                                0 -> {
                                    daily.value = true
                                    weekly.value = true
                                    monthly.value = true
                                    selectedIndex1 = -1
                                }
                            }
                        },
                        selected = index == selectedIndex2
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButton3(first: MutableState<Boolean>, second: MutableState<Boolean>,
                     third: MutableState<Boolean>) {
    val checkedList = remember { mutableStateListOf<Int>() }
    val options = listOf(
        stringResource(id = R.string.active),
        stringResource(id = R.string.pasive),
        stringResource(id = R.string.datorii)
    )

    MultiChoiceSegmentedButtonRow(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.margin),
            end = dimensionResource(id = R.dimen.margin)
        )) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onCheckedChange = {
                    if (index in checkedList) {
                        checkedList.remove(index)
                        when (index) {
                            0 -> {
                                first.value = false
                            }

                            1 -> {
                                second.value = false
                            }

                            2 -> {
                                third.value = false
                            }
                        }
                    } else {
                        checkedList.add(index)
                        when (index) {
                            0 -> {
                                first.value = true
                            }

                            1 -> {
                                second.value = true
                            }

                            2 -> {
                                third.value = true
                            }
                        }
                    }
                },
                checked = index in checkedList
            ) {
                Row {
                    Text(text = label)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderSelectCategoryOrTransactionWindowSegmentedButton(showA: MutableState<Boolean>,
                                                           showP: MutableState<Boolean>,
                                                           showD: MutableState<Boolean>,
    
                                                           defaultValueSelected: Boolean = false) {

    var selectedIndex by remember { mutableStateOf(-1) }

    if(defaultValueSelected) selectedIndex = 0

    if (showA.value && !showP.value && !showD.value)
        selectedIndex = 0
    else if (showP.value && !showA.value && !showD.value)
        selectedIndex = 1
    else if (showD.value && !showA.value && !showP.value)
        selectedIndex = 2

    val options = listOf(
        stringResource(id = R.string.active),
        stringResource(id = R.string.pasive),
        stringResource(id = R.string.datorii)
    )

    Column {
        Spacer(Modifier.height(dimensionResource(id = R.dimen.gap)))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin)
                    )
            ) {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = {
                            selectedIndex = index
                            when (selectedIndex) {
                                0 -> {
                                    showA.value = true
                                    showP.value = false
                                    showD.value = false
                                }

                                1 -> {
                                    showP.value = true
                                    showA.value = false
                                    showD.value = false
                                }

                                2 -> {
                                    showD.value = true
                                    showA.value = false
                                    showP.value = false
                                }
                            }
                        },
                        selected = index == selectedIndex
                    ) {
                        Row {
                            Text(text = label)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.gap)))
    }
}
@Composable
fun WarningNotSelectedCategory() {
    Text(text = stringResource(id = R.string.avertisment_neselectare_categorie),
         modifier = Modifier
             .fillMaxWidth()
             .padding(
                 start = dimensionResource(id = R.dimen.margin),
                 top = dimensionResource(id = R.dimen.margin)
             )
        ,
         fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
         fontWeight = FontWeight.Bold,
         color = colorResource(id = R.color.red))
}

@Composable
fun BottomNavigationBar(
    onNavigateToScreen0: () -> Unit,
    onNavigateToScreen1: () -> Unit,
    onNavigateToScreen2: () -> Unit,
    onNavigateToScreen4: () -> Unit,
) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.acasa),
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.margin_extra))
                )
            },
            label = { Text(text = stringResource(id = R.string.acasa),
                            fontSize = fontDimensionResource(id = R.dimen.normal_text_size)) },
            selected = true,
            onClick = { onNavigateToScreen0() },
            alwaysShowLabel = true,
            selectedContentColor = colorResource(id = R.color.black),
            unselectedContentColor = colorResource(id = R.color.gray),
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = stringResource(id = R.string.tranzactii),
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.margin_extra))
                )
            },
            label = { Text(text = stringResource(id = R.string.tranzactii),
                            fontSize = fontDimensionResource(id = R.dimen.normal_text_size)) },
            selected = true,
            onClick = { onNavigateToScreen1() },
            alwaysShowLabel = true,
            selectedContentColor = colorResource(id = R.color.black),
            unselectedContentColor = colorResource(id = R.color.gray),
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = stringResource(id = R.string.categorii),
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.margin_extra))
                )
            },
            label = { Text(text = stringResource(id = R.string.categorii),
                            fontSize = fontDimensionResource(id = R.dimen.normal_text_size)) },
            selected = true,
            onClick = { onNavigateToScreen2() },
            alwaysShowLabel = true,
            selectedContentColor = colorResource(id = R.color.black),
            unselectedContentColor = colorResource(id = R.color.gray)
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = stringResource(id = R.string.bugete),
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.margin_extra))
                )
            },
            label = { Text(text = stringResource(id = R.string.bugete),
                            fontSize = fontDimensionResource(id = R.dimen.normal_text_size)) },
            selected = true,
            onClick = { onNavigateToScreen4() },
            alwaysShowLabel = true,
            selectedContentColor = colorResource(id = R.color.black),
            unselectedContentColor = colorResource(id = R.color.gray)
        )
    }
}