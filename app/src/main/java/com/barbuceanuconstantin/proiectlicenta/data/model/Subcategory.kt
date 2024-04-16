@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteActive
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefiniteDatorii
import com.barbuceanuconstantin.proiectlicenta.subcategorysPredefinitePasive

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

@Composable
private fun AntetSubcategory(text: String, color: Color) {
    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold,
         modifier = Modifier
             .fillMaxWidth()
             .background(color)
             .padding(16.dp)
    )
}

@Composable
private fun Subcategory(text: String, a : Boolean, p : Boolean, d : Boolean,
                        onDeleteItem: () -> Unit) {
    val color : Color = if (a) {
        colorResource(R.color.light_cream_yellow)
    } else if (p) {
        colorResource(R.color.light_cream_red)
    } else if (d) {
        colorResource(R.color.light_cream_blue)
    } else {
        colorResource(R.color.light_cream_gray)
    }

    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.ten_dp)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.two_dp),
            colorResource(id = R.color.light_cream)
        )
    ) {
        Row(modifier = Modifier.background(color)) {
            Text(
                text = text, fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f)
            )

            IconButton(onClick = onDeleteItem) {
                Icon(
                    Icons.Filled.Delete, contentDescription = "Favorite",
                    tint = colorResource(id = R.color.black)
                )
            }
        }
    }
}

@Composable
fun SubcategorysLazyColumn(categorii: MutableList<Subcategory>,
                           a: Boolean = false, p: Boolean = false, d: Boolean = false) {

    var copyA : Boolean = a
    var copyP : Boolean = p
    var copyD : Boolean = d

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        categorii.forEach() { subcateg ->
            items(subcateg.items) { text ->
                if (a && p && d) {
                    if (subcategorysPredefiniteActive.any { entry ->
                            entry.value.contains(text)
                    }) {
                        copyA = true
                        copyP = false
                        copyD = false
                    } else if (subcategorysPredefinitePasive.any { entry ->
                            entry.value.contains(text)
                        }) {
                        copyA = false
                        copyP = true
                        copyD = false
                    } else if (subcategorysPredefiniteDatorii.any { entry ->
                            entry.value.contains(text)
                        }) {
                        copyA = false
                        copyP = false
                        copyD = true
                    }
                    Subcategory(text = text, copyA, copyP, copyD) {
                        subcateg.items.remove(text)
                    }
                } else {
                    Subcategory(text = text, a, p, d) {
                        subcateg.items.remove(text)
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five_dp)))
            }
        }
    }
}
