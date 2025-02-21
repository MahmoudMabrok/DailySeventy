package com.elsharif.dailyseventy.presentaion.zekr

sealed class ZekkrEvent {
    data class SelectCategory(val category: String) : ZekkrEvent()
    data object LoadAzkaar : ZekkrEvent()
    data class IncreaseCount(val zekrCount: Int) : ZekkrEvent()
}