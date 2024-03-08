package com.barbuceanuconstantin.proiectlicenta

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.barbuceanuconstantin.proiectlicenta.R

data class PrincipalScreenComposable(val ctx: Context) {
    private val menuScreensButton = MenuScreensSwipeableTabRows()
    @Composable
    fun principalScreenLayout(modifier: Modifier = Modifier) {
        menuScreensButton.showMenu()
        Column (
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

        }
    }
}
