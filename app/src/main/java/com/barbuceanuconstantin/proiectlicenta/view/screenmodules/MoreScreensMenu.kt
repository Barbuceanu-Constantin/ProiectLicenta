package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RememberMe
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.R

@Composable
fun MoreScreensMenu(onNavigateToBudgetSummaryScreen: () -> Unit,
                    onNavigateToCalendarScreen: () -> Unit,
                    onNavigateToGraphsScreen: () -> Unit) {
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        val string1: CharSequence = stringResource(R.string.sumar_buget)
        val string2: CharSequence = stringResource(R.string.grafice)
        val string3: CharSequence = stringResource(R.string.mementouri)
        val string4: CharSequence = stringResource(R.string.calendar)

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = stringResource(id = R.string.sumar_buget)
                    )
                },
                text = { Text(text = stringResource(id = R.string.sumar_buget)) },
                onClick = {
                            Toast.makeText(context, string1, Toast.LENGTH_SHORT).show()
                            onNavigateToBudgetSummaryScreen()
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AutoGraph,
                        contentDescription = stringResource(id = R.string.grafice)
                    )
                },
                text = { Text(text = stringResource(id = R.string.grafice)) },
                onClick = {
                            Toast.makeText(context, string2, Toast.LENGTH_SHORT).show()
                            onNavigateToGraphsScreen()
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.RememberMe,
                        contentDescription = stringResource(id = R.string.mementouri)
                    )
                },
                text = { Text(text = stringResource(id = R.string.mementouri)) },
                onClick = { Toast.makeText(context, string3, Toast.LENGTH_SHORT).show() }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = stringResource(id = R.string.calendar)
                    )
                },
                text = { Text(text = stringResource(id = R.string.calendar)) },
                onClick = {
                    Toast.makeText(context, string4, Toast.LENGTH_SHORT).show()
                    onNavigateToCalendarScreen()
                }
            )
        }
    }
}