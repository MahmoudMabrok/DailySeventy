package com.elsharif.dailyseventy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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

@HiltAndroidApp
class DilayApp : Application(){



    override fun onCreate() {
        super.onCreate()

        // Initialize Time4J
        ApplicationStarter.initialize(this, true)

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

            val azanChannel = NotificationChannel(
                "AZAN_CHANNEL",
                "Azan Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(azanChannel)
        }

        // Use manual entry point if needed
        val entryPoint = EntryPointAccessors.fromApplication(this, AppEntryPoint::class.java)
        UseCaseProvider.init(
            getSora = entryPoint.getSora(),
            getQuran = entryPoint.getQuran()
        )
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