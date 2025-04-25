package com.example.sipo_reka.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sipo_reka.data.network.ApiClient
import com.example.sipo_reka.model.Undangan
import kotlinx.coroutines.launch

class UndanganViewModel(application: Application) : AndroidViewModel(application) {
    private val _undanganList = mutableStateOf<List<Undangan>>(emptyList()) // variabel menyimpan list undangan. mutableStateOf, jetpack compose yang jika nilai dr list undangan berubah, ui update otomatis. List<Undangan> artinya daftar data dari data class Undangan. EmptyList = list kosong jika list undangan belum terisi.
    val undanganList: State<List<Undangan>> = _undanganList //versi read-only yang bisa di-observe oleh UI

    private val sharedPrefHelper = SharedPrefHelper(application) // mengambil data di class SharedPrefHelper()
    private val apiService = ApiClient.instance

    // Fungsi untuk mengambil undangan berdasarkan token dan role
    fun fetchUndangan() {
        val token = sharedPrefHelper.getToken() ?: return // ambil data token, jika ada token maka akan lanjut ke function selanjutnya, tetapi jika token tidak ada maka akan berhenti (return)
        val userId = sharedPrefHelper.getUserId() // Ambil data userId dari SharedPreferences
        val divisiId = sharedPrefHelper.getDivisiId() // Ambil data divisiId dari SharedPreferences
        val role = sharedPrefHelper.getRole() // Ambil data role dari SharedPreferences
        Log.d("User Role", role)

        // memanggil api sesuai role dengan auth token
        viewModelScope.launch {
            // jika role superdmin maka panggil getUndanganSuperadmin, jika tidak maka panggil getUndangan
            try {
                val response = if (role == "superadmin") {
                    ApiClient.instance.getUndanganSuperadmin("Bearer $token")
                } else {
                    ApiClient.instance.getUndangan("Bearer $token")
                }
                _undanganList.value = response
            } catch (e: Exception) {
                Log.e("UndanganViewModel", "Error: ${e.localizedMessage}")
            }
        }
    }
    fun deleteUndangan(id: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.instance.deteleUndangan("Bearer $token", id)
                if (response.isSuccessful) {
                    // hapus dari list lokal agar UI update
                    _undanganList.value = _undanganList.value.filter { it.id_undangan != id }
                } else {
                    Log.e("UndanganViewModel", "Gagal hapus: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("UndanganViewModel", "Error hapus: ${e.message}")
            }
        }
    }

}
