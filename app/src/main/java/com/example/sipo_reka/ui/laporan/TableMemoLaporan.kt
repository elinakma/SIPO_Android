package com.example.sipo_reka.ui.laporan

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp

@Composable
fun TableMemoLaporan(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)
    ) {
        MemoTitleLaporan(navController)
        Spacer(modifier = Modifier.height(20.dp))
        MemoSearchLaporan()
        Spacer(modifier = Modifier.height(15.dp))
        MemoTableLaporan(navController)
    }
}

@Composable
fun MemoTitleLaporan(navController: NavController) {
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
                text = "LAPORAN MEMO",
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
fun MemoSearchLaporan() {
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
//                .fillMaxWidth()
                .width(250.dp)
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

        Spacer(modifier = Modifier.width(10.dp))

        Box(modifier = Modifier.weight(1f)) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                    .width(150.dp)
                    .height(35.dp)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pilih Divisi",
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
                DropdownMenuItem(text = { Text("HR & GA", color = Color.Black) }, onClick = { expanded = false },)
                DropdownMenuItem(text = { Text("Keuangan", color = Color.Black) }, onClick = { expanded = false })
                DropdownMenuItem(text = { Text("QM & SHE (TI dan K3)", color = Color.Black) }, onClick = { expanded = false })
            }
        }
    }
}

@Composable
fun MemoTableLaporan(navController: NavController) {
    val tableData = (1..20).map { index ->
        listOf(
            index.toString(),
            "Memo Monitoring Resiko Proyek $index",
            "01-01-2024",
            "S-00$index",
            "5.5/REKA/GEN/QM & SHE (IT DAN K3)/III/2025",
            "02-01-2024",
            "QM & SHE-TI",
            "Disetujui",
        )
    }

    val columnHeaders = listOf(
        "No", "Nama Dokumen", "Tanggal Memo", "Seri", "Dokumen",
        "Tanggal Disahkan", "Divisi", "Status"
    )
    val columnWidths = listOf(50.dp, 150.dp, 120.dp, 80.dp, 150.dp, 120.dp, 120.dp, 100.dp)

    val scrollStateHorizontal = rememberScrollState()
    val scrollStateVertical = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .horizontalScroll(scrollStateHorizontal)
    ) {
        Column(modifier = Modifier.verticalScroll(scrollStateVertical)) {
            Row(Modifier.fillMaxWidth().background(Color.White)) {
                columnHeaders.forEachIndexed { index, title ->
                    TableCellLaporan(text = title, width = columnWidths[index], isHeader = true)
                }
            }

            tableData.filter { it[7] == "Disetujui" }.forEach { rowData ->
                val status = rowData[7]
                val statusColor = Color(0xFF00B087)

                Row {
                    rowData.forEachIndexed { index, value ->
                        TableCellLaporan(
                            text = value,
                            width = columnWidths[index],
                            isHeader = false,
                            isNoColumn = index == 0,
                            backgroundColor = if (index == 7) statusColor else Color.White,
                            textColor = when (index) {
                                7 -> Color.White // Status
                                1 -> statusColor // Nama Dokumen
                                else -> Color.Black
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TableCellLaporan(
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
