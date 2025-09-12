package com.elsharif.dailyseventy.presentation.prayertimes.model

import com.elsharif.dailyseventy.R


data class AzanSound(
    val name: String,
    val resId: Int
)

val azanSounds = listOf(
    AzanSound("علي الملا", R.raw.elmola),
    AzanSound("أبو العينين", R.raw.aboelenin),
    AzanSound("عبدالباسط عبدالصمد", R.raw.abdelbasset),
    AzanSound("مشاري راشد", R.raw.mosharyfajr)
)
