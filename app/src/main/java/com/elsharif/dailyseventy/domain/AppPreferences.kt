package com.elsharif.dailyseventy.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.domain.prayertiming.DomainPrayerTiming
import com.example.core.domain.prayertiming.DomainPrayerTimingSchool
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PRAYERS_PREF")

class AppPreferences(private val context: Context) {

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }


    val isFirstTime: Flow<Boolean>  
        get() = context.dataStore.data.map {
            it[IS_FIRST_TIME_STORE_KEY] ?: true
        }
    val method: Flow<DomainPrayerTimingSchool>
        get() = context.dataStore.data.map {
            DomainPrayerTimingSchool(it[METHOD_ID_STORE_KEY] ?: 5, it[METHOD_NAME_STORE_KEY] ?: "")
        }


    val tasbeehCounter: Flow<Int> get() = context.dataStore.data.map { it[TASBEEH_STORE_KEY] ?: 0 }


    val currentLocation: Flow<Pair<Double, Double>>
        get() = context.dataStore.data.map {
            Pair(
                it[LAT_STORE_KEY] ?: 30.0,
                it[LNG_STORE_KEY] ?: 30.0
            )
        }


    suspend fun setMethod(method: DomainPrayerTimingSchool) {
        context.dataStore.edit {
            it[METHOD_ID_STORE_KEY] = method.id
            it[METHOD_NAME_STORE_KEY] = method.name
        }
    }

    suspend fun setLocation(location: Pair<Double, Double>) {
        context.dataStore.edit {
            it[LAT_STORE_KEY] = location.first
            it[LNG_STORE_KEY] = location.second
        }
    }

    fun toggleDarkMode() {
        val isDarkMode = isDarkModeEnabled()
        AppCompatDelegate.setDefaultNightMode(if (!isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        preferences.edit { putBoolean(DARK_MODE_ENABLED, !isDarkMode) }
    }

    suspend fun setTasbeeh(i: Int) {
        context.dataStore.edit {
            it[TASBEEH_STORE_KEY] = i
        }
    }

    suspend fun increaseTasbeeh() {
        context.dataStore.edit {
            Log.d("TAG", "increaseTasbeeh: ${it[TASBEEH_STORE_KEY]}")
            it[TASBEEH_STORE_KEY] = (it[TASBEEH_STORE_KEY] ?: 1) + 1
        }
    }

    suspend fun setIsFirstTime() {
        context.dataStore.edit {
            it[IS_FIRST_TIME_STORE_KEY] = false
        }
    }

    suspend fun updatePrayerTimes(prayers: List<DomainPrayerTiming>) {
        val serialized = Gson().toJson(prayers)
        context.dataStore.edit {
            it[PRAYERS_STORE_KEY] = serialized
        }
    }

    fun getPrayerTimes(): Flow<List<DomainPrayerTiming>> {
        return context.dataStore.data.map {
            it[PRAYERS_STORE_KEY]
        }.map { jsonString ->
            if (jsonString.isNullOrEmpty()) {
                emptyList()
            } else {
                val typeToken = object : TypeToken<List<DomainPrayerTiming>>() {}.type
                Gson().fromJson<List<DomainPrayerTiming>>(jsonString, typeToken)
            }
        }
    }


    fun isDarkModeEnabled(): Boolean = preferences.getBoolean(DARK_MODE_ENABLED, false)

    suspend fun nextQuranPage() {
        context.dataStore.edit {
            it[KHATMA_PAGE] = ((it[KHATMA_PAGE] ?: 1) + 1) % 605
        }
    }

    suspend fun previousQuranPage() {
        context.dataStore.edit {
            it[KHATMA_PAGE] = ((it[KHATMA_PAGE] ?: 1) - 1).coerceAtLeast(1)
        }
    }

    suspend fun setCurrentQuranPage(i: Int) {
        context.dataStore.edit { it[KHATMA_PAGE] = i }
    }

    val currentQuranPage: Flow<Int> = context.dataStore.data.map {
        it[KHATMA_PAGE] ?: 1
    }
















    companion object {
        private const val FILE_NAME = "PRAYERS_PREF"
        private const val CITY_KEY = "CITY_PREF"
        private const val COUNTRY_KEY = "COUNTRY_PREF"
        private const val METHOD_KEY = "METHOD_PREF"
        private const val LAT_KEY = "LAT_PREF"
        private const val LNG_KEY = "LNG_PREF"

        private const val IS_FIRST_TIME = "IS_FIRST_TIME"
        private const val METHOD_ID_KEY = "METHOD_ID_PREF"
        private const val METHOD_NAME_KEY = "METHOD_NAME_PREF"

        private const val TASBEEH_KEY = "TASBEEH"
        private const val DARK_MODE_ENABLED = "DARK_MODE_ENABLED"
        private const val PRAYER_TIMES = "PRAYER_TIMES"
        private val METHOD_ID_STORE_KEY = intPreferencesKey(METHOD_ID_KEY)
        private val METHOD_NAME_STORE_KEY = stringPreferencesKey(METHOD_NAME_KEY)
        private val PRAYERS_STORE_KEY = stringPreferencesKey(PRAYER_TIMES)
        private val LAT_STORE_KEY = doublePreferencesKey(LAT_KEY)
        private val LNG_STORE_KEY = doublePreferencesKey(LNG_KEY)
        private val TASBEEH_STORE_KEY = intPreferencesKey(TASBEEH_KEY)

        private val DARK_MODE_STORE_KEY = booleanPreferencesKey(DARK_MODE_ENABLED)
        private val IS_FIRST_TIME_STORE_KEY = booleanPreferencesKey(IS_FIRST_TIME)
        private val KHATMA_PAGE = intPreferencesKey("KHATMA_PAGE")

    }
}