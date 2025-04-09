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

@Composable
fun DataPerusahaan(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        DataPerusahaanTitle(navController)
        Spacer(modifier = Modifier.height(6.dp))
        DataPerusahaanHeader()
        Spacer(modifier = Modifier.height(10.dp))
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
                    .align(Alignment.CenterVertically)
                    .padding(end = 40.dp),
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
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
        painter = painterResource(id = R.drawable.logo_perusahaan),
        contentDescription = "Logo",
        modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Jl. Candi Sewu No. 30 , Madiun 63122",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
    }
}

data class CompanyProfile(
    val nama: String,
    val website: String,
    val telepon: String,
    val email: String,
    val alamat: String
)

@Composable
fun DataPerusahaanFitur() {
    val profile = CompanyProfile(
        nama = "PT Rekaindo",
        website = "rekaindo.co.id",
        telepon = "6285123456789",
        email = "rekaindo@gmail.com",
        alamat = "jl. candi sewu"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(500.dp)
            .wrapContentHeight()
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(5.dp), clip = false)
            .border(-10.dp, Color(0xFFCCC9C9), shape = RoundedCornerShape(5.dp)),
        shape = RoundedCornerShape(5.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Data Perusahaan",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .align(Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CompanyText("Nama Instansi : ${profile.nama}")
                CompanyText("Alamat Website : ${profile.website}")
                CompanyText("Telp : ${profile.telepon}")
                CompanyText("Email : ${profile.email}")
                CompanyText("Alamat : ${profile.alamat}")
            }
        }
    }
}

@Composable
fun CompanyText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 13.sp
        )
    }
}
