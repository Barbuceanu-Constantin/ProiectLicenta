package com.example.proiectlicenta

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.room5_documentatie.R

data class GraphsScreenComposable(val ctx: Context) {
    private var showMenu = mutableStateOf(false)

    private val meniuEcrane = MeniuEcrane()
    @Composable
    fun principalScreenLayout(modifier: Modifier = Modifier) {
        if (showMenu.value) { meniuEcrane.showMenu() }
        Column (
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { showMenu.value = !showMenu.value },
                modifier = modifier.height(100.dp).width(200.dp).padding(top = 20.dp)
            ) { Text(stringResource(R.string.meniu_ecrane)) }
            if (!showMenu.value) {

            }
        }
    }
}
