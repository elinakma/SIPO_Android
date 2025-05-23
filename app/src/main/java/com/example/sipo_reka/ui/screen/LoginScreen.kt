package com.example.sipo_reka.ui.screen

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sipo_reka.R
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sipo_reka.data.network.ApiClient
import com.example.sipo_reka.model.LoginRequest
import com.example.sipo_reka.model.LoginResponse
import com.example.sipo_reka.ui.screen.ForgotPassword
import com.example.sipo_reka.viewModel.AuthViewModel
import com.example.sipo_reka.viewModel.AuthViewModelFactory
import com.example.sipo_reka.viewModel.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    Log.d("LoginScreen", "Composisi dimulai")
    val context = LocalContext.current
    val state = authViewModel.loginState

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
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background_login),
                        contentDescription = "Login Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )

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

                        Text(
                            text = "SISTEM INFORMASI PERSURATAN",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input Fields
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val emailState = remember { mutableStateOf("") }
                    val passwordState = remember { mutableStateOf("") }
                    var passwordVisible by remember { mutableStateOf(false) }

                    TextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "User Icon",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        placeholder = { Text("Masukkan email") },
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
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

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.password),
                                contentDescription = "Password Icon",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        placeholder = { Text("Masukkan kata sandi") },
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = {},
                                modifier = Modifier
                                    .padding(start = 8.dp)
                            )
                            Text(
                                "Ingatkan Saya",
                                fontSize = 14.sp
                            )
                        }
                        Text(
                            text = "Lupa Password?",
                            color = Color(0xFF1578FB).copy(alpha = 1f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("forgotPassword")
                                }
                                .padding(end = 25.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Button masuk (login)
                    Button(
                        onClick = {
                            Log.d("LoginScreen", "Tombol login diklik dengan email: ${emailState.value}")
                            // Gunakan coroutine scope di dalam onClick
                            authViewModel.login(emailState.value, passwordState.value)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E4178))
                    ) {
                        Text(
                            "MASUK",
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }

                    // Mengamati loginState dan melakukan navigasi berdasarkan hasilnya
                    when (state) {
                        is LoginState.Loading -> {
                            CircularProgressIndicator(color = Color(0xFF1E4178))
                        }

                        is LoginState.Success -> {
                            val role = state.user.role
                            Toast.makeText(
                                context,
                                "Login berhasil sebagai $role",
                                Toast.LENGTH_LONG
                            ).show()
                            when (role) {
                                "superadmin" -> navController.navigate("dashboard/$role")
                                "admin" -> navController.navigate("dashboard/$role")
                                "manager" -> navController.navigate("dashboard/$role")
                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Role tidak dikenali",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            // Reset login state setelah sukses
                            authViewModel.resetLoginState()
                        }

                        is LoginState.Error -> {
                            // Tampilkan pesan error
                            Toast.makeText(
                                context,
                                "Login gagal: ${state.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}