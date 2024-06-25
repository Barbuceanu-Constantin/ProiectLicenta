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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.toSize
import com.barbuceanuconstantin.proiectlicenta.R

@Composable
fun Menu(
    update: (String) -> Unit,
    lChoices: List<String>,
    value: String,
    label: String,
    categories: Boolean = false,
    revenueCategories: List<String> = listOf(),
    expenseCategories: List<String> = listOf(),
    debtCategories: List<String> = listOf()
) {

    var expanded by remember { mutableStateOf(false) }
    var textFilledSize by remember { mutableStateOf(Size.Zero) }
    val icon =  if (expanded) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.margin),
            end = dimensionResource(id = R.dimen.margin)
        ),
        contentAlignment = Alignment.Center) {

        OutlinedTextField(
            value = value,
            onValueChange = {
                update(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFilledSize = coordinates.size.toSize()
                },
            enabled = true,
            label = { Text(text = label) },
            trailingIcon = { Icon(icon, "", Modifier.clickable {
                                                                                expanded = !expanded
                                                                                }
                            )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        if (!categories) {
            DropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                lChoices.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            update(label)
                        },
                        text = { Text(text = label) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        } else {
            DropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                lChoices.forEach { label ->
                    if (expenseCategories.contains(label)) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                update(label)
                            },
                            text = {
                                Text(
                                    text = label,
                                    color = colorResource(id = R.color.dark_red)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else if (revenueCategories.contains(label)) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                update(label)
                            },
                            text = {
                                Text(
                                    text = label,
                                    color = colorResource(id = R.color.strong_yellow)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                update(label)
                            },
                            text = {
                                Text(
                                    text = label,
                                    color = colorResource(id = R.color.blue)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}