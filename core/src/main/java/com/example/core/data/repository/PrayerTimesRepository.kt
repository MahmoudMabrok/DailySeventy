package com.example.core.data.repository

import com.example.core.data.datasource.PrayerTimesDatasource
import com.example.core.domain.prayertiming.DomainPrayerTimingSchool
import java.time.LocalDate

class PrayerTimesRepository(private val datasource: PrayerTimesDatasource) {
    fun getUserLocation() = datasource.getUserLocation()
    fun setUserLocation(lat: Double, lng: Double) = datasource.setUserLocation(lat, lng)

    fun getPrayerTimeAuthorities() = datasource.getPrayerTimeAuthorities()

    fun getCurrentPrayerTimesAuthority() = datasource.getCurrentPrayerTimesAuthority()

    fun setPrayerTimesAuthority(school: DomainPrayerTimingSchool) = datasource.setPrayerTimesAuthority(school)

    fun getPrayerTimes(lat: Double, lng: Double, date: LocalDate, school: DomainPrayerTimingSchool) = datasource.getPrayerTimes(lat,lng,date,school)
}