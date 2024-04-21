package com.barbuceanuconstantin.proiectlicenta

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.data.model.Budget
import com.barbuceanuconstantin.proiectlicenta.data.model.Category
import com.barbuceanuconstantin.proiectlicenta.view.screen.isDateAfterOrEqualToCurrent
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

//@Composable
//fun ThreeTopButtons(first: MutableState<Boolean>, second: MutableState<Boolean>,
//                    third: MutableState<Boolean>, shortName: Boolean = false,
//                    firstId: Int, secondId: Int, thirdId: Int
//) {
//    val modifier: Modifier = Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp)
//    val containerColor1 = colorResource(id = R.color.dark_purple)
//    val contentColor1: Color = colorResource(id = R.color.white)
//    val containerColor2 = colorResource(id = R.color.light_purple)
//    val contentColor2: Color = colorResource(id = R.color.black)
//    val aContainer : Color
//    val aContent : Color
//    val pContainer : Color
//    val pContent : Color
//    val dContainer : Color
//    val dContent : Color
//    if (first.value && !second.value && !third.value) {
//        aContainer = containerColor1
//        aContent = contentColor1
//        pContainer = containerColor2
//        pContent = contentColor2
//        dContainer = containerColor2
//        dContent = contentColor2
//    } else if (second.value && !first.value && !third.value) {
//        aContainer = containerColor2
//        aContent = contentColor2
//        pContainer = containerColor1
//        pContent = contentColor1
//        dContainer = containerColor2
//        dContent = contentColor2
//    } else if (third.value && !first.value && !second.value) {
//        aContainer = containerColor2
//        aContent = contentColor2
//        pContainer = containerColor2
//        pContent = contentColor2
//        dContainer = containerColor1
//        dContent = contentColor1
//    } else {
//        aContainer = containerColor2
//        aContent = contentColor2
//        pContainer = containerColor2
//        pContent = contentColor2
//        dContainer = containerColor2
//        dContent = contentColor2
//    }
//
//    Button(onClick = {
//        first.value = true
//        second.value = false
//        third.value = false
//    }, modifier = modifier, colors = ButtonColors(containerColor = aContainer, contentColor = aContent, disabledContainerColor = colorResource(id = R.color.gray), disabledContentColor = colorResource(id = R.color.gray))) {
//        if (!shortName)
//            Text(text = stringResource(id = firstId), fontSize = 20.sp)
//        else
//            Text(text = stringResource(id = firstId))
//    }
//
//    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))
//
//    Button(
//        onClick = {
//            second.value = true
//            first.value = false
//            third.value = false
//        },
//        modifier = modifier,  colors = ButtonColors(containerColor = pContainer, contentColor = pContent, disabledContainerColor = colorResource(id = R.color.gray), disabledContentColor = colorResource(id = R.color.gray))
//    ) {
//        if (!shortName)
//            Text(text = stringResource(id = secondId), fontSize = 20.sp)
//        else
//            Text(text = stringResource(id = secondId))
//    }
//
//    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))
//
//    Button(
//        onClick = {
//            third.value = true
//            first.value = false
//            second.value = false
//        },
//        modifier = modifier,   colors = ButtonColors(containerColor = dContainer, contentColor = dContent, disabledContainerColor = colorResource(id = R.color.gray), disabledContentColor = colorResource(id = R.color.gray))
//    ) {
//        if (!shortName)
//            Text(text = stringResource(id = thirdId), fontSize = 20.sp)
//        else
//            Text(text = stringResource(id = thirdId))
//    }
//}
//
//@Composable
//fun FourthButton(id: Int, first: MutableState<Boolean>, second: MutableState<Boolean>, third: MutableState<Boolean>) {
//    val containerColor: Color
//    val contentColor: Color
//
//    if (first.value && second.value && third.value) {
//        containerColor = colorResource(id = R.color.dark_purple)
//        contentColor = colorResource(id = R.color.white)
//    } else {
//        containerColor = colorResource(id = R.color.light_purple)
//        contentColor = colorResource(id = R.color.black)
//    }
//
//    Spacer(Modifier.fillMaxHeight(fraction = 10F / LocalConfiguration.current.screenHeightDp))
//
//    Button(onClick = {
//        first.value = true
//        second.value = true
//        third.value = true
//    }, modifier = Modifier, colors = ButtonColors(containerColor = containerColor, contentColor = contentColor, disabledContainerColor = colorResource(id = R.color.gray), disabledContentColor = colorResource(id = R.color.gray))) {
//        Text(text = stringResource(id = id), fontSize = 20.sp)
//    }
//
//    Spacer(Modifier.fillMaxHeight(fraction = 25F / LocalConfiguration.current.screenHeightDp))
//}
//
//@Composable
//fun HeaderSelectCategoryOrTransactionWindow(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>) {
//    Text(text = stringResource(R.string.mesaj_selectare_categorie_principala),
//        modifier = Modifier.fillMaxWidth(),
//        fontSize = fontDimensionResource(R.dimen.fifty_sp),
//        fontWeight = FontWeight.Bold,
//        color = colorResource(id = R.color.red))
//
//    Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
//
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//        ThreeTopButtons(first = showA, second = showP, third = showD, shortName = true, firstId = R.string.prescurtareActive, secondId = R.string.prescurtarePasive, thirdId = R.string.prescurtareDatorii)
//    }
//
//    Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
//}

