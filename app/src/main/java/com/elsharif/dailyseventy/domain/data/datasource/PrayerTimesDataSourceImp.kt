package com.elsharif.dailyseventy.domain.data.datasource

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.domain.AppPreferences
import com.elsharif.dailyseventy.domain.azan.prayermethods.PrayerTimingMethods
import com.elsharif.dailyseventy.domain.azan.prayertimes.PrayerTiming
import com.elsharif.dailyseventy.domain.azan.prayertimes.Timings
import com.elsharif.dailyseventy.domain.remote.PrayerAPI
import com.example.core.data.datasource.PrayerTimesDatasource
import com.example.core.domain.prayertiming.DomainPrayer
import com.example.core.domain.prayertiming.DomainPrayerTiming
import com.example.core.domain.prayertiming.DomainPrayerTimingSchool
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject


private const val TAG = "PrayerTimesDataSourceIm"

class PrayerTimesDataSourceImp @Inject constructor(
    private val preferences: AppPreferences,
    private val api: PrayerAPI,
    @ApplicationContext private val context: Context,
) :
    PrayerTimesDatasource {
    override fun getUserLocation(): Flow<Pair<Double, Double>> = preferences.currentLocation

    override fun setUserLocation(lat: Double, lng: Double): Flow<Boolean> = flow {
        preferences.setLocation(Pair(lat, lng))
        emit(true)
    }

    override fun getPrayerTimeAuthorities(): Flow<List<DomainPrayerTimingSchool>> = flow {
        val data = api.prayerTimesMethods().data!!
        val prayerTimingMethods = PrayerTimingMethods(data)
        Log.d(TAG, "getPrayerTimeAuthorities: $data")

        val prayerTimingSchools = prayerTimingMethods.methods.filterNotNull().map {
            DomainPrayerTimingSchool(it.id, it.name ?: "")
        }

        emit(prayerTimingSchools)
    }

    override fun getCurrentPrayerTimesAuthority(): Flow<DomainPrayerTimingSchool> = preferences.method

    override fun setPrayerTimesAuthority(school: DomainPrayerTimingSchool): Flow<Boolean> = flow {
        preferences.setMethod(school)
        emit(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getPrayerTimes(
        lat: Double,
        lng: Double,
        date: LocalDate,
        school: DomainPrayerTimingSchool
    ): Flow<List<DomainPrayerTiming>> = flow {
        fun convertFromTimings(timings: Timings): List<PrayerTiming> = buildList {
            add(PrayerTiming(R.string.fajr, timings.fajr, R.drawable.fajr))
            add(PrayerTiming(R.string.sun_rise, timings.sunrise, R.drawable.duha))
            add(PrayerTiming(R.string.dhuhr, timings.dhuhr, R.drawable.duhur))
            add(PrayerTiming(R.string.asr, timings.asr, R.drawable.asr))
            add(PrayerTiming(R.string.maghrib, timings.maghrib, R.drawable.maghrib))
            add(PrayerTiming(R.string.isha, timings.isha, R.drawable.isha))
        }

        val response = api.getPrayerTimes(lat, lng, school.id, date.monthValue, date.year)
        val symbols = DecimalFormatSymbols(Locale.US)

        val timings = response.data?.map {
            it.date.gregorian.let {
                "${it?.year}-${
                    DecimalFormat(
                        "00",
                        symbols
                    ).format(it?.month?.number)
                }-${it?.day}"
            } to it.timings
        }
        val prayers = timings?.map { it.first to convertFromTimings(it.second) }
        emit(prayers?.flatMap { prayersWithDate ->
            Log.d(TAG, "getPrayerTimes: ${prayersWithDate.first}")
            prayersWithDate.second.map {
                DomainPrayerTiming(
                    DomainPrayer(
                        context.resources.getResourceName(it.prayerName),
                        context.resources.getResourceName(it.prayerImage)
                    ),
                    it.prayerTime,
                    prayersWithDate.first,
                    lat,
                    lng,
                    school
                )
            }
        } ?: listOf())
    }
}