package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.toSize
import com.barbuceanuconstantin.proiectlicenta.R

@Composable
fun CategoriesMenu(lSubcategorys: MutableList<String>,
                   subcategory: String,
                   onSelect: (String) -> Unit) {
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(subcategory) }
    var textFilledSize by remember { mutableStateOf(Size.Zero) }
    val icon =  if (expanded1 || expanded2) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown }

    Box(modifier = Modifier.fillMaxWidth().padding(start = dimensionResource(id = R.dimen.margin), end = dimensionResource(id = R.dimen.margin)),
        contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                textFilledSize = coordinates.size.toSize()
            },
            enabled = true,
            label = { Text(text = stringResource(id = R.string.selectare_subcategory)) },
            trailingIcon = { Icon(icon, "", Modifier.clickable {
                                                                                expanded1 = !expanded1
                                                                                expanded2 = !expanded2
                                                                                }
                                )
                           } ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        val filteredOptions = lSubcategorys.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteredOptions.isNotEmpty()) {
            //Aici afiseaza doar rezultatele filtrate
            DropdownMenu(
                expanded = expanded1, onDismissRequest = { expanded1 = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                filteredOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = label
                            expanded1 = false
                            onSelect(label)
                        },
                        text = { Text(text = label) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        } else {
            //Aici afiseaza toate rezultatele
            DropdownMenu(
                expanded = expanded2, onDismissRequest = { expanded2 = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                lSubcategorys.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = label
                            expanded2 = false
                            onSelect(label)
                        },
                        text = { Text(text = label) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}