package com.example.sipo_reka.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sipo_reka.data.network.ApiClient
import com.example.sipo_reka.model.LoginRequest
import com.example.sipo_reka.model.UserResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val context: Context) : ViewModel() { // context untuk memanggil SharedPreferences
    private val sharedPrefHelper = SharedPrefHelper(context)
    private var _loginState by mutableStateOf<LoginState>(LoginState.Idle) // variabel mutable internal
    val loginState: LoginState get() = _loginState // variabel publik yang hanya bisa dibaca dari luar

    // Token state
    private val _token = mutableStateOf<String?>(null)
    val token: State<String?> get() = _token

    // Fungsi login untuk meng-handle login logic
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState = LoginState.Loading // Ubah state menjadi Loading

            try {
                val request = LoginRequest(email, password)
                val response = ApiClient.instance.login(request)

                sharedPrefHelper.saveToken("Bearer ${response.token}")
                _loginState = LoginState.Success(response.user) // Ubah state menjadi Success
                _token.value = response.token // Menyimpan token setelah login berhasil
                Log.d("Login", "Token: ${response.token}")

                sharedPrefHelper.saveUserDetails(
                    response.user.id,
                    response.user.divisi_id_divisi,
                    response.user.role // Simpan role yang diterima
                )
                _loginState = LoginState.Success(response.user)
            } catch (e: Exception) {
                _loginState = LoginState.Error(e.localizedMessage ?: "Unknown Error") // Ubah state menjadi Error
            }
        }
    }

    fun resetLoginState() {
        _loginState = LoginState.Idle
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: UserResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}