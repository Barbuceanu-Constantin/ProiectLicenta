package com.barbuceanuconstantin.proiectlicenta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen0
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen1
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen2
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen4
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen6
import com.barbuceanuconstantin.proiectlicenta.view.screen.screen7

//Branch ecrane-individuale//

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                screen0()
            }
        }
    }
}
