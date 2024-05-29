@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.FadingArrowIcon
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.TransactionWithCategory
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import com.barbuceanuconstantin.proiectlicenta.fontDimensionResource
import com.barbuceanuconstantin.proiectlicenta.returnToBudgetSummaryIndex
import com.barbuceanuconstantin.proiectlicenta.returnToCalendarIndex
import com.barbuceanuconstantin.proiectlicenta.returnToTransactionIndex
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Locale

fun CoroutineScope.launchDeleteCategoryById(
    delete: (Int) -> Unit,
    id: Int
) = launch {
    delete(id)
}

fun runDeleteCategoryById(
    delete: (Int) -> Unit,
    id: Int
) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchDeleteCategoryById(delete, id)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tranzactie(
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
    transaction: TransactionWithCategory,
    id: MutableState<Int>,
    updateStateButtons: () -> Unit,
    updateUpdateId: (Int) -> Unit,
    idUpdateList: MutableState<Int>,
    index: Int,
    idList: Int,
) {
    val modifier = Modifier
        .combinedClickable(
            onClick = { },
            onLongClick = {
                updateStateButtons()
                //Index este idul din cadrul listei de tranzactii pt care
                //vreau sa fac update, deci sa il extrag ca sa il trimit
                //ecranului de ditare
                updateUpdateId(index)
                //idUpdateList stocheaza indexul listei de tranzactii
                idUpdateList.value = idList
                //id.value stocheaza idul efectiv din baza de date pentru stergere
                id.value = transaction.transaction.id
            }
        )
        .padding(
            start = dimensionResource(id = R.dimen.margin),
            end = dimensionResource(id = R.dimen.margin)
        )

    val color: Color =
        if (categoriesA.any { it.name == transaction.category.name })
            colorResource(id = R.color.light_cream_yellow)
        else if (categoriesP.any { it.name == transaction.category.name })
            colorResource(id = R.color.light_cream_red)
        else if (categoriesD.any { it.name == transaction.category.name })
            colorResource(id = R.color.light_cream_blue) else colorResource(id = R.color.light_cream_gray)

    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
        modifier = modifier
    ) {
        Column(modifier = Modifier.background(color)) {
            Text(
                text = "${transaction.category.name} ---> ${transaction.transaction.value} RON", fontSize = 18.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = R.dimen.margin)),
                color = colorResource(R.color.black)
            )
            Text(
                text = "${stringResource(id = R.string.furnizor_sau_beneficiar)} : ${transaction.transaction.payee}",
                maxLines = 2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.margin),
                    top = dimensionResource(id = R.dimen.spacing)
                ),
                color = colorResource(R.color.black)
            )
            Text(
                text = "${stringResource(id = R.string.data)} : ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(transaction.transaction.date)}",
                maxLines = 2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.margin),
                    top = dimensionResource(id = R.dimen.spacing)
                ),
                color = colorResource(R.color.black)
            )
            Text(
                text = "${stringResource(id = R.string.descriere)} : ${transaction.transaction.description}",
                maxLines = 2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.margin),
                    top = dimensionResource(id = R.dimen.spacing)
                ),
                color = colorResource(R.color.black)
            )
        }
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_line)))
}

@Composable
fun TranzactiiLazyColumn(
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
    tranzactii: List<CategoryAndTransactions>,
    navController: NavController,
    summary: Boolean = false,
    updateStateButtons: () -> Unit,
    buttons: Boolean,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit,
    idUpdate: Int,
    modifier: Modifier
) {
    val idDelete: MutableState<Int> = remember { mutableIntStateOf(-1) }
    val idUpdateList: MutableState<Int> = remember { mutableIntStateOf(-1) }

    if (buttons) {
        AlertDialog(
            onDismissRequest = {
                updateStateButtons()
            },
            title = {
                Text(text = stringResource(id = R.string.selectare_actiune))
            },
            text = {
                Text(text = stringResource(id = R.string.mesaj_selectare_actiune),
                    fontSize = fontDimensionResource(id = R.dimen.normal_text_size))
            },
            confirmButton = {
                Button(
                    onClick = {
                        updateStateButtons()
                        val transactionObj = tranzactii[idUpdateList.value].transactions[idUpdate]
                        val gson: Gson = GsonBuilder().create()
                        val transactionJson = gson.toJson(transactionObj)

                        if (!summary) {
                            navController.navigate(
                                "editTransactionScreen/$returnToTransactionIndex?transaction={transaction}"
                                    .replace(oldValue = "{transaction}", newValue = transactionJson)
                            )
                        } else {
                            navController.navigate(
                                "editTransactionScreen/$returnToBudgetSummaryIndex?transaction={transaction}"
                                    .replace(oldValue = "{transaction}", newValue = transactionJson)
                            )
                        }
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.modificare))
                        Icon(
                            Icons.Filled.Colorize, contentDescription = "Delete",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        updateStateButtons()
                        runDeleteCategoryById(deleteById, idDelete.value)
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.stergere))
                        Icon(
                            Icons.Filled.Delete, contentDescription = "Update",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            }
        )
    }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        itemsIndexed(tranzactii) { idList, tranzactie ->
            tranzactie.transactions.forEachIndexed { index, transaction ->
                val c: Categories = tranzactii[idList].category
                val t: Transactions = tranzactie.transactions[index]
                val compoundTransactionWithCategory: TransactionWithCategory = TransactionWithCategory(
                    category = c,
                    transaction = t
                )
                Tranzactie(
                    categoriesA = categoriesA,
                    categoriesP = categoriesP,
                    categoriesD = categoriesD,
                    transaction = compoundTransactionWithCategory,
                    id = idDelete,
                    updateStateButtons = updateStateButtons,
                    updateUpdateId = updateUpdateId,
                    index = index,
                    idList = idList,
                    idUpdateList = idUpdateList
                )
            }
        }
    }
}

