package com.elsharif.dailyseventy.presentation.prayertimes.model

import androidx.annotation.DrawableRes

data class UiPrayerTime(@DrawableRes val iconRes: Int, val name: String, val time: String, val remainingTime:
String)
