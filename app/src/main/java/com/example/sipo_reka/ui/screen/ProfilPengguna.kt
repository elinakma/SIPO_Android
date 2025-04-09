package com.example.sipo_reka.ui.screen

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import com.example.sipo_reka.ui.superadmin.DashboardScreen

@Composable
fun ProfilPengguna(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        ProfilTitle(navController)
        Spacer(modifier = Modifier.height(10.dp))
        ProfilFitur()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun ProfilesScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProfilPengguna(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun ProfilTitle(navController: NavHostController) {
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
                text = "PROFIL PENGGUNA",
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

data class UserProfile(
    val id: String,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val telp: String,
    val password: String,
    val confirmPassword: String,
    val division: String,
    val position: String
)

@Composable
fun ProfilFitur() {
    val profile = UserProfile(
        id = "1",
        email = "azzahraw1@gmail.com",
        username = "Mawar",
        firstName = "Zahra",
        lastName = "Hoho",
        telp = "08123456789",
        password = "password123",
        confirmPassword = "password123",
        division = "Keuangan",
        position = "Staff Keuangan"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(500.dp)
            .wrapContentHeight()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(15.dp), clip = false)
            .border(-15.dp, Color(0xFFCCC9C9), shape = RoundedCornerShape(15.dp)), // Border harus positif
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_profile),
                contentDescription = "Profile Icon",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF3E58C0), shape = RoundedCornerShape(15.dp))
                    .border(1.dp, Color(0xFF3E58C0), shape = RoundedCornerShape(15.dp))
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "Super Admin",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = profile.email,
                color = Color.Black,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Biodata ditampilkan dengan data class
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .align(Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                BiodataText("ID Pengguna : ${profile.id}")
                BiodataText("Email : ${profile.email}")
                BiodataText("Nama Pengguna : ${profile.username}")
                BiodataText("Nama Depan : ${profile.firstName}")
                BiodataText("Nama Belakang : ${profile.lastName}")
                BiodataText("Nomor Telepon : ${profile.telp}")
                BiodataText("Kata sandi : ${profile.password}")
                BiodataText("Konfirmasi Kata Sandi : ${profile.confirmPassword}")
                BiodataText("Divisi : ${profile.division}")
                BiodataText("Posisi : ${profile.position}")
            }
        }
    }
}

@Composable
fun BiodataText(text: String) {
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