@Composable
fun CalendarSummaryTranzactiiLazyColumn(
    navController: NavController,
    tranzactii: List<CategoryAndTransactions>,
    categoriesA: List<Categories>,
    categoriesP: List<Categories>,
    categoriesD: List<Categories>,
    incomesOrExpenses: Boolean,
    updateIncomesExpenses: (Boolean, Boolean) -> Unit,
    deleteById: (Int) -> Unit,
    updateUpdateId: (Int) -> Unit,
    updateButtons: () -> Unit,
    buttons: Boolean,
    date: String,
    idUpdate: Int,
    updateDate: (String) -> Unit
) {
    val modifier: Modifier = Modifier.fillMaxHeight(0.9F)

    val idDelete: MutableState<Int> = remember { mutableIntStateOf(-1) }
    val idUpdateList: MutableState<Int> = remember { mutableIntStateOf(-1) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

        if (incomesOrExpenses) {
            //Coloana venituri
            Text(text = stringResource(id = R.string.Venituri) + " " + date,
                fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream)),
                color = colorResource(R.color.black)
            )

            FadingArrowIcon()

        } else {
            //Coloana cheltuieli
            Text(text = stringResource(id = R.string.Cheltuieli) + " " + date,
                fontSize = fontDimensionResource(id = R.dimen.medium_text_size),
                style = TextStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline),
                modifier = Modifier.background(colorResource(R.color.light_cream)),
                color = colorResource(R.color.black)
            )

            FadingArrowIcon()
        }

        LazyColumn(modifier = modifier.fillMaxWidth()) {
            itemsIndexed(tranzactii) { idList, tranzactie ->
                tranzactie.transactions.forEachIndexed { index, transaction ->
                    val c: Categories = tranzactii[idList].category
                    val t: Transactions = tranzactie.transactions[index]
                    val compoundTransactionWithCategory = TransactionWithCategory(
                        category = c,
                        transaction = t
                    )
                    Tranzactie(
                        categoriesA = categoriesA,
                        categoriesP = categoriesP,
                        categoriesD = categoriesD,
                        transaction = compoundTransactionWithCategory,
                        id = idDelete,
                        updateStateButtons = updateButtons,
                        updateUpdateId = updateUpdateId,
                        index = index,
                        idList = idList,
                        idUpdateList = idUpdateList
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_extra)))

        OkButton(updateIncomesExpenses = updateIncomesExpenses, updateDate = updateDate)
    }

    if (buttons) {
        AlertDialog(
            onDismissRequest = {
                updateButtons()
            },
            title = {
                Text(text = stringResource(id = R.string.selectare_actiune))
            },
            text = {
                Text(text = stringResource(id = R.string.mesaj_selectare_actiune),
                    fontSize = fontDimensionResource(id = R.dimen.normal_text_size))
            },
            confirmButton = {
                Button(
                    onClick = {
                        updateButtons()
                        val transactionObj = tranzactii[idUpdateList.value].transactions[idUpdate]
                        val gson: Gson = GsonBuilder().create()
                        val transactionJson = gson.toJson(transactionObj)

                        navController.navigate(
                            "editTransactionScreen/$returnToCalendarIndex?transaction={transaction}"
                                .replace(oldValue = "{transaction}", newValue = transactionJson)
                        )
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.modificare))
                        Icon(
                            Icons.Filled.Colorize, contentDescription = "Delete",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        updateButtons()
                        runDeleteCategoryById(deleteById, idDelete.value)
                    }
                ) {
                    Row {
                        Text(text = stringResource(id = R.string.stergere))
                        Icon(
                            Icons.Filled.Delete, contentDescription = "Update",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            }
        )
    }
}
