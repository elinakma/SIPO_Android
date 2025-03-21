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
import androidx.compose.ui.tooling.preview.Preview
import com.example.sipo_reka.ui.screen.SplashScreen
import com.example.sipo_reka.ui.theme.SIPO_RekaTheme
import androidx.compose.runtime.*
import com.example.sipo_reka.ui.screen.LoginScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sipo_reka.ui.screen.ForgotPassword
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SIPO_Reka)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SIPO_RekaTheme {
                val navController = rememberNavController() // Buat NavController
                AppNavigator(navController)
//                Navigation()
//                LoginScreen()
//                MemoSuperadmin()
            }
        }
    }
}

//@Composable
//fun AppNavigator() {
//    var showSplash by remember { mutableStateOf(true) }
//
//    if (showSplash) {
//        SplashScreen {
//            showSplash = false // Setelah Splash, lanjut ke layar utama
//        }
//    } else {
//        LoginScreen()
////        DashboardScreen() // Ganti dengan composable utama setelah splash
//    }
//}

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen {
                navController.navigate("login")
            }
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("forgotPassword") {
            ForgotPassword()
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Text(
            text = "Ini adalah layar utama setelah Splash Screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hai $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SIPO_RekaTheme {
        Greeting("Android")
    }
}