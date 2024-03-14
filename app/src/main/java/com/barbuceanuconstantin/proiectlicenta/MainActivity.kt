package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.transactionsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.screen1
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.screen2
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                screen1()
            }
        }
    }
}
