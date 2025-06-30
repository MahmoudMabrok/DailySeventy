package com.example.core.usecase

import com.example.core.data.repository.PrayerTimesRepository
import com.example.core.domain.prayertiming.DomainPrayerTimingSchool
import java.time.LocalDate


class GetUserLocationUseCase (private val repository: PrayerTimesRepository) {
    operator fun invoke() = repository.getUserLocation()
}

class SetUserLocationUseCase(private val repository: PrayerTimesRepository) {
    operator fun invoke(lat: Double, lng: Double) = repository.setUserLocation(lat,lng)
}

class GetPrayerTimesAuthoritiesUseCase(private val repository: PrayerTimesRepository) {
    operator fun invoke() = repository.getPrayerTimeAuthorities()
}

class GetCurrentPrayerTimesAuthorityUseCase(private val repository: PrayerTimesRepository) {
    operator fun invoke() = repository.getCurrentPrayerTimesAuthority()
}

class SetCurrentPrayerTimesAuthorityUseCase(private val repository: PrayerTimesRepository) {
    operator fun invoke(school: DomainPrayerTimingSchool) = repository.setPrayerTimesAuthority(school)
}

class GetPrayerTimesUseCase(private val repository: PrayerTimesRepository) {
    operator fun invoke(lat: Double, lng: Double, date: LocalDate, school: DomainPrayerTimingSchool) =
        repository.getPrayerTimes(lat, lng, date, school)
}
