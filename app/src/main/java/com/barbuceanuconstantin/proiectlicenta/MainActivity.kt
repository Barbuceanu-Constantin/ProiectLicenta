package com.barbuceanuconstantin.proiectlicenta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.showTransactionDialog
import com.barbuceanuconstantin.proiectlicenta.view.screen.transactionsComposableScreen
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.screen1
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.screen2
import com.barbuceanuconstantin.proiectlicenta.view.screenmodules.showMenu

//Branch ecrane-individuale//

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                screen1()
            }
        }
    }
}
