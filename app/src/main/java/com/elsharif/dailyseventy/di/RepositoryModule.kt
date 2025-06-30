package com.elsharif.dailyseventy.di
import com.example.core.data.datasource.AzkarDatasource
import com.example.core.data.datasource.NamesOfAllahDatasource
import com.example.core.data.datasource.PrayerTimesDatasource
import com.example.core.data.datasource.QuranDatasource
import com.example.core.data.datasource.TasbeehDatasource
import com.example.core.data.repository.AzkarRepository
import com.example.core.data.repository.NamesOfAllahRepository
import com.example.core.data.repository.PrayerTimesRepository
import com.example.core.data.repository.QuranRepository
import com.example.core.data.repository.TasbeehRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provide all repositories using the above datasources
    @Provides
    @Singleton
    fun provideNamesOfAllahRepository(
        datasource: NamesOfAllahDatasource
    ): NamesOfAllahRepository =
        NamesOfAllahRepository(datasource)

    @Provides
    @Singleton
    fun provideTasbeehRepository(
        datasource: TasbeehDatasource
    ): TasbeehRepository =
        TasbeehRepository(datasource)

    @Provides
    @Singleton
    fun providePrayerTimesRepository(
        datasource: PrayerTimesDatasource
    ): PrayerTimesRepository =
        PrayerTimesRepository(datasource)

    @Provides
    @Singleton
    fun provideAzkarRepository(
        datasource: AzkarDatasource
    ): AzkarRepository =
        AzkarRepository(datasource)

    @Provides
    @Singleton
    fun provideQuranRepository(
        datasource: QuranDatasource
    ): QuranRepository =
        QuranRepository(datasource)
}
