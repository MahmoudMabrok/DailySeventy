package com.elsharif.dailyseventy

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.elsharif.dailyseventy.util.workmanager.LocationTrackerService
import com.example.core.usecase.GetQuranPageAyaWithTafseerUseCase
import com.example.core.usecase.GetSoraByPageNumberUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import net.time4j.android.ApplicationStarter
import javax.inject.Inject
import androidx.core.net.toUri
import com.elsharif.dailyseventy.domain.azan.prayersnotification.updateAzanChannel

@HiltAndroidApp
class DilayApp : Application(){



    override fun onCreate() {
        super.onCreate()

        // Initialize Time4J
        ApplicationStarter.initialize(this, true)
/*
        // Create notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val locationChannel = NotificationChannel(
                LocationTrackerService.LOCATION_CHANNEL,
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(locationChannel)
            val azanSound = "android.resource://${packageName}/${R.raw.azan}".toUri()

            val azanChannel = NotificationChannel(
                "AZAN_CHANNEL",
                "Azan Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setSound(azanSound, Notification.AUDIO_ATTRIBUTES_DEFAULT)
            }

            notificationManager.createNotificationChannel(azanChannel)
        }*/

        // Create notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createLocationChannel()
            updateAzanChannel(this) // هنا بنربط القناة مع الصوت المحفوظ
        }

        // Use manual entry point if needed
        val entryPoint = EntryPointAccessors.fromApplication(this, AppEntryPoint::class.java)
        UseCaseProvider.init(
            getSora = entryPoint.getSora(),
            getQuran = entryPoint.getQuran()
        )
    }

    @SuppressLint("NewApi")
    private fun createLocationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val locationChannel = NotificationChannel(
            LocationTrackerService.LOCATION_CHANNEL,
            "Location",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(locationChannel)
    }

    companion object {
        private const val CHANNEL_ID = "AZAN_CHANNEL"
        private const val CHANNEL_NAME = "azan channel"
    }
}


@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPoint {
    fun getSora(): GetSoraByPageNumberUseCase
    fun getQuran(): GetQuranPageAyaWithTafseerUseCase
}
/*@HiltAndroidApp
class DilayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Location channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val locationChannel = NotificationChannel(
                LocationTrackerService.LOCATION_CHANNEL,
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(locationChannel)

            // Azan channel
            val azanSound = Uri.parse("android.resource://${packageName}/${R.raw.azan}")
            val azanChannel = NotificationChannel(
                "AZAN_CHANNEL",
                "Azan Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setSound(azanSound, Notification.AUDIO_ATTRIBUTES_DEFAULT)
            }
            notificationManager.createNotificationChannel(azanChannel)
        }

        // Optional: Time4J
        ApplicationStarter.initialize(this, true)
    }
}

* */