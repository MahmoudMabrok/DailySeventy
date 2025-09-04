package com.elsharif.dailyseventy.domain.data.sharedpreferences

import android.content.Context
import androidx.core.content.edit
import com.elsharif.dailyseventy.R

object AzanSoundPrefs {

    private const val PREF_NAME = "azan_prefs"
    private const val KEY_SELECTED_SOUND = "selected_sound_res_id"

    /**
     * Save selected Azan sound safely
     */
    fun saveSelectedSound(context: Context, soundResId: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit(commit = true) {
            putInt(KEY_SELECTED_SOUND, soundResId)
        }
    }

    /**
     * Load selected Azan sound safely
     * Fallback = Elmola if not set or invalid
     */
    fun loadSelectedSound(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val resId = prefs.getInt(KEY_SELECTED_SOUND, R.raw.elmola)
        return if (isValidResId(context, resId)) resId else R.raw.elmola
    }

    private fun isValidResId(context: Context, resId: Int): Boolean {
        return try {
            context.resources.getResourceName(resId)
            true
        } catch (e: Exception) {
            false
        }
    }
}
