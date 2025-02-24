package com.elsharif.dailyseventy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Zakker(
    val category :String,
    val  count : String,
    val description : String,
    val reference : String,
    val content : String
)