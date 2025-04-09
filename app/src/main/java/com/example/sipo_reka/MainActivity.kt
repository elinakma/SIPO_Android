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
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.sipo_reka.ui.screen.SplashScreen
import com.example.sipo_reka.ui.theme.SIPO_RekaTheme
import com.example.sipo_reka.ui.screen.LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sipo_reka.ui.screen.ForgotPassword
import androidx.navigation.NavHostController
import com.example.sipo_reka.ui.admin.DashboardAdmin
import com.example.sipo_reka.ui.admin.DashboardsAdmin
import com.example.sipo_reka.ui.admin.DetailMemoAdminScreen
import com.example.sipo_reka.ui.admin.DetailRisalahAdmin
import com.example.sipo_reka.ui.admin.DetailRisalahAdminScreen
import com.example.sipo_reka.ui.admin.DetailUndanganAdminScreen
import com.example.sipo_reka.ui.admin.KirimMemoAdminScreen
import com.example.sipo_reka.ui.admin.KirimRisalahAdmin
import com.example.sipo_reka.ui.admin.KirimRisalahAdminScreen
import com.example.sipo_reka.ui.admin.KirimUndanganAdmin
import com.example.sipo_reka.ui.admin.KirimUndanganAdminScreen
import com.example.sipo_reka.ui.admin.MemoAdmin
import com.example.sipo_reka.ui.admin.RisalahAdmin
import com.example.sipo_reka.ui.admin.UndanganAdmin
import com.example.sipo_reka.ui.arsip.Arsip
import com.example.sipo_reka.ui.arsip.MemoArsip
import com.example.sipo_reka.ui.arsip.RisalahArsip
import com.example.sipo_reka.ui.arsip.UndanganArsip
import com.example.sipo_reka.ui.manager.DashboardManager
import com.example.sipo_reka.ui.manager.DashboardsManager
import com.example.sipo_reka.ui.manager.MemoDiterima
import com.example.sipo_reka.ui.manager.DetailMemoDiterima
import com.example.sipo_reka.ui.manager.DetailMemoDiterimaScreen
import com.example.sipo_reka.ui.manager.DetailMemoTerkirimScreen
import com.example.sipo_reka.ui.manager.DetailRisalahManager
import com.example.sipo_reka.ui.manager.DetailUndanganManager
import com.example.sipo_reka.ui.manager.MemoManager
import com.example.sipo_reka.ui.manager.MemoTerkirim
import com.example.sipo_reka.ui.manager.RisalahManager
import com.example.sipo_reka.ui.manager.UndanganManager
import com.example.sipo_reka.ui.screen.DataPerusahaanScreen
import com.example.sipo_reka.ui.screen.NewPassword
import com.example.sipo_reka.ui.screen.ProfilesScreen
import com.example.sipo_reka.ui.screen.Sementara
import com.example.sipo_reka.ui.screen.SuccessPassword
import com.example.sipo_reka.ui.screen.VerificationPassword
import com.example.sipo_reka.ui.superadmin.DashboardScreen
import com.example.sipo_reka.ui.superadmin.DashboardsScreen
import com.example.sipo_reka.ui.superadmin.DetailMemoScreen
import com.example.sipo_reka.ui.superadmin.DetailRisalahScreen
import com.example.sipo_reka.ui.superadmin.DetailUndanganScreen
import com.example.sipo_reka.ui.superadmin.MemoSuperadmin
import com.example.sipo_reka.ui.superadmin.RisalahSuperadmin
import com.example.sipo_reka.ui.superadmin.UndanganSuperadmin
import com.example.sipo_reka.ui.superadmin.UserManage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SIPO_Reka)

        super.onCreate(savedInstanceState)
        hideSystemUI()
        enableEdgeToEdge()
        setContent {
            SIPO_RekaTheme {
                val navController = rememberNavController() // Buat NavController
                AppNavigator(navController)
            }
        }
    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller?.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        controller?.hide(WindowInsetsCompat.Type.systemBars())
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
        composable("login") { LoginScreen(navController) }
        composable("forgotPassword") { ForgotPassword(navController) }
        composable("verificationPassword") { VerificationPassword(navController) }
        composable("newPassword") { NewPassword(navController) }
        composable("successPassword") { SuccessPassword(navController) }
        composable("dashboard") { DashboardsScreen(navController) }
        composable("profile") { ProfilesScreen(navController) }
        composable("dataPerusahaan") { DataPerusahaanScreen(navController) }
        composable("detailMemo") { DetailMemoScreen(navController) }

        // SEMENTARA
        composable("detailUndangan") { DetailUndanganScreen(navController) }
        composable("detailRisalah") { DetailRisalahScreen(navController) }
        composable("detailMemoDiterima") { DetailMemoDiterimaScreen(navController) }

        // Superadmin
        composable("memo_screen") { MemoSuperadmin(navController) }
        composable("user_management_screen") { UserManage(navController) }
        composable("undangan_screen") { UndanganSuperadmin(navController) }
        composable("risalah_screen") { RisalahSuperadmin(navController) }

        // Arsip
        composable("arsip_screen") { Arsip(navController) }
        composable("memoArsip") { MemoArsip(navController) }
        composable("undanganArsip") { UndanganArsip(navController) }
        composable("risalahArsip") { RisalahArsip(navController) }

        // Manager
        composable("dashboardManager") { DashboardsManager(navController)}
        composable("memoManager") { MemoManager(navController) }
        composable("memoDiterima") { MemoDiterima(navController) }
        composable("memoTerkirim") { MemoTerkirim(navController) }
        composable("detailMemoDiterima") { DetailMemoDiterimaScreen(navController) }
        composable("detailMemoTerkirim") { DetailMemoTerkirimScreen(navController) }
        composable("undanganManager") { UndanganManager(navController) }
        composable("detailUndanganManager") { DetailUndanganManager(navController) }
        composable("risalahManager") { RisalahManager(navController) }
        composable("detailRisalahManager") { DetailRisalahManager(navController) }

        // Admin
        composable("dashboardAdmin") { DashboardsAdmin(navController) }
        // Memo
        composable("memoAdmin") { MemoAdmin(navController) }
        composable("detailMemoAdmin") { DetailMemoAdminScreen(navController) }
        composable("kirimMemoAdmin") { KirimMemoAdminScreen(navController) }
        // Undangan
        composable("undanganAdmin") { UndanganAdmin(navController) }
        composable("detailUndanganAdmin") { DetailUndanganAdminScreen(navController) }
        composable("kirimUndanganAdmin") { KirimUndanganAdminScreen(navController) }
        // Risalah
        composable("risalahAdmin") { RisalahAdmin(navController) }
        composable("detailRisalahAdmin") { DetailRisalahAdminScreen(navController) }
        composable("kirimRisalahAdmin") { KirimRisalahAdminScreen(navController) }

        composable("sementara") { Sementara(navController) }
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