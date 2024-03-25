@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbuceanuconstantin.proiectlicenta.R

data class Subcategory(
    val name: String,
    val items: SnapshotStateList<String>
)

@Composable
private fun antetSubcategory(text: String, color: Color) {
    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold,
         modifier = Modifier.fillMaxWidth().background(color).padding(16.dp)
    )
}

@Composable
private fun subcategory(text: String, onDeleteItem: () -> Unit) {
    Row() {
        Text(text = text, fontSize = 14.sp,
             modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
                                .padding(16.dp).weight(1f)
        )

        IconButton(onClick = onDeleteItem) {
            Icon(Icons.Filled.Delete, contentDescription = "Favorite", tint = colorResource(id = R.color.black))
        }
    }
}

@Composable
fun subcategorysLazyColumn(categorii: MutableList<Subcategory>, a: Int = -1, p: Int = -1, d: Int = -1) {
    val index = remember { mutableStateOf(0) }

    LazyColumn(
        Modifier.fillMaxHeight(700F / LocalConfiguration.current.screenHeightDp).fillMaxWidth(0.8f)
    )
    {
        categorii.forEach() { subcateg ->
            this@LazyColumn.stickyHeader {
                if ((a != -1) && (p != -1) && (d != -1)) {
                    val color = when (subcateg) {
                        categorii[a] -> colorResource(R.color.yellow)
                        categorii[p] -> colorResource(R.color.red)
                        categorii[d] -> colorResource(R.color.blue)
                        else -> MaterialTheme.colorScheme.primaryContainer
                    }
                    antetSubcategory(text = subcateg.name, color)
                } else {
                    antetSubcategory(text = subcateg.name, MaterialTheme.colorScheme.primaryContainer)
                }
                index.value += 1
            }

            items(subcateg.items) { text ->
                subcategory(text = text) {
                    subcateg.items.remove(text)
                }
            }
        }
    }
}
