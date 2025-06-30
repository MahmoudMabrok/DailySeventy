package com.elsharif.dailyseventy.di

import com.example.core.usecase.GetCurrentPrayerTimesAuthorityUseCase
import com.example.core.usecase.GetPrayerTimesUseCase
import com.example.core.usecase.GetQuranPageAyaWithTafseerUseCase
import com.example.core.usecase.GetSoraByPageNumberUseCase
import com.example.core.usecase.GetUserLocationUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.time4j.calendar.HijriCalendar
import net.time4j.format.expert.ChronoFormatter

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PrayerTimesEntryPoint {
    fun getUserLocationUseCase(): GetUserLocationUseCase
    fun getCurrentPrayerTimesAuthorityUseCase(): GetCurrentPrayerTimesAuthorityUseCase
    fun getPrayerTimesUseCase(): GetPrayerTimesUseCase
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HijriFormatterEntryPoint {
    fun hijriFormatter(): ChronoFormatter<HijriCalendar>
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WorkerEntryPoint {
    fun getPrayerTimesUseCase(): GetPrayerTimesUseCase
}