package com.elsharif.dailyseventy.domain.data.sharedpreferences


import android.annotation.SuppressLint
import android.content.Context

enum class NightThird(val arabic: String) {
    FIRST("الثلث الأول"),
    SECOND("الثلث الثاني"),
    THIRD("الثلث الأخير")
}

object NightThirdPrefs {
    private const val PREF = "night_third_prefs"
    private const val KEY_ENABLED = "enabled"
    private const val KEY_SET = "selected_set"

    @SuppressLint("UseKtx")
    fun saveEnabled(context: Context, enabled: Boolean) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putBoolean(KEY_ENABLED, enabled).apply()
    }

    fun isEnabled(context: Context): Boolean {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getBoolean(KEY_ENABLED, false)
    }

    @SuppressLint("UseKtx")
    fun saveSelection(context: Context, set: Set<NightThird>) {
        val raw = set.map { it.name }.toSet()
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putStringSet(KEY_SET, raw).apply()
    }

    fun getSelection(context: Context): Set<NightThird> {
        val raw = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getStringSet(KEY_SET, emptySet()) ?: emptySet()
        return raw.mapNotNull { runCatching { NightThird.valueOf(it) }.getOrNull() }.toSet()
    }
}
