package com.example.sipo_reka.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.sipo_reka.R

@Composable
fun SplashScreen(onNavigateNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // durasi 2 menit
        onNavigateNext()
    }

    // Tampilan UI Splash Screen
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sipo),
                contentDescription = "Logo Perusahaan",
                modifier = Modifier.size(96.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sistem Informasi Persuratan Online oleh PT. Rekaindo Global Jasa",
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFCCC9C9)
            )
        }
    }
}
