@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Subcategory(
    val name: String,
    val items: MutableList<String>
)

@Composable
private fun antetSubcategory(
    text: String,
    color: Color
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().background(color).padding(16.dp)
    )
}

@Composable
private fun subcategory(
    text: String,
    modifier: Modifier = Modifier
) {
    Text (
        text = text,
        fontSize = 14.sp,
        modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(16.dp)
    )
}

@Composable
fun subcategorysLazyColumn(
    categorii: MutableList<Subcategory>,
    a: Int = -1,
    p: Int = -1,
    d: Int = -1
) {
    val index = remember { mutableStateOf(0) }
    LazyColumn(Modifier.fillMaxHeight(fraction = 700F / LocalConfiguration.current.screenHeightDp).
                        fillMaxWidth(0.8f)) {
        categorii.forEach() {
            subcateg -> this@LazyColumn.stickyHeader{
                if ((a != -1) && (p != -1) && (d != -1)) {
                    val color = when (subcateg) {
                        categorii[a] -> Color(240, 200, 80)
                        categorii[p] -> Color(210, 20, 20)
                        categorii[d] -> Color(40, 105, 200)
                        else -> MaterialTheme.colorScheme.primaryContainer
                    }
                    antetSubcategory(text = subcateg.name, color)
                } else {
                    antetSubcategory(text = subcateg.name, MaterialTheme.colorScheme.primaryContainer)
                }
                index.value += 1
            }
            items(subcateg.items) { text -> subcategory(text = text) }
        }
    }
}

