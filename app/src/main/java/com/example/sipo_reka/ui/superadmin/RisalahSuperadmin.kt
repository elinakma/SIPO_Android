package com.example.sipo_reka.ui.superadmin

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sipo_reka.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp

@Composable
fun RisalahSuperadmin(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)
    ) {
        RisalahTitle(navController)
        Spacer(modifier = Modifier.height(20.dp))
        RisalahSearch()
        Spacer(modifier = Modifier.height(10.dp))
        RisalahFitur()
        Spacer(modifier = Modifier.height(15.dp))
        RisalahTable()
    }
}

@Composable
fun RisalahTitle(navController: NavController) {
    Column(
        modifier = Modifier.padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ikon_back),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "RISALAH",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically).padding(end = 40.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RisalahSearch() {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .height(35.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black // Pastikan ikon terlihat
                )
                Spacer(modifier = Modifier.width(5.dp))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RisalahFitur() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val datePickerStateAwal = rememberDatePickerState()
    val datePickerStateAkhir = rememberDatePickerState()
    val showDatePickerAwal = remember { mutableStateOf(false) }
    val showDatePickerAkhir = remember { mutableStateOf(false) }
    var selectedDateAwal by remember { mutableStateOf("Tgl.Awal") }
    var selectedDateAkhir by remember { mutableStateOf("Tgl.Akhir") }

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                    .width(110.dp)
                    .height(35.dp)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status",
                        fontSize = 13.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(color = Color.White),
            ) {
                DropdownMenuItem(text = { Text("Disetujui", color = Color.Black) }, onClick = { expanded = false },)
                DropdownMenuItem(text = { Text("Diproses", color = Color.Black) }, onClick = { expanded = false })
                DropdownMenuItem(text = { Text("Ditolak", color = Color.Black) }, onClick = { expanded = false })
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Filter Tanggal Awal
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                .width(125.dp)
                .height(35.dp)
                .padding(horizontal = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedDateAwal,
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f).padding(0.dp),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { showDatePickerAwal.value = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Tanggal",
                        modifier = Modifier.width(13.dp).height(13.dp).padding(0.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Filter Tanggal Akhir
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                .width(125.dp)
                .height(35.dp)
                .padding(horizontal = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedDateAkhir,
                    fontSize = 12.7.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f).padding(0.dp),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { showDatePickerAkhir.value = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Tanggal",
                        modifier = Modifier.width(13.dp).height(13.dp).padding(0.dp)
                    )
                }
            }
        }
    }
    // Date Picker Dialog Awal
    if (showDatePickerAwal.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerAwal.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePickerAwal.value = false
                        val pickedDate = datePickerStateAwal.selectedDateMillis
                        if (pickedDate != null) {
                            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            selectedDateAwal = dateFormatter.format(Date(pickedDate))
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerAwal.value = false }) {
                    Text("BATAL")
                }
            }
        ) {
            DatePicker(state = datePickerStateAwal)
        }
    }

    // Date Picker Dialog Akhir
    if (showDatePickerAkhir.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerAkhir.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePickerAkhir.value = false
                        val pickedDate = datePickerStateAkhir.selectedDateMillis
                        if (pickedDate != null) {
                            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            selectedDateAkhir = dateFormatter.format(Date(pickedDate))
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerAkhir.value = false }) {
                    Text("BATAL")
                }
            }
        ) {
            DatePicker(state = datePickerStateAkhir)
        }
    }
}

@Composable
fun RisalahTable() {
    val tableData = (1..20).map { index ->
        val status = when (index % 3) {
            0 -> "Disetujui"
            1 -> "Diproses"
            else -> "Ditolak"
        }
        listOf(
            index.toString(),
            "Risalah Rapat Kerja Kajian $index",
            "01-01-2024",
            "S-00$index",
            "5.5/REKA/GEN/QM & SHE (IT DAN K3)/III/2025",
            "02-01-2024",
            "QM & SHE-TI",
            status,
            "Hapus"
        )
    }

    val columnHeaders = listOf(
        "No", "Nama Dokumen", "Tanggal Risalah", "Seri", "Dokumen",
        "Tanggal Disahkan", "Divisi", "Status", "Aksi"
    )
    val columnWidths = listOf(50.dp, 150.dp, 120.dp, 80.dp, 150.dp, 120.dp, 120.dp, 100.dp, 100.dp)

    val scrollStateHorizontal = rememberScrollState()
    val scrollStateVertical = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .horizontalScroll(scrollStateHorizontal)
    ) {
        Column(modifier = Modifier.verticalScroll(scrollStateVertical)) {
            // Header
            Row(Modifier.fillMaxWidth().background(Color.White)) {
                columnHeaders.forEachIndexed { index, title ->
                    TableCell(text = title, width = columnWidths[index], isHeader = true)
                }
            }

            // Data Rows
            tableData.forEach { rowData ->
                val status = rowData[7]
                val statusColor = when (status) {
                    "Disetujui" -> Color(0xFF00B087) // Soft green
                    "Diproses" -> Color(0xFFDD9A19) // Soft yellow
                    "Ditolak" -> Color(0xFFFF0000)  // Soft red
                    else -> Color.LightGray
                }

                Row {
                    rowData.forEachIndexed { index, value ->
                        when {
                            index == rowData.lastIndex -> {
                                UndanganDeleteButtonCell(width = columnWidths[index])
                            }
                            index == 7 -> {
                                UndanganTableCell(
                                    text = value,
                                    width = columnWidths[index],
                                    isHeader = false,
                                    backgroundColor = statusColor,
                                    textColor = Color.White
                                )
                            }
                            else -> {
                                val textColor = if (index == 1) statusColor else Color.Black
                                RisalahTableCell(
                                    text = value,
                                    width = columnWidths[index],
                                    isHeader = false,
                                    isNoColumn = index == 0,
                                    backgroundColor = Color.White,
                                    textColor = textColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RisalahTableCell(
    text: String,
    width: Dp,
    isHeader: Boolean,
    isNoColumn: Boolean = false,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    val context = LocalContext.current
    val imageBitmap = remember {
        BitmapFactory.decodeResource(context.resources, R.drawable.ikon_no)
    }.asImageBitmap()

    Box(
        modifier = Modifier
            .width(width)
            .padding(8.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .fillMaxWidth()
            .drawBehind {
                if (isNoColumn) {
                    val imageWidth = size.width * 0.8f
                    val imageHeight = size.height
                    val offsetX = (size.width - imageWidth) / 2f
                    val offsetY = 0f

                    with(drawContext.canvas) {
                        save()
                        translate(offsetX, offsetY)
                        scale(imageWidth / imageBitmap.width, imageHeight / imageBitmap.height)
                        drawImage(imageBitmap)
                        restore()
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            color = when {
                isHeader -> Color(0xFF878790)
                isNoColumn -> Color.White
                else -> textColor
            },
            fontSize = 14.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RisalahDeleteButtonCell(width: Dp) {
    Box(
        modifier = Modifier
            .width(width)
            .wrapContentHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = { /* Tambahkan aksi hapus di sini */ }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Hapus",
                tint = Color.Red, // Warna ikon hapus
                modifier = Modifier.size(20.dp) // Ukuran ikon
            )
        }
    }
}
