package com.elsharif.dailyseventy.presentaion.zekr

import com.elsharif.dailyseventy.domain.model.Zakker


data class ZekkrState(
    val azkaar: List<Zakker> = emptyList(),
    val selectedCategory: String? = null
)

