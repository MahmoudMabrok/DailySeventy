package com.example.core.data.datasource

import com.example.core.domain.prayertiming.DomainPrayerTiming
import com.example.core.domain.prayertiming.DomainPrayerTimingSchool
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface PrayerTimesDatasource {
    fun getUserLocation(): Flow<Pair<Double, Double>>
    fun setUserLocation(lat: Double, lng: Double): Flow<Boolean>
    fun getPrayerTimeAuthorities(): Flow<List<DomainPrayerTimingSchool>>
    fun getCurrentPrayerTimesAuthority(): Flow<DomainPrayerTimingSchool>
    fun setPrayerTimesAuthority(school: DomainPrayerTimingSchool): Flow<Boolean>
    fun getPrayerTimes(lat: Double, lng: Double, date: LocalDate, school: DomainPrayerTimingSchool): Flow<List<DomainPrayerTiming>>

}