/*
this@LazyColumn.stickyHeader {
    if (a && p && d) {
        val color = when (subcateg) {
            categorii[a] -> colorResource(R.color.yellow)
            categorii[p] -> colorResource(R.color.red)
            categorii[d] -> colorResource(R.color.blue)
            else -> MaterialTheme.colorScheme.primaryContainer
        }
        AntetSubcategory(text = subcateg.name, color)
    } else {
        AntetSubcategory(text = subcateg.name, MaterialTheme.colorScheme.primaryContainer)
    }
    index.value += 1
}
*/

/*
data class Subcategory(
    val id: Int,
    val categoryType: CategoryType,
    val name: String,
    val items: SnapshotStateList<String>
)

enum class CategoryType(val id: Int) {
    ACTIVE(1),
    PASIVE(2),
    DATORII(3)
}
 */

/*
Din PrincipalComposableScreen

addButton.value = true -> in OnClick
val addButton: MutableState<Boolean> = remember { mutableStateOf(false) }

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
}*/

/*Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    Row{
        Button(
            onClick = {
                if (!(showA.value && showP.value && showD.value) && !showMeniuValute.value && !showMeniuSubcategorys.value) {
                    showMeniuSubcategorys.value = !showMeniuSubcategorys.value
                }
            }
        ) { Text(stringResource(R.string.mesaj_selectare_subcategory)) }

        Spacer(Modifier.width(dimensionResource(id = R.dimen.ten_dp)))

        Button(
            onClick = {
                if (!(showA.value && showP.value && showD.value) && !showMeniuSubcategorys.value && !showMeniuValute.value) {
                    showMeniuValute.value = !showMeniuValute.value
                }
            }
        ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
    }
}*/

/*Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    Row{
        Button(
            onClick = {
                if (!(showA.value && showP.value && showD.value) && !showMeniuValute.value && !showMeniuSubcategorys.value) {
                    showMeniuSubcategorys.value = !showMeniuSubcategorys.value
                }
            }
        ) { Text(stringResource(R.string.mesaj_selectare_subcategory)) }

        Spacer(Modifier.width(dimensionResource(id = R.dimen.ten_dp)))

        Button(
            onClick = {
                if (!(showA.value && showP.value && showD.value) && !showMeniuSubcategorys.value && !showMeniuValute.value) {
                    showMeniuValute.value = !showMeniuValute.value
                }
            }
        ) { Text(stringResource(R.string.mesaj_selectare_valuta)) }
    }
}*/

/*
private fun adaugareTranzactie(l: SnapshotStateList<Tranzactie>, currency:String, subcategory:String,
                               valueSum:String, payee:String, date:String, description:String) {
    val newTranzactie = Tranzactie(valueSum.toDouble(), currency, description, subcategory, date, payee)
    l.add(0, newTranzactie)
}
*/

//        DatePickerDialog() {
//                Column( modifier = Modifier.fillMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally) {
//                    Calendar(onDateSelected = { selectedDate -> dateMutable.value = selectedDate })
//
//                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))
//
//                    OkButton(ok = dateButton)
//                }
//        }

//Text(text = stringResource(R.string.mesaj_selectare_categorie_principala),
//modifier = Modifier
//.fillMaxWidth()
//.padding(start = dimensionResource(id = R.dimen.margin),
//top = dimensionResource(id = R.dimen.margin)),
//fontSize = fontDimensionResource(R.dimen.fifty_sp),
//fontWeight = FontWeight.Bold,
//color = colorResource(id = R.color.red))

/*else if (showA.value && showP.value && showD.value) {
    Spacer(Modifier.height(dimensionResource(id = R.dimen.thirty_dp)))
    WarningNotSelectedCategory()
}*/

/*ShowMenuCurrencies(showMeniuValute = showMeniuValute) {
    currency = it
}*/

