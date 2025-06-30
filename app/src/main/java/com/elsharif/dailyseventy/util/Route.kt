package com.elsharif.dailyseventy.util

sealed class Screen(val route: String) {
    object Home : Screen("الأذكار")
    object PrayerTimes : Screen("مواقيت الصلاة")
    object Hijri: Screen("التاريخ الهجري")
    object Qible: Screen("القبلة")
}
