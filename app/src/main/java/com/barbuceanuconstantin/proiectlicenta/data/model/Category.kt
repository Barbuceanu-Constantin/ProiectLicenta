@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.di.CategoriesScreenViewModel
import com.barbuceanuconstantin.proiectlicenta.navigation.editCategoryScreenFullPath
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.launchDeleteCategoryByName(
                                                    delete: (String, String) -> Unit,
                                                    name: String,
                                                    main: String
                                                ) = launch {
    delete(name, main)
}

fun runDeleteCategoryByName(
                            delete: (String, String) -> Unit,
                            name: String,
                            main: String
) {
    runBlocking {
        CoroutineScope(Dispatchers.Default).launchDeleteCategoryByName(delete, name, main)
    }
}
@Composable
private fun Subcategory(
    text: String,
    label: String,
    index: Int,
    navController: NavController,
    categorii: List<Categories>,
    deleteByNameCoroutine: (String, String) -> Unit
) {
    val colorA = colorResource(R.color.light_cream_yellow)
    val colorP = colorResource(R.color.light_cream_red)
    val colorD = colorResource(R.color.light_cream_blue)
    var color = colorResource(R.color.light_cream_gray)

    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_line)),
        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.margin),
                                    end = dimensionResource(id = R.dimen.margin))
                            .combinedClickable(
                                        onClick = { },
                                        onLongClick = {
                                                        val categoryObj = categorii[index]
                                                        val gson: Gson = GsonBuilder().create()
                                                        val categoryJson = gson.toJson(categoryObj)
                                                        navController.navigate(
                                                            editCategoryScreenFullPath
                                                            .replace(oldValue = "{category}", newValue = categoryJson)
                                                        )
                                                      },
                                    )
    ) {
        if (label == "Active") color = colorA
        else if (label == "Pasive") color = colorP
        else if (label == "Datorii") color = colorD
        Row(modifier = Modifier.background(color)) {
            Text(
                text = text, fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f)
            )

            IconButton(onClick = {
                runDeleteCategoryByName(deleteByNameCoroutine, text, label)
            }) {
                Icon(
                    Icons.Filled.Delete, contentDescription = "Favorite",
                    tint = colorResource(id = R.color.black)
                )
            }
        }
    }
}

@Composable
fun CategoriesLazyColumn(
    categorii: List<Categories>,
    navController: NavController,
    deleteByNameCoroutine: (String, String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(categorii) { index, subcateg ->
            Subcategory(
                        text = subcateg.name,
                        label = subcateg.mainCategory,
                        index = index,
                        navController = navController,
                        categorii = categorii,
                        deleteByNameCoroutine = deleteByNameCoroutine
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
        }
    }
}
