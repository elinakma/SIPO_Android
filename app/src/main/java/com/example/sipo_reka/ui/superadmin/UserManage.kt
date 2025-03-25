package com.example.sipo_reka.ui.superadmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun UserManagementScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Manajemen Pengguna",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Cari pengguna...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        UserTable()
    }
}

@Composable
fun UserTable() {
    val users = listOf(
        User("Zahra hoho", "azzahraw1@gmail.com", "Admin", Color.Blue),
        User("Ellin Akma", "elinakma005@gmail.com", "Supervisor", Color(0xFF673AB7)),
        User("icuttt", "icuticut@gmail.com", "Superadmin", Color(0xFF512DA8)),
        User("Arabiyah", "rezamall@gmail.com", "Admin", Color.Blue)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        users.forEach { user ->
            UserRow(user)
            Divider()
        }
    }
}

@Composable
fun Divider() {
    TODO("Not yet implemented")
}

@Composable
fun UserRow(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.sym_def_app_icon),
            contentDescription = "User Avatar",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = user.name, fontWeight = FontWeight.Bold)
            Text(text = user.email, fontSize = 12.sp, color = Color.Gray)
        }

        Box(
            modifier = Modifier
                .background(user.accessColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(text = user.access, color = Color.White)
        }
    }
}

data class User(val name: String, val email: String, val access: String, val accessColor: Color)
