package com.elsharif.dailyseventy.di


import android.content.Context
import com.elsharif.dailyseventy.domain.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.time4j.android.ApplicationStarter
import net.time4j.calendar.HijriCalendar
import net.time4j.format.expert.ChronoFormatter
import net.time4j.format.expert.PatternType
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VariablesModule {

    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideHijriFormatter(@ApplicationContext context: Context): ChronoFormatter<HijriCalendar> {
        ApplicationStarter.initialize(context, true) // true = prefetch time zone and calendar data

        return ChronoFormatter.setUp(HijriCalendar.family(), Locale.getDefault())
            .addPattern("dd/MMMM/yyyy", PatternType.CLDR)
            .build()
            .withCalendarVariant(HijriCalendar.VARIANT_UMALQURA)
    }
}

