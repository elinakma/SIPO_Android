package com.example.sipo_reka.ui.superadmin

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sipo_reka.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.sipo_reka.ui.screen.BottomNavBar

// mengatur panjang setiap function dalam dashboard
@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        DashboardHeader(navController)
        Spacer(modifier = Modifier.height(0.dp))
        DashboardBanner()
        Spacer(modifier = Modifier.height(15.dp))
        DashboardOverview()
        Spacer(modifier = Modifier.height(15.dp))
        DashboardMenu(navController)
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun DashboardsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DashboardScreen(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun DashboardHeader(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Menjaga agar elemen sejajar secara vertikal
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_perusahaan),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically // Memastikan ikon dan teks sejajar
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.4f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Mawar",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.End)
                )
                Text(
                    text = "Super Admin",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.width(8.dp)) // Jarak antara teks dan ikon profil

            // Ikon Profil
            Icon(
                painter = painterResource(id = R.drawable.icon_profile),
                contentDescription = "Profile Icon",
                modifier = Modifier.size(40.dp)
                    .clickable {
                        navController.navigate("profile")
                    },
                tint = Color.Unspecified // Ubah warna ikon sesuai desain
            )
        }
    }
}


@Composable
fun DashboardBanner() {
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
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Selamat datang Mawar di ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF00B087))) {
                        append("Sistem Persuratan!")
                    }
                },
                fontSize = 12.sp
            )
            Row {
                Text(
                    text = "Anda login sebagai ",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.offset(y = -3.dp)
                )
                Box(
                    modifier = Modifier
                        .offset(y = -4.dp)
                        .background(Color(0xFFDD9A19), shape = RoundedCornerShape(15.dp)) // Warna oranye
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "Super Admin",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

// mengatur dashboard dari overview
@Composable
fun DashboardOverview() {
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
                .padding(horizontal = 14.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Tinjauan",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = "Tinjau aktivitas dan jumlah surat.",
                color = Color.Black,
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OverviewCard("Memo", "12", R.drawable.ikon_memo)
                OverviewCard("Risalah Rapat", "7", R.drawable.ikon_risalah)
                OverviewCard("Undangan Rapat", "4", R.drawable.ikon_undangan)
            }
        }
    }
}

// mengatur overview card (isi)
@Composable
fun OverviewCard(title: String, count: String, icon: Int) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), // Warna bg biru
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .height(100.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 15.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            // Row ikon dan jumlah
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = title,
                    modifier = Modifier.size(45.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(4.dp)) // Memberi jarak antara ikon dan count
                Text(
                    text = count,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color(0xFF1E4178)
                )
            }
        }
    }
}

// mengatur menu di dashboard
@Composable
fun DashboardMenu(navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(500.dp)
            .wrapContentHeight()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(15.dp), clip = false)
            .border(-15.dp, Color(0xFFCCC9C9), shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Menu",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, top = 20.dp)
            )
            Text(
                text = "Gunakan menu ini untuk mengelola informasi persuratan.",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // isi dari menu card
            val menuItems = listOf(
                Triple("Memo", R.drawable.menu_memo, "memo_screen"),
                Triple("Risalah Rapat", R.drawable.menu_risalah, "risalah_screen"),
                Triple("Undangan Rapat", R.drawable.menu_undangan, "undangan_screen"),
                Triple("Arsip", R.drawable.menu_arsip, "arsip_screen"),
                Triple("Laporan", R.drawable.menu_laporan, "laporan_screen"),
                Triple("Manajemen Pengguna", R.drawable.menu_user, "user_management_screen"),
                Triple("Data Perusahaan", R.drawable.menu_data, "dataPerusahaan"),
                Triple("Info1", R.drawable.menu_info, "detailMemo"),
                Triple("Info2", R.drawable.menu_info, "detailUndangan"),
                Triple("Info3", R.drawable.menu_info, "detailRisalah"),
                Triple("Info4", R.drawable.menu_info, "detailMemoAdmin"),
                Triple("Info5", R.drawable.menu_info, "kirimMemoAdmin"),
                Triple("Info6", R.drawable.menu_info, "detailMemoDiterima"),
                Triple("Info7", R.drawable.menu_info, "detailMemoTerkirim")
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(menuItems) { (menu, icon, route) ->
                    MenuCard(menu, icon, route, navController)
                }
            }

        }
    }
}

// untuk mengatur menuItems
@Composable
fun MenuCard(title: String, icon: Int, route: String, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .clickable { navController.navigate(route) }, // Navigasi saat diklik
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier.size(45.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            color = Color.Black,
            fontSize = 9.sp,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

