package com.elsharif.dailyseventy.domain

import android.content.Context
import android.content.SharedPreferences
import org.osmdroid.util.GeoPoint

class AppPreferences(context: Context) {
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    var city: String?
        get() = preferences.getString(CITY_KEY, "cairo")
        set(city) {
            preferences.edit().putString(CITY_KEY, city).apply()
        }
    var country: String?
        get() = preferences.getString(COUNTRY_KEY, "EG")
        set(country) {
            preferences.edit().putString(COUNTRY_KEY, country).apply()
        }
    var method: Int
        get() = preferences.getInt(METHOD_KEY, 5)
        set(method) {
            preferences.edit().putInt(METHOD_KEY, method).apply()
        }
    var currentLocation: GeoPoint
        get() = GeoPoint(
            preferences.getFloat(LAT_KEY, 30.0f).toDouble(),
            preferences.getFloat(LNG_KEY, 30.0f).toDouble()
        )
        set(location) {
            preferences.edit().putFloat(LAT_KEY, location.latitude.toFloat()).apply()
            preferences.edit().putFloat(LNG_KEY, location.longitude.toFloat()).apply()
        }

    companion object {
        private const val FILE_NAME = "PRAYERS_PREF"
        private const val CITY_KEY = "CITY_PREF"
        private const val COUNTRY_KEY = "COUNTRY_PREF"
        private const val METHOD_KEY = "METHOD_PREF"
        private const val LAT_KEY = "LAT_PREF"
        private const val LNG_KEY = "LNG_PREF"
    }
}