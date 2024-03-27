package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.barbuceanuconstantin.proiectlicenta.OkButton
import com.barbuceanuconstantin.proiectlicenta.R
@Composable
fun ShowMenuSubcategories(lSubcategorys: MutableList<String>, showMeniuSubcategorys: MutableState<Boolean>, okButton: Boolean = true, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFilledSize by remember { mutableStateOf(Size.Zero) }
    val icon =  if (expanded) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown }

    val fraction = if (okButton) 1f else 0.7f
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            modifier = Modifier.fillMaxWidth(fraction).onGloballyPositioned { coordinates ->
                    textFilledSize = coordinates.size.toSize()
                },
            label = { Text(text = stringResource(id = R.string.selectare_subcategory)) },
            trailingIcon = { Icon(icon, "", Modifier.clickable { expanded = !expanded }) }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false },
                     modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
        ) {
            lSubcategorys.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                    onSelect(label) //
                },
                    text = { Text(text = label) }
                )
            }
        }

        if (okButton)
            OkButton(showMeniuSubcategorys)
    }
}