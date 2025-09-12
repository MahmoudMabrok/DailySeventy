/*
package com.elsharif.dailyseventy.domain.data.sharedpreferences

import android.content.Context
import androidx.core.content.edit

object OverlayPrefs {
    private const val PREFS_NAME = "overlay_prefs"
    private const val KEY_ENABLED = "overlay_enabled"

    fun isEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ENABLED, true) // Default شغال
    }

    fun setEnabled(context: Context, enabled: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit { putBoolean(KEY_ENABLED, enabled) }
    }
}
*/
