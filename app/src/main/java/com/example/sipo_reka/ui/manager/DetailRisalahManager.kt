package com.example.sipo_reka.ui.manager

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import com.example.sipo_reka.ui.screen.BottomNavBar

@Composable
fun DetailRisalahManager(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 150.dp)
            .imePadding()
            .animateContentSize()
    ){
        DetailRisalahManagerTitle(navController)
        Spacer(modifier = Modifier.height(20.dp))
        AgendaDetailRisalahManagerFitur()
        Spacer(modifier = Modifier.height(20.dp))
        StatusDetailRisalahManagerFitur()
        Spacer(modifier = Modifier.height(20.dp))
        InformationDetailRisalahManagerFitur()
        Spacer(modifier = Modifier.height(20.dp))
        PimpinanDetailRisalahManagerFitur()
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun DetailRisalahManagerScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DetailRisalahManager(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun DetailRisalahManagerTitle(navController: NavHostController) {
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
                text = "DETAIL RISALAH RAPAT MANAGER",
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
fun AgendaDetailRisalahManagerFitur() {
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
            Spacer(modifier = Modifier.width(8.dp))
            Text("No Agenda", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowRisalahManager(label = "No Seri", value = "1")
            DetailRowRisalahManager(label = "Diterima", value = "")
        }
    }
}

@Composable
fun StatusDetailRisalahManagerFitur() {
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
            Spacer(modifier = Modifier.width(8.dp))
            Text("Status Surat", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowRisalahManager(label = "Status", value = "Disetujui", isApproved = true)
            DetailRowRisalahManager(label = "Tanggal", value = "8 Januari 2025")
        }
    }
}

@Composable
fun InformationDetailRisalahManagerFitur() {
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
            Text("Informasi Detail Surat", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowRisalahManager(label = "No Surat", value = "2.2/REKA/GEN/HR & GA/II/2025")
            DetailRowRisalahManager(label = "Seri Surat", value = "2")
            DetailRowRisalahManager(label = "Perihal", value = "Risalah Rapat Kajian")
            DetailRowRisalahManager(label = "Tanggal", value = "8 Januari 2025")
            DetailRowRisalahManager(label = "Kepada", value = "Manager Divisi Logistik")
            DetailRowFileRisalahManager()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun DetailRowRisalahManager(label: String, value: String, isApproved: Boolean = false) {
    val backgroundColor = when (value) {
        "Disetujui" -> Color(0xFF4CAF50)
        "Diproses" -> Color(0xFFFFA000)
        "Ditolak" -> Color(0xFFF44336)
        else -> Color.Gray
    }

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
                        .background(backgroundColor)
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
fun DetailRowFileRisalahManager() {
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
        }
    }
}

@Composable
fun PimpinanDetailRisalahManagerFitur() {
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
            Text("Informasi Pimpinan", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DetailRowUndanganManager(label = "Pengesahan", value = "Disetujui", isApproved = true)
        }
    }
}









