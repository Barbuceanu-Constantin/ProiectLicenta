package com.barbuceanuconstantin.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.barbuceanuconstantin.proiectlicenta.navigation.AppNavHost
import com.barbuceanuconstantin.proiectlicenta.navigation.demoScreen
import com.barbuceanuconstantin.proiectlicenta.ui.theme.ProiectLicentaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProiectLicentaTheme {
                val navController = rememberNavController()

                AppNavHost(navController = navController, startDestination = demoScreen)
            }
        }
    }
}
