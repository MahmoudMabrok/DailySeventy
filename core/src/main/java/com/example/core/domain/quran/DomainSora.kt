package com.example.core.domain.quran

data class DomainSora(
    val soraNumber: Int,
    val startPage: Int,
    val endPage: Int,
    val arabicName: String,
    val englishName: String,
    val ayaCount : Int,
)