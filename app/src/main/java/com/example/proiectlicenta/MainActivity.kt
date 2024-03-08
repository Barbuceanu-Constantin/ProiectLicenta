package com.example.proiectlicenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proiectlicenta.ui.theme.ProiectLicentaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProiectLicentaTheme {
                val screen = TransactionsScreenComposable(this)
                screen.transactionsLayout()
            }
        }
    }
}
