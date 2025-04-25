package com.example.sipo_reka.ui.admin

import android.app.Application
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
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sipo_reka.model.Undangan
import com.example.sipo_reka.ui.superadmin.ActionButtons
import com.example.sipo_reka.ui.superadmin.TableCell
import com.example.sipo_reka.ui.superadmin.formatTanggal
import com.example.sipo_reka.viewModel.AuthViewModel
import com.example.sipo_reka.viewModel.AuthViewModelFactory
import com.example.sipo_reka.viewModel.SharedPrefHelper
import com.example.sipo_reka.viewModel.UndanganViewModel

@Composable
fun UndanganAdmin(navController: NavController, undanganViewModel: UndanganViewModel) {
    val undanganList = undanganViewModel.undanganList.value

    LaunchedEffect(Unit) {
        undanganViewModel.fetchUndangan()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)
    ) {
        UndanganTitleAdmin(navController)
        Spacer(modifier = Modifier.height(20.dp))
        UndanganSearchAdmin()
        Spacer(modifier = Modifier.height(10.dp))
        UndanganFiturAdmin()
        Spacer(modifier = Modifier.height(15.dp))
        UndanganTableAdmin(navController, undanganList)
    }
}

@Composable
fun UndanganTitleAdmin(navController: NavController) {
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
                text = "UNDANGAN RAPAT",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically).padding(end = 40.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun UndanganSearchAdmin() {
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
                    textStyle = TextStyle(
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
fun UndanganFiturAdmin() {
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
fun UndanganTableAdmin(navController: NavController, undanganList: List<Undangan>) {
    val sharedPrefHelper = SharedPrefHelper(LocalContext.current)
    val userDivisiId = sharedPrefHelper.getDivisiId()

    val columnHeaders = listOf(
        "No", "Nama Dokumen", "Tanggal Undangan", "Seri", "Dokumen",
        "Tanggal Disahkan", "Divisi", "Status", "Aksi"
    )
    val columnWidths = listOf(50.dp, 150.dp, 120.dp, 80.dp, 150.dp, 120.dp, 120.dp, 100.dp, 150.dp)

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
            undanganList.forEachIndexed { index, undangan ->
                val status = undangan.status
                val statusText = when (status) {
                    "approve" -> "Disetujui"
                    "pending" -> "Diproses"
                    "reject" -> "Ditolak"
                    else -> "Status Tidak Dikenal"
                }
                val statusColor = when (status) {
                    "approve" -> Color(0xFF00B087)
                    "pending" -> Color(0xFFDD9A19)
                    "reject" -> Color(0xFFFF0000)
                    else -> Color.LightGray
                }

                val rowData = listOf(
                    (index + 1).toString(),                                 // No
                    undangan.judul,                                         // Nama Dokumen
                    formatTanggal(undangan.tgl_dibuat),                     // format tanggal didefinisikan functionnya di UndanganSuperadmin
                    undangan.seri_surat ?: "",                              // Seri
                    undangan.nomor_undangan,                                // Dokumen
                    formatTanggal(undangan.tgl_disahkan ?: ""),     // format tanggal didefinisikan functionnya di UndanganSuperadmin
                    undangan.divisi?.nm_divisi ?: ""                        // Divisi
                )

                Row {
                    rowData.forEachIndexed { columnIndex, value ->
                        val textColor = if (columnIndex == 1) statusColor else Color.Black
                        UndanganTableCellAdmin(
                            text = value,
                            width = columnWidths[columnIndex],
                            isHeader = false,
                            isNoColumn = columnIndex == 0,
                            backgroundColor = Color.White,
                            textColor = textColor
                        )
                    }

                    // Kolom Status (warna background)
                    UndanganTableCellAdmin(
                        text = statusText,
                        width = columnWidths[7],
                        isHeader = false,
                        backgroundColor = statusColor,
                        textColor = Color.White
                    )

                    // Kolom Aksi (tombol)
                    UndanganAdminActionButtons(
                        width = columnWidths[8],
                        undanganId = undangan.id_undangan,
                        navController = navController,
                        userDivisiId = userDivisiId,
                        undanganDivisiId = undangan.divisi?.id_divisi,
                        status = undangan.status
                    )
                }
            }
        }
    }
}

@Composable
fun UndanganTableCellAdmin(
    text: String,
    width: Dp,
    isHeader: Boolean,
    isNoColumn: Boolean = false,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
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
fun UndanganAdminActionButtons(
    width: Dp,
    undanganId: Int,
    navController: NavController,
    userDivisiId: Int,
    undanganDivisiId: Int?,
    status: String
) {
    Box(
        modifier = Modifier.width(width).wrapContentHeight().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // tombol kirim
            if (
                undanganDivisiId != null &&
                undanganDivisiId == userDivisiId &&
                (status == "pending" || status == "approve")
            ) {
                IconButton(onClick = {
                    navController.navigate("kirimUndanganAdmin")
                }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Kirim",
                        tint = Color(0xFF5D5FEF),
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
            // tombol arsip
            if (
                (undanganDivisiId == userDivisiId && (status == "approve" || status == "reject")) ||
                (undanganDivisiId != userDivisiId && status == "approve")
            ) {
                IconButton(onClick = {
                    // Logika arsip di sini
                }) {
                    Icon(
                        imageVector = Icons.Default.Archive,
                        contentDescription = "Arsip",
                        tint = Color(0xFF0095FF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            // tombol view
            IconButton(onClick = {navController.navigate("detailUndanganAdmin/$undanganId")}) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "View",
                    tint = Color(0xFF0095FF),
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}