/*if (addButton.value) {
    val dateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = dateTime.format(dateFormatter)
    val date by remember { mutableStateOf(formattedDate) }
    val dateMutable: MutableState<String> = remember { mutableStateOf(date) }
    val showAB: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showPB: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showDB: MutableState<Boolean> = remember { mutableStateOf(true) }
    *//*ShowTransactionDialog(onDismissRequest = { addButton.value = false },
                          onConfirmation = {addButton.value = false},
                          lActive = lTrA, lPasive = lTrP, lDatorii = lTrD,
                          dateMutable = dateMutable, showAB = showAB,
                          showPB = showPB, showDB = showDB)*//*
}*/

//@Composable
//private fun AntetSubcategory(text: String, color: Color) {
//    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color)
//            .padding(16.dp)
//    )
//}

//val color : Color = if (a) {
//    colorResource(R.color.light_cream_yellow)
//} else if (p) {
//    colorResource(R.color.light_cream_red)
//} else if (d) {
//    colorResource(R.color.light_cream_blue)
//} else {
//    colorResource(R.color.light_cream_gray)
//}

/*
private fun addSubcategory(l: MutableList<Category>, firstLetter:String, filledText:String) {
    val foundSubcategory = l.find{it.name == firstLetter}

    if (foundSubcategory != null) {
        foundSubcategory.items.add(filledText)
    } else {
        val insertionIndex = l.binarySearch { it.name.compareTo(firstLetter) }

        val newSubcategory = Category(name = firstLetter, items = mutableStateListOf(filledText))

        if (insertionIndex < 0) {
            l.add(-insertionIndex - 1, newSubcategory)
        } else {
            l.add(insertionIndex, newSubcategory)
        }
    }
}*/

//if (showA.value && !showP.value && !showD.value) {
//    if (subcategorysPredefinitePasive.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (subcategorysPredefiniteDatorii.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (ok)
//        addSubcategory(lActive, firstLetter, filledText)
//} else if (showP.value && !showA.value && !showD.value) {
//    if (subcategorysPredefiniteActive.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (subcategorysPredefiniteDatorii.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (ok)
//        addSubcategory(lPasive, firstLetter, filledText)
//} else if (showD.value && !showA.value && !showP.value) {
//    if (subcategorysPredefiniteActive.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (subcategorysPredefinitePasive.any { entry ->
//            entry.value.contains(filledText)
//        }) {
//        ok = false
//    }
//    if (ok)
//        addSubcategory(lDatorii, firstLetter, filledText)
//}

//resetButtons(showA, showP, showD)
//addButton.value = !addButton.value

//if (filledText != "") {
//    val firstLetter = filledText[0].toString().uppercase()
//    var ok = true
//}

//Text(text = stringResource(id = R.string.mesaj_selectare_subcategory),
//modifier = Modifier.fillMaxWidth(),
//fontSize = fontDimensionResource(R.dimen.fifty_sp),
//fontWeight = FontWeight.Bold)

//if (a && p && d) {
//    if (subcategorysPredefiniteActive.any { entry ->
//            entry.value.contains(text)
//        }) {
//        copyA = true
//        copyP = false
//        copyD = false
//    } else if (subcategorysPredefinitePasive.any { entry ->
//            entry.value.contains(text)
//        }) {
//        copyA = false
//        copyP = true
//        copyD = false
//    } else if (subcategorysPredefiniteDatorii.any { entry ->
//            entry.value.contains(text)
//        }) {
//        copyA = false
//        copyP = false
//        copyD = true
//    }

//private fun addBudget(lFixedBudgets: SnapshotStateList<Budget>, filledText: String, valueSum: String,
//                      date1: String, date2: String) {
//    val newBudget = Budget(date1, date2, filledText, valueSum.toDouble())
//    lFixedBudgets.add(0, newBudget)
//}

//if (filledText != "" && valueSum != "") {
//    addBudget(lFixedBudgets, filledText, valueSum,
//        dateMutable1.value, dateMutable2.value)
//}

/*
if (dateButton1.value && !dateButton2.value) {
    DatePickerDialog(onDismissRequest = { dateButton1.value = !dateButton1.value },
        confirmButton = {},
        dismissButton = {}) {
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Calendar(onDateSelected = { selectedDate ->
                if (isDateAfterOrEqualToCurrent(selectedDate, LocalDate.now())) {
                    dateMutable1.value = selectedDate
                }
            })

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

            OkButton(ok = dateButton1)
        }
    }
} else if (!dateButton1.value && dateButton2.value) {
    if (!openDialog.value) {
        DatePickerDialog(onDismissRequest = { dateButton2.value = !dateButton2.value },
            confirmButton = {},
            dismissButton = {}) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Calendar(onDateSelected = { selectedDate ->
                    if (isDateAfterOrEqualToCurrent(selectedDate, LocalDate.now())) {
                        val dateString: String = dateMutable1.value
                        val formatter: DateTimeFormatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val localDate: LocalDate = LocalDate.parse(dateString, formatter)
                        if (isDateAfterOrEqualToCurrent(selectedDate, localDate)) {
                            dateMutable2.value = selectedDate
                        } else {
                            openDialog.value = true
                        }
                    }
                })

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_hundred)))

                OkButton(ok = dateButton2)
            }
        }
    } else {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false // Dismiss the dialog when the user clicks outside the dialog or on the back button
            },
            title = {
                Text(text = stringResource(id = R.string.avertisment_data))
            },
            text = {
                Text(text = stringResource(id = R.string.avertisment_data_continut))
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
} else if (!dateButton1.value && !dateButton2.value) {*/

