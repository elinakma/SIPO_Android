package com.example.sipo_reka.ui.admin

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import com.example.sipo_reka.ui.screen.BottomNavBar

@Composable
fun KirimUndanganAdmin(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 150.dp)
            .imePadding()
            .animateContentSize()
    ){
        KirimUndanganAdminTitle(navController)
        Spacer(modifier = Modifier.height(20.dp))
        InformationKirimUndanganAdminFitur()
        Spacer(modifier = Modifier.height(30.dp))
        KirimUndanganAdminFitur()
        Spacer(modifier = Modifier.height(30.dp))
        TindakanKirimUndanganAdmin()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun KirimUndanganAdminScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KirimUndanganAdmin(navController)

        BottomNavBar(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Composable
fun KirimUndanganAdminTitle(navController: NavController) {
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
                text = "KIRIM UNDANGAN RAPAT",
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
fun InformationKirimUndanganAdminFitur() {
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
            KirimRowUndanganAdmin(label = "No Surat", value = "2.2/REKA/GEN/HR & GA/II/2025")
            KirimRowUndanganAdmin(label = "Seri Surat", value = "2")
            KirimRowUndanganAdmin(label = "Perihal", value = "Undangan Rapat Kajian")
            KirimRowUndanganAdmin(label = "Tanggal", value = "8 Januari 2025")
            KirimRowUndanganAdmin(label = "Kepada", value = "Manager Divisi Logistik")
        }
    }
}

@Composable
fun KirimUndanganAdminFitur() {
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
            KirimRowUndanganAdmin(label = "Pembuat", value = "Admin Logistik")
            KirimRowUndanganAdmin(label = "Status", value = "Disetujui", isApproved = true)
            KirimRowFileUndanganAdmin()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun KirimRowUndanganAdmin(label: String, value: String, isApproved: Boolean = false) {
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
fun KirimRowFileUndanganAdmin() {
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
fun TindakanKirimUndanganAdmin() {
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
            Text("Tindakan Selanjutnya", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Isi data
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            DropdownTindakanUndanganAdmin(label = "Posisi Penerima", value = "", items = listOf("Admin Divisi", "Manager"))
            Spacer(modifier = Modifier.height(10.dp))
            DropdownTindakanUndanganAdmin(label = "Divisi Penerima", value = "", items = listOf("HR & GA", "Keuangan", "Logistik $ Gudang", "Pemasaran", "Sekretaris Perusahaan", "MRH", "Teknologi", "Quality Control", "QM & SHE", "PPC"))
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun DropdownTindakanUndanganAdmin(label: String, value: String, items: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(value) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E4178),
                modifier = Modifier.width(120.dp)
            )
            Text(
                text = ":",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E4178),
                modifier = Modifier.width(8.dp)
            )
            Text(
                text = if (selectedText.isEmpty()) "-- Pilih --" else selectedText,
                fontSize = 14.sp,
                color = if (selectedText.isEmpty()) Color.Gray else Color.Black,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = 200.dp, y = 0.dp), // agar overlay dropdown item menu di kanan
                modifier = Modifier.background(color = Color.White)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item, color = Color.Black) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}