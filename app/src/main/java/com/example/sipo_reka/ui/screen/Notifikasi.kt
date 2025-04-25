package com.example.sipo_reka.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun Notification(navController: NavHostController) {
    val notifikasiDummy = listOf(
        Notifikasi("1", "memo", "Judul Memo", "Isi memo tentang proyek X", "10:15", "2025-04-08", false),
        Notifikasi("2", "risalah", "Judul Risalah", "Hasil rapat hari ini", "13:20", "2025-04-08", true),
        Notifikasi("3", "undangan", "Judul Undangan", "Rapat divisi besok", "08:00", "2025-04-09", false),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        NotificationTitle(navController)
        Spacer(modifier = Modifier.height(10.dp))
        NotificationFitur(notifikasiList = notifikasiDummy)
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun NotificationScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Notification(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun NotificationTitle(navController: NavHostController) {
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
                text = "NOTIFIKASI",
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

data class Notifikasi(
    val id: String,
    val jenis: String, // "memo", "risalah", "undangan"
    val judul: String,
    val deskripsi: String,
    val waktu: String,
    val tanggal: String, // misalnya "2025-02-25"
    val sudahDibaca: Boolean
)

@Composable
fun NotificationFitur(notifikasiList: List<Notifikasi>) {
    val grouped = notifikasiList.groupBy { it.tanggal }

    Column {
        grouped.forEach { (tanggal, items) ->
            Text(
                text = formatTanggal(tanggal), // misal: "25 Februari 2025"
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            items.forEach { notif ->
                NotificationItem(
                    iconRes = getIcon(notif.jenis),
                    title = getJudul(notif.jenis),
                    subtitle = notif.deskripsi,
                    time = notif.waktu,
                    iconColor = getIconColor(notif.jenis),
                    background = if (!notif.sudahDibaca) Color(0xFFEAF4FA) else Color.White
                )
            }
        }
    }
}

@Composable
fun NotificationItem(
    iconRes: Int,
    title: String,
    subtitle: String,
    time: String,
    iconColor: Color,
    background: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(background, shape = RoundedCornerShape(20.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconColor.copy(alpha = 0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Icon",
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }

        Text(
            text = time,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


fun getIcon(jenis: String): Int = when (jenis) {
    "memo" -> R.drawable.notif_memo
    "risalah" -> R.drawable.notif_risalah
    "undangan" -> R.drawable.notif_undangan
    else -> R.drawable.logo_sipo
}

fun getIconColor(jenis: String): Color = when (jenis) {
    "memo" -> Color.Red
    "risalah" -> Color(0xFF4CAF50)
    "undangan" -> Color(0xFF2196F3)
    else -> Color.Gray
}

fun getJudul(jenis: String): String = when (jenis) {
    "memo" -> "Memo Baru"
    "risalah" -> "Risalah Rapat Baru"
    "undangan" -> "Undangan Rapat Baru"
    else -> "Notifikasi"
}

fun formatTanggal(tanggal: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("id", "ID"))

    val date = inputFormat.parse(tanggal)
    return outputFormat.format(date ?: Date())
}

