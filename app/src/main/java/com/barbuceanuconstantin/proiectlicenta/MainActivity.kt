package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesScreenComposable
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalScreenComposable
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsScreenComposable
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.MenuScreensSwipeableTabRows

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                val screensMenu = MenuScreensSwipeableTabRows()
                screensMenu.showMenu()
            }
        }
    }
}
