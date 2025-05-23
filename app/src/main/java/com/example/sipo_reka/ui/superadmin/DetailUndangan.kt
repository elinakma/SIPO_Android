package com.example.sipo_reka.ui.superadmin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sipo_reka.R
import com.example.sipo_reka.model.Undangan
import com.example.sipo_reka.ui.screen.BottomNavBar
import com.example.sipo_reka.viewModel.UndanganViewModel

@Composable
fun DetailUndangan(navController: NavController, undanganViewModel: UndanganViewModel, undanganId: Int) {
    val undanganList = undanganViewModel.undanganList.value
    val selectedUndangan = undanganList.find { it.id_undangan == undanganId }

    LaunchedEffect(Unit) {
        undanganViewModel.fetchUndangan()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        DetailUndanganTitle(navController)
        Spacer(modifier = Modifier.height(20.dp))
        selectedUndangan?.let {
            InformationUndanganFitur(it)
            Spacer(modifier = Modifier.height(30.dp))
            DetailUndanganFitur(it)
        } ?: run {
            Text("Data undangan tidak ditemukan.", color = Color.Red)
        }
    }
}

@Composable
fun DetailUndanganScreen(navController: NavController, undanganViewModel: UndanganViewModel, undanganId: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DetailUndangan(navController, undanganViewModel, undanganId)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun DetailUndanganTitle(navController: NavController) {
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
                text = "DETAIL UNDANGAN RAPAT",
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
fun InformationUndanganFitur(undangan: Undangan) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEFF4FA), RoundedCornerShape(8.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB3D5F7))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.detail_memo1),
                contentDescription = "Informasi",
                tint = Color(0xFF1E4178)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Informasi Detail Undangan Rapat", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowUndangan(label = "No Surat", value = undangan.nomor_undangan)
            DetailRowUndangan(label = "Seri Surat", value = undangan.seri_surat ?: "-")
            DetailRowUndangan(label = "Perihal", value = undangan.judul)
            DetailRowUndangan(label = "Tanggal", value = formatTanggal(undangan.tgl_dibuat)) // format tanggal didefinisikan functionnya di UndanganSuperamdin
            DetailRowUndangan(label = "Kepada", value = undangan.tujuan ?: "-")
        }

    }
}

@Composable
fun DetailUndanganFitur(undangan: Undangan) {
    val statusText = when (undangan.status) {
        "approve" -> "Diterima"
        "pending" -> "Diproses"
        "reject" -> "Ditolak"
        else -> "Status Tidak Dikenal"
    }

    val statusColor = when (undangan.status) {
        "approve" -> Color(0xFF4CAF50) // Hijau
        "pending" -> Color(0xFFFFA000) // Kuning
        "reject" -> Color(0xFFF44336) // Merah
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEFF4FA), RoundedCornerShape(8.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB3D5F7))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.detail_memo2),
                contentDescription = "Detail",
                tint = Color(0xFF1E4178)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Detail", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowUndangan(label = "Pembuat", value = undangan.pembuat ?: "-")
            DetailRowUndangan(label = "Status", value = statusText, isApproved = true, statusColor = statusColor)
            DetailRowUndangan(label = "Dibuat Tanggal", value = formatTanggal(undangan.tgl_dibuat)) // format tanggal di definisikan functionnya di UndanganSuperamdin
            DetailRowFileUndangan()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun DetailRowUndangan(label: String, value: String, isApproved: Boolean = false, statusColor: Color = Color.Gray) {
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
            if (isApproved) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(statusColor)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = value,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = Color(0xFF1E4178),
                )
            }
        }
        // Spacer bawah
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun DetailRowFileUndangan() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label
        Text(
            text = "File",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E4178),
            modifier = Modifier.width(120.dp)
        )

        // Titik dua
        Text(
            text = ":",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E4178),
            modifier = Modifier.width(8.dp)
        )

        // Tombol Lihat & Unduh
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* Lihat file */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF0D47A1)),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                modifier = Modifier.height(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Lihat", color = Color.White, fontSize = 12.sp)
            }

            Button(
                onClick = { /* Unduh file */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF0D47A1)),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                modifier = Modifier.height(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Unduh", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

