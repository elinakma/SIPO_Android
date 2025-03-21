package com.example.sipo_reka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.sipo_reka.ui.screen.SplashScreen
import com.example.sipo_reka.ui.theme.SIPO_RekaTheme
import androidx.compose.runtime.*
import com.example.sipo_reka.ui.superadmin.DashboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SIPO_Reka)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SIPO_RekaTheme {
                AppNavigator() // Menjalankan navigasi Splash Screen
            }
        }
    }
}

@Composable
fun AppNavigator() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen {
            showSplash = false // Setelah Splash, lanjut ke layar utama
        }
    } else {
        DashboardScreen() // Ganti dengan composable utama setelah splash
    }
}