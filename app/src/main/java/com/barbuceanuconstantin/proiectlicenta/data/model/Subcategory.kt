@file:OptIn(ExperimentalFoundationApi::class)

package com.barbuceanuconstantin.proiectlicenta.data.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    Text (
       text = text,
       fontSize = 16.sp,
       fontWeight = FontWeight.Bold,
       modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer).padding(16.dp)
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
    categorii: List<Subcategory>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.height(400.dp)) {
        categorii.forEach() {
            subcateg -> this@LazyColumn.stickyHeader{ antetSubcategory(text = subcateg.name) }
            items(subcateg.items) { text -> subcategory(text = text) }
        }
    }
}

