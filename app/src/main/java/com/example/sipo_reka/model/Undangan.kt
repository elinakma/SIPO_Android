package com.example.sipo_reka.model

data class Undangan(
    val id_undangan: Int,
    val judul: String,
    val tujuan: String?,
    val isi_undangan: String?,
    val tgl_dibuat: String,
    val tgl_disahkan: String?,
    val status: String,
    val pembuat: String?,
    val catatan: String?,
    val lampiran: String?,
    val nomor_undangan: String,
    val nama_bertandatangan: String?,
    val seri_surat: String?,
    val divisi: Divisi?
)

data class Divisi(
    val id_divisi: Int,
    val nm_divisi: String
)

