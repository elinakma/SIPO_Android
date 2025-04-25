package com.example.sipo_reka.viewModel

import android.content.Context

// menyimpan data pengguna untuk internal storage dan tetap ada walau app ditutup atau reboot.
class SharedPrefHelper(context: Context) {
    private val prefs = context.getSharedPreferences("sipo_reka_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("token", null)
    }

    // Fungsi untuk menyimpan dan mengambil userId, divisiId, dan role
    fun saveUserDetails(userId: Int, divisiId: Int, role: String) {
        prefs.edit().apply {
            putInt("user_id", userId)
            putInt("divisi_id", divisiId)
            putString("role", role)
            apply()
        }
    }

    fun getUserId(): Int {
        return prefs.getInt("user_id", -1) // return -1 jika tidak ada
    }

    fun getDivisiId(): Int {
        return prefs.getInt("divisi_id", -1)
    }

    fun getRole(): String {
        return prefs.getString("role", "") ?: ""
    }

    fun clearToken() {
        prefs.edit().remove("token").apply()
    }
}