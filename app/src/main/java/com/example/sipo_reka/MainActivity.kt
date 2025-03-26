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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sipo_reka.ui.screen.SplashScreen
import com.example.sipo_reka.ui.theme.SIPO_RekaTheme
import com.example.sipo_reka.ui.screen.LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sipo_reka.ui.screen.ForgotPassword
import androidx.navigation.NavHostController
import com.example.sipo_reka.ui.screen.NewPassword
import com.example.sipo_reka.ui.screen.SuccessPassword
import com.example.sipo_reka.ui.screen.VerificationPassword
import com.example.sipo_reka.ui.superadmin.DashboardScreen
import com.example.sipo_reka.ui.superadmin.MemoSuperadmin
import com.example.sipo_reka.ui.superadmin.UserManage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SIPO_Reka)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SIPO_RekaTheme {
                val navController = rememberNavController() // Buat NavController
                AppNavigator(navController)
            }
        }
    }
}

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
            ForgotPassword(navController)
        }
        composable("verificationPassword") {
            VerificationPassword(navController)
        }
        composable("newPassword") {
            NewPassword(navController)
        }
        composable("successPassword") {
            SuccessPassword(navController)
        }
        composable("dashboard") {
            DashboardScreen(navController)
        }

//        Superadmin
        composable("memo_screen") { MemoSuperadmin(navController) }
        composable("user_management_screen") { UserManage(navController) }
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