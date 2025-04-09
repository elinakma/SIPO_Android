package com.example.sipo_reka.ui.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sipo_reka.R

@Composable
fun MemoManager(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        MemoManagerTitle(navController)
        Spacer(modifier = Modifier.height(200.dp))
        MemoDiterimaManager(navController)
        Spacer(modifier = Modifier.height(25.dp))
        MemoTerkirimManager(navController)
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun MemoManagerTitle(navController: NavController) {
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
                text = "MEMO MANAGER",
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
fun MemoDiterimaManager(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp), clip = false)
                .border(1.dp, Color(0xFF1E4178), shape = RoundedCornerShape(20.dp))
                .clickable { navController.navigate("memoDiterima") },
            shape = RoundedCornerShape(20.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ikon_memo),
//                        contentDescription = "Memo Icon",
//                        tint = Color.Unspecified, // Biarkan warna asli ikon
//                        modifier = Modifier
//                            .width(20.dp)
//                            .height(20.dp)
//                    )
                    Spacer(modifier = Modifier.width(15.dp)) // Jarak antara ikon dan teks
                    Text(
                        text = "Memo Diterima",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun MemoTerkirimManager(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp), clip = false)
                .border(1.dp, Color(0xFF1E4178), shape = RoundedCornerShape(20.dp))
                .clickable { navController.navigate("memoTerkirim") },
            shape = RoundedCornerShape(20.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(15.dp)) // Jarak antara ikon dan teks
                    Text(
                        text = "Memo Terkirim",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
