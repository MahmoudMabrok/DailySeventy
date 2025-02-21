package com.elsharif.dailyseventy.domain.repository

import android.content.Context
import com.elsharif.dailyseventy.domain.model.Zakker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ZekrRepository(private val context: Context) {

    private val _azkaarList = MutableStateFlow<List<Zakker>>(emptyList()) // Holds all data
    val azkaarList: StateFlow<List<Zakker>> get() = _azkaarList

    init {
        loadAzkar() // Load data when repository is initialized
    }

    private fun loadAzkar() {
        if (_azkaarList.value.isEmpty()) {
            val jsonText = context.assets.open("Azkar.json")
                .bufferedReader()
                .use { it.readText() }
            _azkaarList.value = Json.decodeFromString(jsonText)
        }
    }

    // Fetch azkaar for a specific category
    suspend fun getZekrByCategory(name: String): List<Zakker> {
        return withContext(Dispatchers.IO) {
            _azkaarList.value.filter { it.category == name }
        }
    }
}
