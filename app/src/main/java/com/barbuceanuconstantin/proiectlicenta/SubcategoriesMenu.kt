package com.barbuceanuconstantin.proiectlicenta

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.barbuceanuconstantin.proiectlicenta.R

class MeniuSubcategorii {
    @Composable
    fun showMenu(selected: String,
                 lSubcategorii: MutableList<String>,
                 showMeniuSubcategorii: MutableState<Boolean>,
                 onSelect: (String) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        var selectedItem by remember { mutableStateOf("") }
        var textFilledSize by remember { mutableStateOf(Size.Zero) }
        val icon =  if (expanded) { Icons.Filled.KeyboardArrowUp }
        else { Icons.Filled.KeyboardArrowDown }

        Column(
            modifier = Modifier.padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                    textFilledSize = coordinates.size.toSize()
                },
                label = { Text(text = "Selecteaza subcategoria") },
                trailingIcon = { Icon(icon, "", Modifier.clickable { expanded = !expanded }) })
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                lSubcategorii.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedItem = label
                        expanded = false
                        onSelect(label) //
                    },
                        text = { Text(text = label) }
                    )
                }
            }
            okButton(selectedItem, showMeniuSubcategorii)
        }
    }
}