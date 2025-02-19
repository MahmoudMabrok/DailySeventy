package com.elsharif.dailyseventy.domain.repository

import android.content.Context
import com.elsharif.dailyseventy.domain.model.Zakker
import kotlinx.serialization.json.Json

object ZekrRepository {




    var azkaarList: List<Zakker> = listOf()


    fun initAzkar(context: Context): List<Zakker> {
        if (azkaarList.isEmpty()) {
            val zekker =
                   context.assets.open("Azkar.json")
                       .bufferedReader()
                       .use { it.readText() }
            azkaarList = Json.decodeFromString(zekker)
        }
        return azkaarList
    }
    fun getZekrByNamCategory(name: String): Zakker? {
        return azkaarList.find { it.category == name }
    }

}