package com.elsharif.dailyseventy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.elsharif.dailyseventy.util.workmanager.LocationTrackerService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DilayApp:Application(){
    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                LocationTrackerService.LOCATION_CHANNEL,
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

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