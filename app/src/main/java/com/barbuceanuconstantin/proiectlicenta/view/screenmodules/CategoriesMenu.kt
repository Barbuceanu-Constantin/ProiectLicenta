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
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.ListCategories

@Composable
fun CategoriesMenu(
    updateCategoryNameSimple: (String, String) -> Unit,
    listType: ListCategories,
    lSubcategorys: List<Categories>,
    categoryName: String,
) {

    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var textFilledSize by remember { mutableStateOf(Size.Zero) }
    val icon =  if (expanded1 || expanded2) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown }

    Box(modifier = Modifier.fillMaxWidth().padding(start = dimensionResource(id = R.dimen.margin), end = dimensionResource(id = R.dimen.margin)),
        contentAlignment = Alignment.Center) {
        val filteredOptions = lSubcategorys.filter { it.name.contains(categoryName, ignoreCase = true) }

        OutlinedTextField(
            value = categoryName,
            onValueChange = {
                if (listType == ListCategories.REVENUES)
                    updateCategoryNameSimple(it, "Active")
                if (listType == ListCategories.EXPENSES)
                    updateCategoryNameSimple(it, "Pasive")
                if (listType == ListCategories.DEBT)
                    updateCategoryNameSimple(it, "Datorii")
            },
            modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                textFilledSize = coordinates.size.toSize()
            },
            enabled = true,
            label = { Text(text = stringResource(id = R.string.selectare_categorie)) },
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

        if (filteredOptions.isNotEmpty()) {
            //Aici afiseaza doar rezultatele filtrate
            DropdownMenu(
                expanded = expanded1, onDismissRequest = { expanded1 = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
            ) {
                filteredOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            expanded1 = false
                            if (listType == ListCategories.REVENUES)
                                updateCategoryNameSimple(label.name, "Active")
                            if (listType == ListCategories.EXPENSES)
                                updateCategoryNameSimple(label.name, "Pasive")
                            if (listType == ListCategories.DEBT)
                                updateCategoryNameSimple(label.name, "Datorii")
                        },
                        text = { Text(text = label.name) },
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
                            expanded2 = false
                            if (listType == ListCategories.REVENUES)
                                updateCategoryNameSimple(label.name, "Active")
                            if (listType == ListCategories.EXPENSES)
                                updateCategoryNameSimple(label.name, "Pasive")
                            if (listType == ListCategories.DEBT)
                                updateCategoryNameSimple(label.name, "Datorii")
                        },
                        text = { Text(text = label.name) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}