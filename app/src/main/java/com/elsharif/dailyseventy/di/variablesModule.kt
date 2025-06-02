package com.elsharif.dailyseventy.di


import android.content.Context
import com.elsharif.dailyseventy.domain.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.time4j.calendar.HijriCalendar
import net.time4j.format.expert.ChronoFormatter
import net.time4j.format.expert.PatternType
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VariablesModule {

    @Provides
    @Singleton
    fun provideAppPreferences(context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    fun provideHijriFormatter(): ChronoFormatter<HijriCalendar> {
        return ChronoFormatter.setUp(HijriCalendar.family(), Locale.getDefault())
            .addPattern("dd/MMMM/yyyy", PatternType.CLDR)
            .build()
            .withCalendarVariant(HijriCalendar.VARIANT_UMALQURA)
    }
}
