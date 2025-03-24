package com.example.sipo_reka.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sipo_reka.R

@Composable
fun VerificationPassword(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .wrapContentHeight()
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp), clip = false)
                .border(1.dp, Color(0xFFCCC9C9), shape = RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Background Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_reka),
                            contentDescription = "Logo Reka",
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 0.dp)
                ) {
                    Text(
                        text = "Verifikasi",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        color = Color(0xFF1E4178),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                    )

                    Text(
                        text = "Masukkan kode 4 digit yang Anda terima di email Anda.",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left,
                        color = Color(0xFFCCC9C9)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Input Fields
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val textField1 = remember { mutableStateOf("") }
                    val textField2 = remember { mutableStateOf("") }
                    val textField3 = remember { mutableStateOf("") }
                    val textField4 = remember { mutableStateOf("") }

                    Row (
                        modifier = Modifier
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        listOf(textField1, textField2, textField3, textField4).forEach { textField ->
                            TextField(
                                value = textField.value,
                                onValueChange = { textField.value = it },
                                textStyle = androidx.compose.ui.text.TextStyle(
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                ),
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .border(1.dp, Color(0xFFCCC9C9), RoundedCornerShape(8.dp)),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Timer detik
                    var timeLeft by remember { mutableStateOf(59) }

                    LaunchedEffect(timeLeft) {
                        if (timeLeft > 0) {
                            kotlinx.coroutines.delay(1000L)
                            timeLeft -= 1
                        }
                    }

                    Text(
                        text = "00:${if (timeLeft < 10) "0$timeLeft" else timeLeft}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    Button(
                        onClick = {
                            navController.navigate("newPassword")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E4178))
                    ) {
                        Text(
                            "VERFIKASI",
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }

                    Row (
                        modifier = Modifier
                    ) {
                        Text(
                            text = "Verifikasi jika tidak menerima kode! ",
                            color = Color(0xFF1E4178),
                            fontSize = 10.sp
                        )

                        Text(
                            text = " Kirim ulang",
                            color = Color.Red,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}
