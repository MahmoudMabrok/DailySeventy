package com.elsharif.dailyseventy.presentaion.zekr

sealed class ZekkrEvent {
    data class SelectCategory(val category: String) : ZekkrEvent()
    object LoadAzkaar : ZekkrEvent()
}