/*
val dateButton1 = remember { mutableStateOf(false) }
val dateButton2 = remember { mutableStateOf(false) }*/

//val dateTime = LocalDateTime.now()
//val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//val formattedDate = dateTime.format(dateFormatter)
//val dateMutable1: MutableState<String> = mutableStateOf(formattedDate)
//val dateMutable2: MutableState<String> = mutableStateOf(formattedDate)

//@Composable
//private fun ShowAddBudgetDialog(lFixedBudgets: SnapshotStateList<Budget>, fab: MutableState<Boolean>,
//                                onDismissRequest: () -> Unit = { fab.value = false },
//                                onConfirmation: () -> Unit = { fab.value = false },
//) {
////    ShowBudgetDialog(onDismissRequest = onDismissRequest, onConfirmation = onConfirmation,
////                     lFixedBudgets = lFixedBudgets, dateMutable1 = dateMutable1,
////                     dateMutable2 = dateMutable2)
//}

//val fab: MutableState<Boolean> = remember { mutableStateOf(false) }
//
//if (fab.value) {
//    ShowAddBudgetDialog(lFixedBudgets = lFixedBudgets, fab = fab)
//} else {

//,
//onDeleteItem = { lFixedBudgets.remove(budget) }

//Row {
//    ThreeTopButtons(first = daily, second = weekly, third = monthly,
//        firstId = R.string.zilnic, secondId = R.string.saptamanal, thirdId = R.string.lunar)
//}
//
//FourthButton(id = R.string.total, first = daily, second = weekly, third = monthly)

//@Composable
//fun SummaryTranzactiiLazyColumn(tranzactii: SnapshotStateList<Transaction>) {
//    val buttons: MutableState<Boolean> = remember { mutableStateOf(false)}
//
//    LazyColumn(modifier = Modifier.fillMaxHeight(0.85F)) {
//        items(tranzactii) {
//                tranzactie -> Tranzactie(transaction = tranzactie, buttons)
//                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
//        }
//    }
//}

//@Composable
//fun SwipeCard(onSwipeLeft: () -> Unit = {},
//              onSwipeRight: () -> Unit = {},
//              swipeThreshold: Float = 200f,
//              sensitivityFactor: Float = 4f,
//              content: @Composable () -> Unit) {
//    var offset by remember { mutableStateOf(0f) }
//    var dismissRight by remember { mutableStateOf(false) }
//    var dismissLeft by remember { mutableStateOf(false) }
//    val density = LocalDensity.current.density
//
//    LaunchedEffect(dismissRight) {
//        if (dismissRight) {
//            delay(200)
//            onSwipeRight.invoke()
//            dismissRight = false
//        }
//    }
//
//    LaunchedEffect(dismissLeft) {
//        if (dismissLeft) {
//            delay(200)
//            onSwipeLeft.invoke()
//            dismissLeft = false
//        }
//    }
//
//    Box(modifier = Modifier
//        .offset { IntOffset(offset.roundToInt(), 0) }
//        .pointerInput(Unit) {
//            detectHorizontalDragGestures(onDragEnd = {
//                offset = 0f
//            }) { change, dragAmount ->
//
//                offset += (dragAmount / density) * sensitivityFactor
//                when {
//                    offset > swipeThreshold -> {
//                        dismissRight = true
//                    }
//
//                    offset < -swipeThreshold -> {
//                        dismissLeft = true
//                    }
//                }
//                if (change.positionChange() != Offset.Zero) change.consume()
//            }
//        }
//        .graphicsLayer(
//            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f, label = "").value,
//            rotationZ = animateFloatAsState(offset / 50, label = "").value
//        )) {
//        content()
//    }
//}

//Screen0()
//EditTransactionScreen()
//Screen1()
//Screen2()
//EditCategoryScreen()
//Screen4()
//EditBudgetScreen()
//Screen6()
//Screen7()

//Spacer(Modifier.height(dimensionResource(id = R.dimen.gap)))
//
//Text(text = stringResource(id = R.string.ecran_bugete_fixe),
//style = TextStyle(
//fontStyle = FontStyle.Italic,
//textDecoration = TextDecoration.Underline
//),
//fontSize = fontDimensionResource(id = R.dimen.big_text_size)
//)