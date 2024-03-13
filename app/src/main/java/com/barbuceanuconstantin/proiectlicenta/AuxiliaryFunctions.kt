package com.barbuceanuconstantin.proiectlicenta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
fun resetButtons(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>) {
    showA.value = true
    showP.value = true
    showD.value = true
}
@Composable
fun okButton(selectedItem: String, showMenu: MutableState<Boolean>) {
    Spacer(Modifier.fillMaxHeight(50f / LocalConfiguration.current.screenHeightDp))

    val buttonWidthFraction = 0.3f
    Button(onClick = { showMenu.value = !showMenu.value },
        modifier = Modifier.fillMaxHeight(120f / LocalConfiguration.current.screenHeightDp).fillMaxWidth(buttonWidthFraction)) {
        Text(text = stringResource(id = R.string.ok), fontSize = 20.sp)
    }
}
@Composable
fun headerSelectCategoryOrTransactionWindow(showA: MutableState<Boolean>, showP: MutableState<Boolean>, showD: MutableState<Boolean>) {
    Text(text = stringResource(R.string.mesaj_selectare_categorie_principala),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
    Spacer(Modifier.fillMaxHeight(fraction = 5F / LocalConfiguration.current.screenHeightDp))
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        selectCategoryItemList(showA = showA, showP = showP, showD = showD, shortName = true)
    }
    Spacer(Modifier.fillMaxHeight(fraction = 15F / LocalConfiguration.current.screenHeightDp))
}
@Composable
fun warningNotSelectedCategory() {
    Text(
        text = stringResource(id = R.string.avertisment_neselectare_categorie),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
}

@Composable
fun selectCategoryItemList(showA: MutableState<Boolean>, showP: MutableState<Boolean>,
                           showD: MutableState<Boolean>, shortName: Boolean = false
) {
    val modifier: Modifier = Modifier.fillMaxHeight(fraction = 50F / LocalConfiguration.current.screenHeightDp)

    val containerColor1: Color = Color(70, 10, 110)
    val contentColor1: Color = Color.White
    val containerColor2: Color = Color(220, 190, 245)
    val contentColor2: Color = Color.Black
    val aContainer : Color
    val aContent : Color
    val pContainer : Color
    val pContent : Color
    val dContainer : Color
    val dContent : Color
    if (showA.value && !showP.value && !showD.value) {
        aContainer = containerColor1
        aContent = contentColor1
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor2
        dContent = contentColor2
    } else if (showP.value && !showA.value && !showD.value) {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor1
        pContent = contentColor1
        dContainer = containerColor2
        dContent = contentColor2
    } else if (showD.value && !showA.value && !showP.value) {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor1
        dContent = contentColor1
    } else {
        aContainer = containerColor2
        aContent = contentColor2
        pContainer = containerColor2
        pContent = contentColor2
        dContainer = containerColor2
        dContent = contentColor2
    }

    Button(onClick = {
                        showA.value = true
                        showP.value = false
                        showD.value = false
    }, modifier = modifier, colors = ButtonColors(containerColor = aContainer, contentColor = aContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)) {
        if (!shortName)
            Text(text = stringResource(id = R.string.active), fontSize = 20.sp)
        else
            Text(text = stringResource(id = R.string.prescurtareActive))
    }

    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))

    Button(
        onClick = {
                        showP.value = true
                        showA.value = false
                        showD.value = false
        },
        modifier = modifier,  colors = ButtonColors(containerColor = pContainer, contentColor = pContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)
    ) {
        if (!shortName)
            Text(text = stringResource(id = R.string.pasive), fontSize = 20.sp)
        else
            Text(text = stringResource(id = R.string.prescurtarePasive))
    }

    Spacer(modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp))

    Button(
        onClick = {
                        showD.value = true
                        showA.value = false
                        showP.value = false
        },
        modifier = modifier,   colors = ButtonColors(containerColor = dContainer, contentColor = dContent, disabledContainerColor = Color.Gray, disabledContentColor = Color.Gray)
    ) {
        if (!shortName)
            Text(text = stringResource(id = R.string.datorii), fontSize = 20.sp)
        else
            Text(text = stringResource(id = R.string.prescurtareDatorii))
    }
}
@Composable
fun addOrDeleteItem(addButton: MutableState<Boolean>, deleteButton: MutableState<Boolean>) {
    Button(
        onClick = { if (!addButton.value) { addButton.value = true } },
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.favorite),
            modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp)
                .fillMaxHeight(fraction = 40F / LocalConfiguration.current.screenHeightDp)
        )
    }
    Spacer(modifier = Modifier.fillMaxWidth(fraction = 80F / LocalConfiguration.current.screenWidthDp))
    Button(
        onClick = { if (!deleteButton.value) { deleteButton.value = true } },
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.favorite),
            modifier = Modifier.fillMaxWidth(fraction = 30F / LocalConfiguration.current.screenWidthDp)
                .fillMaxHeight(fraction = 40F / LocalConfiguration.current.screenHeightDp)
        )
    }
}