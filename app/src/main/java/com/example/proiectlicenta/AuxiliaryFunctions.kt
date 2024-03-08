package com.example.proiectlicenta

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.room5_documentatie.R

@Composable
fun selectCategoryItemList(showA: MutableState<Boolean>, showP: MutableState<Boolean>,
                           showD: MutableState<Boolean>, modifier: Modifier
) {
    if (showA.value) {
        Button(onClick = {
            if (showA.value && showP.value && showD.value) {
                showP.value = false
                showD.value = false
            } else if (showA.value && !showP.value && !showD.value) {
                showP.value = true
                showD.value = true
            }
        }, modifier = modifier.height(75.dp)) {
            Text(text = stringResource(id = R.string.active), fontSize = 20.sp)
        }
    }
    if (showP.value) {
        Button(
            onClick = {
                if (showP.value && showA.value && showD.value) {
                    showA.value = false
                    showD.value = false
                } else if (showP.value && !showA.value && !showD.value) {
                    showA.value = true
                    showD.value = true
                }
            },
            modifier = modifier.height(75.dp)
        ) {
            Text(text = stringResource(id = R.string.pasive), fontSize = 20.sp)
        }
    }
    if (showD.value) {
        Button(
            onClick = {
                if (showD.value && showA.value && showP.value) {
                    showA.value = false
                    showP.value = false
                } else if (showD.value && !showA.value && !showP.value) {
                    showA.value = true
                    showP.value = true
                }
            },
            modifier = modifier.height(75.dp)
        ) {
            Text(text = stringResource(id = R.string.datorii), fontSize = 20.sp)
        }
    }
}
@Composable
fun addOrDeleteItem(addButton: MutableState<Boolean>, deleteButton: MutableState<Boolean>) {
    Button(
        onClick = {
            if (!addButton.value) { addButton.value = true }
        },
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Favorite",
            modifier = Modifier.size(20.dp)
        )
    }

    Spacer(modifier = Modifier.width(80.dp))

    Button(
        onClick = {
            if (!deleteButton.value) { deleteButton.value = true }
        },
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Favorite",
            modifier = Modifier.size(20.dp)
        )
    }
}