package com.barbuceanuconstantin.proiectlicenta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen0
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen1
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen2
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen4
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen6
import com.barbuceanuconstantin.proiectlicenta.view.screen.Screen7

//Branch ecrane-individuale//

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                Screen7()
            }
        }
    }
}
