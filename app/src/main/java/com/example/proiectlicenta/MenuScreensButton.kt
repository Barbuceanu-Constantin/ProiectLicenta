package com.example.proiectlicenta

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

class MenuScreensButton {
    @Composable
    fun showMenu() {
        var expanded by remember { mutableStateOf(false) }
        val list = listOf(
            "Ecran principal",
            "Tranzactii",
            "Categories",
            "Mementouri",
            "Valute",
            "Sumar buget",
            "Calendar",
            "Grafice"
        )
        var selectedItem by remember { mutableStateOf("") }
        var textFilledSize by remember { mutableStateOf(Size.Zero) }
        val icon =  if (expanded) { Icons.Filled.KeyboardArrowUp }
                    else { Icons.Filled.KeyboardArrowDown }

        Column(modifier = Modifier.padding(top = 100.dp)) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        textFilledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Selecteaza ecran") },
                trailingIcon = { Icon(icon, "", Modifier.clickable { expanded = !expanded }) })
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                list.forEach { label ->
                    DropdownMenuItem(onClick = {
                                                    selectedItem = label
                                                    expanded = false
                                               },
                                    text = { Text(text = label) }
                                    )
                }
            }
        }
    }
}