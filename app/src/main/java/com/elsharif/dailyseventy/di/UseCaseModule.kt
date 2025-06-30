    package com.elsharif.dailyseventy.di


    import com.example.core.data.repository.PrayerTimesRepository
    import com.example.core.data.repository.QuranRepository
    import com.example.core.usecase.*
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.EntryPoint
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import net.time4j.calendar.HijriCalendar
    import net.time4j.format.expert.ChronoFormatter
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object UseCaseModule {

        @Provides
        @Singleton
        fun provideGetSoraByPageNumberUseCase(repository: QuranRepository): GetSoraByPageNumberUseCase {
            return GetSoraByPageNumberUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetQuranPageAyaWithTafseerUseCase(repository: QuranRepository): GetQuranPageAyaWithTafseerUseCase {
            return GetQuranPageAyaWithTafseerUseCase(repository)
        }

        @Provides
        fun provideGetUserLocationUseCase(repository:PrayerTimesRepository): GetUserLocationUseCase {
            return GetUserLocationUseCase(repository)
        }

        @Provides
        fun provideGetCurrentPrayerTimesAuthorityUseCase(repository: PrayerTimesRepository): GetCurrentPrayerTimesAuthorityUseCase {
            return GetCurrentPrayerTimesAuthorityUseCase(repository)
        }

        @Provides
        fun provideGetPrayerTimesUseCase(repository: PrayerTimesRepository): GetPrayerTimesUseCase {
            return GetPrayerTimesUseCase(repository)
        }

        @Provides
        fun provideSetUserLocationUseCase(
            repository: PrayerTimesRepository
        ): SetUserLocationUseCase {
            return SetUserLocationUseCase(repository)
        }

        @Provides
        fun provideGetPrayerTimesAuthoritiesUseCase(
            repository: PrayerTimesRepository
        ): GetPrayerTimesAuthoritiesUseCase {
            return GetPrayerTimesAuthoritiesUseCase(repository)
        }

        @Provides
        fun provideSetCurrentPrayerTimesAuthorityUseCase(
            repository: PrayerTimesRepository
        ): SetCurrentPrayerTimesAuthorityUseCase {
            return SetCurrentPrayerTimesAuthorityUseCase(repository)
        }   

        // You can add more use cases here if needed
    }
