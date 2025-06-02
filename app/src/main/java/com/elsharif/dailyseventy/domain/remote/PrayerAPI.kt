package com.elsharif.dailyseventy.domain.remote

import com.elsharif.dailyseventy.domain.azan.prayermethods.PrayerTimesMethodsResponse
import com.elsharif.dailyseventy.domain.azan.prayertimes.PrayerTimesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PrayerAPI {
    @GET("calendar")
    fun getPrayerTimes(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double,
        @Query("method") method: Int,
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Call<PrayerTimesResponse>

    @get:GET("methods")
    val prayerTimesMethods: Call<PrayerTimesMethodsResponse>
}