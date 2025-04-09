package com.example.sipo_reka.ui.superadmin

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.unit.Dp

@Composable
fun UserManage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        UserManageTitle(navController)
        Spacer(modifier = Modifier.height(10.dp))
        UserManageFitur()
        Spacer(modifier = Modifier.height(15.dp))
        UserManageTable()
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun UserManageTitle(navController: NavController) {
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
                text = "MANAJEMEN PENGGUNA",
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
fun UserManageFitur() {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                    .width(115.dp)
                    .height(35.dp)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(color = Color.White),
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "Disetujui",
                            color = Color.Black) },
                    onClick = { expanded = false },
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            "Diproses",
                            color = Color.Black) },
                    onClick = { expanded = false }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            "Ditolak",
                            color = Color.Black) },
                    onClick = { expanded = false }
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(5.dp))
                .width(250.dp)
                .height(35.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
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
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                // Tampilkan ikon Clear jika searchQuery tidak kosong
                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { searchQuery = "" } // Kosongkan input saat diklik
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close, // Ganti dengan ikon silang
                            contentDescription = "Clear",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun UserManageTable() {
    val users = listOf(
        User("Zahra Hoho", "azzahraw1@gmail.com", "Admin", "Keuangan", "Staff Keuangan", "08123456789"),
        User("Ellin Akma", "elinakma005@gmail.com", "Supervisor", "Keuangan", "Staff Keuangan", "08129876543"),
        User("Icuttt", "icuticut@gmail.com", "Superadmin", "Keuangan", "Staff Keuangan", "08121234567"),
        User("Arabiyah", "rezamall@gmail.com", "Admin", "Keuangan", "Staff Keuangan", "08127778889")
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Column {
                // Table Header
                Row(
                    modifier = Modifier
                        .background(Color(0xFFEFF4FA))
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableHeader("Nama / Email", 180.dp)
                    TableHeader("Izin Akses", 150.dp)
                    TableHeader("Divisi", 100.dp)
                    TableHeader("Posisi", 150.dp)
                    TableHeader("No. Telp", 150.dp)
                    TableHeader("Aksi", 80.dp)
                }

                // Table Body
                LazyColumn {
                    itemsIndexed(users) { index, user ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TableCellWithImage(user.name, user.email)
                            TableCell(user.accessLevel, 150.dp)
                            TableCell(user.division, 100.dp)
                            TableCell(user.position, 150.dp)
                            TableCell(user.phone, 150.dp)
                            TableCellWithActions()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TableCellWithImage(name: String, email: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(180.dp) // Lebar lebih besar agar cukup dengan gambar
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_user),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp) // Ukuran gambar
                .padding(end = 8.dp)
        )
        Column {
            Text(
                text = name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = email,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun TableCellWithActions() {
    Row(
        modifier = Modifier.width(80.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* TODO: Implement Delete action */ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
        }
    }
}


@Composable
fun TableHeader(text: String, width: Dp) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Color(0xFF8F9BB3),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .width(width)
            .padding(8.dp)
    )
}

@Composable
fun TableCell(text: String, width: Dp) {
    val borderColor = when (text) {
        "Admin" -> Color(0xFF0095FF)
        "Supervisor" -> Color(0xFF5D5FEF)
        "Superadmin" -> Color(0xFF3E58C0)
        else -> Color.Transparent
    }

    val backgroundColor = when (text) {
        "Admin" -> Color(0xFF0095FF)
        "Supervisor" -> Color(0xFF5D5FEF)
        "Superadmin" -> Color(0xFF3E58C0)
        else -> Color.Transparent
    }

    val borderWidth = width * 0.7f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.width(width)
    ) {
        if (borderColor != Color.Transparent) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(borderWidth)
                    .background(backgroundColor, RoundedCornerShape(8.dp))
                    .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 2.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Text(
                text = text,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

data class User(
    val name: String,
    val email: String,
    val accessLevel: String,
    val division: String,
    val position: String,
    val phone: String
)