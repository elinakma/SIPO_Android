package com.example.sipo_reka.ui.arsip

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sipo_reka.R
import com.example.sipo_reka.ui.admin.ActionButtonsCell
import com.example.sipo_reka.ui.superadmin.MemoFitur
import com.example.sipo_reka.ui.superadmin.MemoSearch
import com.example.sipo_reka.ui.superadmin.MemoTable
import com.example.sipo_reka.ui.superadmin.MemoTitle

@Composable
fun MemoArsip(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)
    ) {
        MemoTitleArsip(navController)
        Spacer(modifier = Modifier.height(20.dp))
        MemoSearchArsip()
        Spacer(modifier = Modifier.height(10.dp))
        MemoTableArsip(navController)
    }
}

@Composable
fun MemoTitleArsip(navController: NavController) {
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
                text = "ARSIP MEMO",
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
fun MemoSearchArsip() {
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

@Composable
fun MemoTableArsip(navController: NavController) {
    val tableData = (1..20).map { index ->
        val status = when (index % 3) {
            0 -> "Disetujui"
            1 -> "Diproses"
            else -> "Ditolak"
        }
        listOf(
            index.toString(),
            "Memo Monitoring Resiko Proyek $index",
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
        "No", "Nama Dokumen", "Tanggal Memo", "Seri", "Dokumen",
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
                    MemoTableCell(text = title, width = columnWidths[index], isHeader = true)
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
//                                MemoDeleteButton(width = columnWidths[index])
                                val showArchiveIcon = rowData[7] == "Disetujui"
                                MemoArsipActionButtons(
                                    width = columnWidths[index],
                                    showArchiveIcon = showArchiveIcon,
                                    navController = navController
                                )
                            }
                            index == 7 -> {
                                MemoTableCell(
                                    text = value,
                                    width = columnWidths[index],
                                    isHeader = false,
                                    backgroundColor = statusColor,
                                    textColor = Color.White
                                )
                            }
                            else -> {
                                val textColor = if (index == 1) statusColor else Color.Black
                                MemoTableCell(
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
fun MemoTableCell(
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
fun MemoDeleteButton(width: Dp) {
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

@Composable
fun MemoArsipActionButtons(width: Dp, showArchiveIcon: Boolean, navController: NavController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.width(width).wrapContentHeight().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {/* Tambahkan aksi hapus di sini */ }) {
                Icon(
//                    painter = painterResource(id = R.drawable.ikon_share),
                    imageVector = Icons.Default.Download,
                    contentDescription = "Kirim",
                    tint = Color(0xFF5D5FEF),
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = { /* Tambahkan aksi hapus di sini */ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Hapus",
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {navController.navigate("detailMemoAdmin")}) {
                Icon(
//                    painter = painterResource(id = R.drawable.ikon_view),
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "View",
                    tint = Color(0xFF0095FF),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}