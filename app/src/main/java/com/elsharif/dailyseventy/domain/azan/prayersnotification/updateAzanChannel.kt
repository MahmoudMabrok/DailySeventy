package com.elsharif.dailyseventy.domain.azan.prayersnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.net.toUri
import com.elsharif.dailyseventy.domain.data.sharedpreferences.AzanSoundPrefs

fun updateAzanChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val selectedSoundResId = AzanSoundPrefs.loadSelectedSound(context)
        val azanSound: Uri = "android.resource://${context.packageName}/$selectedSoundResId".toUri()

        val azanChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setSound(azanSound, Notification.AUDIO_ATTRIBUTES_DEFAULT)
            enableVibration(true)
            enableLights(true)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(azanChannel)
    }
}

const val CHANNEL_ID = "AZAN_CHANNEL"
const val CHANNEL_NAME = "Azan Channel"
