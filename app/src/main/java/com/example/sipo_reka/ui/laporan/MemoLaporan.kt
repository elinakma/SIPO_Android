package com.example.sipo_reka.ui.laporan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sipo_reka.R
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun MemoLaporan(navController: NavHostController) {
    var tanggalAwal by remember { mutableStateOf("") }
    var tanggalAkhir by remember { mutableStateOf("") }
    var showErrorAwal by remember { mutableStateOf(false) }
    var showErrorAkhir by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MemoLaporanTitle(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Input Tanggal Awal dan Akhir
            TanggalAwalFilter(
                tanggalAwal = tanggalAwal,
                onTanggalAwalChange = { tanggalAwal = it },
                showErrorAwal = showErrorAwal,
            )
            Spacer(modifier = Modifier.height(24.dp))

            TanggalAkhirFilter(
                tanggalAkhir = tanggalAkhir,
                onTanggalAkhirChange = { tanggalAkhir = it },
                showErrorAkhir = showErrorAkhir
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Aksi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        tanggalAwal = ""
                        tanggalAkhir = ""
                        showErrorAwal = false
                        showErrorAkhir = false
                    },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                ) {
                    Text("Batal")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val isAwalKosong = tanggalAwal.isBlank()
                        val isAkhirKosong = tanggalAkhir.isBlank()

                        showErrorAwal = isAwalKosong
                        showErrorAkhir = isAkhirKosong

                        if (!isAwalKosong && !isAkhirKosong) {
                            navController.navigate("tableMemoLaporan")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3C88))
                ) {
                    Text("Filter", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MemoLaporanTitle(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ikon_back),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "LAPORAN MEMO",
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
fun TanggalAwalFilter(
    tanggalAwal: String,
    onTanggalAwalChange: (String) -> Unit,
    showErrorAwal: Boolean,
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "${String.format("%02d", dayOfMonth)}-${String.format("%02d", month + 1)}-$year"
                onTanggalAwalChange(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
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
                .background(Color(0x6692C5FF))
                .padding(8.dp)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.detail_memo1),
                contentDescription = "Informasi",
                tint = Color(0xFF1E4178),
                modifier = Modifier
                    .size(16.dp) // <- Ukuran ikon diatur di sini
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tanggal Awal", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Input Tanggal Awal
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            OutlinedTextField(
                value = tanggalAwal,
                onValueChange = {}, // User tidak bisa ketik manual
                readOnly = true, // Field tidak bisa diketik
                enabled = true, // Tetap bisa diklik untuk buka date picker
                placeholder = { Text("Masukkan Tanggal Awal") },
                leadingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Black // ICON jadi hitam
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() },
                isError = showErrorAwal,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1F3C88),
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    errorBorderColor = Color.Red,
                    focusedTextColor = Color.Black, // teks saat aktif
                    unfocusedTextColor = if (tanggalAwal.isNotBlank()) Color.Black else Color.Gray // teks pas blur
                )
            )

            if (showErrorAwal) {
                Text(
                    text = "*Masukkan tanggal awal filter data memo!",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun TanggalAkhirFilter(
    tanggalAkhir: String,
    onTanggalAkhirChange: (String) -> Unit,
    showErrorAkhir: Boolean
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "${String.format("%02d", dayOfMonth)}-${String.format("%02d", month + 1)}-$year"
                onTanggalAkhirChange(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
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
                .background(Color(0x6692C5FF))
                .padding(8.dp)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.detail_memo1),
                contentDescription = "Informasi",
                tint = Color(0xFF1E4178),
                modifier = Modifier
                    .size(16.dp) // <- Ukuran ikon diatur di sini
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tanggal Akhir", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E4178))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Input Tanggal Akhir
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            OutlinedTextField(
                value = tanggalAkhir,
                onValueChange = {},
                readOnly = true,
                enabled = true,
                placeholder = { Text("Masukkan Tanggal Akhir") },
                leadingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Black // ICON jadi hitam
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() },
                isError = showErrorAkhir,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1F3C88),
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    errorBorderColor = Color.Red,
                    focusedTextColor = Color.Black, // teks saat aktif
                    unfocusedTextColor = if (tanggalAkhir.isNotBlank()) Color.Black else Color.Gray // teks pas blur
                )            )
            if (showErrorAkhir) {
                Text(
                    text = "*Masukkan tanggal akhir filter data memo!",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DetailRowMemoAdmin(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = "$label: ", fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}
