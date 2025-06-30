package com.example.core.domain.quran

data class DomainQuranPage(
    val pageNumber: Int, val soraNameAr: String, val soraNameEn: String, val soraNo: Int,
    val isBookmark: Boolean = false
)
