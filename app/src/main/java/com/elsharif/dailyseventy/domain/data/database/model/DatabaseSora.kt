package com.elsharif.dailyseventy.domain.data.database.model

import androidx.room.Entity

@Entity
data class DatabaseSora(
    val soraNumber: Int,
    val startPage: Int,
    val endPage: Int,
    val arabicName: String,
    val englishName: String,
    val ayaCount: Int,
)
