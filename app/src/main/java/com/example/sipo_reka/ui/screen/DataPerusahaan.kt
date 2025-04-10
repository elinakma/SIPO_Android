package com.example.sipo_reka.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import com.example.sipo_reka.ui.superadmin.DetailRow

@Composable
fun DataPerusahaan(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        DataPerusahaanTitle(navController)
        Spacer(modifier = Modifier.height(10.dp))
        DataPerusahaanHeader()
        Spacer(modifier = Modifier.height(4.dp))
        DataPerusahaanFitur()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun DataPerusahaanScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DataPerusahaan(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun DataPerusahaanTitle(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ikon_back),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "DATA PERUSAHAAN",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DataPerusahaanHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_reka1),
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 4.dp)
        )
        Text(
            text = "Jl. Candi Sewu No. 30 , Madiun 63122",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun DataPerusahaanFitur() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFCCC9C9), RoundedCornerShape(8.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFECE8E8))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text("Data Perusahaan", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            CompanyText(label = "Nama Instansi", value = "2.2/REKA/GEN/HR & GA/II/2025")
            CompanyText(label = "Alamat Website", value = "www.ptrekaindo.co.id")
            CompanyText(label = "Telepon", value = "+62 351 4773030")
            CompanyText(label = "Email", value = "sekretariat@ptrekaindo.co.id")
            CompanyText(label = "Alamat", value = "Jl. Candi Sewu No. 30 Madiun, 63122 Indonesia")
        }
    }
}

@Composable
fun CompanyText(label: String, value: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Label
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E4178),
                modifier = Modifier.width(120.dp) // Lebar tetap agar titik dua sejajar
            )
            // Titik dua
            Text(
                text = ":",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E4178),
                modifier = Modifier.width(8.dp) // Jarak titik dua tetap
            )
            // Value
            Text(
                text = value,
                fontSize = 14.sp,
                color = Color(0xFF1E4178)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
