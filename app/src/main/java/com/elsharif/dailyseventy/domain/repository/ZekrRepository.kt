package com.elsharif.dailyseventy.domain.repository

import android.content.Context
import android.util.Log
import com.elsharif.dailyseventy.domain.model.Zakker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ZekrRepository(private val context: Context) {

    private val _azkaarList = MutableStateFlow<List<Zakker>>(emptyList()) // Holds all data
    val azkaarList: StateFlow<List<Zakker>> get() = _azkaarList

    init {
        loadAzkar() // Load data when repository is initialized
    }

    private fun loadAzkar() {
        try {
            val jsonText = context.assets.open("Azkar.json")
                .bufferedReader()
                .use { it.readText() }

            Log.d("JsonDebug", jsonText)

            // Parse JSON as a map where keys are categories and values are lists of Zakker
            val azkarMap: Map<String, List<Zakker>> = Json.decodeFromString(jsonText)

            // Flatten all azkar into a single list
            val allAzkar = azkarMap.values.flatten()

            _azkaarList.value = allAzkar

        } catch (e: Exception) {
            Log.e("JsonError", "Error parsing JSON: ${e.message}")
        }
    }

    // Fetch azkaar for a specific category
    suspend fun getZekrByCategory(name: String): List<Zakker> {
        return withContext(Dispatchers.IO) {
            _azkaarList.value.filter { it.category == name }
        }
    }
}
