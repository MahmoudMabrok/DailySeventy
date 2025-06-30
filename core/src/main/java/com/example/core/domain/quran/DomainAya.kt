package com.example.core.domain.quran


data class DomainAya(
    val id: Int,
    val jozz: Int,
    val sora: Int,
    val soraNameEn: String,
    val soraNameAr: String,
    val page: Int,
    val lineStart: Int,
    val lineEnd: Int,
    val ayaNo: Int,
    val ayaText: String,
    val ayaTextEmlaey: String,
)