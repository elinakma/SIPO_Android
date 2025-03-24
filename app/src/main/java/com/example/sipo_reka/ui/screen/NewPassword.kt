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
import com.example.sipo_reka.R
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NewPassword(navController: NavController) {
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
                        text = "Kata Sandi Baru",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        color = Color(0xFF1E4178),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                    )

                    Text(
                        text = "Tetapkan kata sandi baru untuk akun Anda sehingga Anda dapat masuk dan mengakses semua fitur.",
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
                        .padding(bottom = 15.dp)
                ) {
                    val passwordState = remember { mutableStateOf("") }
                    var passwordVisible by remember { mutableStateOf(false) }

                    Text(
                        text = "Masukkan Kata Sandi Baru",
                        fontSize = 16.sp,
                        color = Color(0xFF1E4178),
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 5.dp),
                    )

                    TextField(
                        value = passwordState.value,
                        onValueChange = {passwordState.value = it},
                        placeholder = { Text("Masukkan kata sandi baru") },
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Sembunyikan Password" else "Lihat Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                            .height(50.dp)
                            .border(1.dp, Color(0xFFCCC9C9), RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent, // Menghilangkan indicator default
                            unfocusedIndicatorColor = Color.Transparent // Menghilangkan indicator default
                        )
                    )

                    Text(
                        text = "Konfirmasi Kata Sandi",
                        fontSize = 16.sp,
                        color = Color(0xFF1E4178),
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 5.dp),
                    )

                    TextField(
                        value = passwordState.value,
                        onValueChange = {passwordState.value = it},
                        placeholder = { Text("Konfirmasi kata sandi") },
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Sembunyikan Password" else "Lihat Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                            .height(50.dp)
                            .border(1.dp, Color(0xFFCCC9C9), RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent, // Menghilangkan indicator default
                            unfocusedIndicatorColor = Color.Transparent // Menghilangkan indicator default
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            navController.navigate("successPassword")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E4178))
                    ) {
                        Text(
                            "PERBARUI KATA SANDI",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}
