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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

data class Category(
    val name: String
)

private var listaSubcategorysActive = subcategorysPredefiniteActive.toMutableList()
private var listaSubcategorysPasive = subcategorysPredefinitePasive.toMutableList()
private var listaSubcategorysDatorii = subcategorysPredefiniteDatorii.toMutableList()

@Composable
private fun Subcategory(text: String) {
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
                                        onLongClick = { /*  Aici vine logica de update cand
                                                            modifici o categorie*/ },
                                    )
    ) {
        if (listaSubcategorysActive.contains(text)) color = colorA
        else if (listaSubcategorysPasive.contains(text)) color = colorP
        else if (listaSubcategorysDatorii.contains(text)) color = colorD
        Row(modifier = Modifier.background(color)) {
            Text(
                text = text, fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f)
            )

            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.Delete, contentDescription = "Favorite",
                    tint = colorResource(id = R.color.black)
                )
            }
        }
    }
}

@Composable
fun SubcategorysLazyColumn(categorii: MutableList<Category>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(categorii) { subcateg ->
            Subcategory(text = subcateg.name)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thin_line)))
        }
    }
}
