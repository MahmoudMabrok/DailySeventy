package com.elsharif.dailyseventy.domain.azan.prayersnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.elsharif.dailyseventy.MainActivity
import com.elsharif.dailyseventy.R

class AzanAlarmReceiver : BroadcastReceiver() {

    private val TAG = "AzanAlarmReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val content = intent.getStringExtra("CONTENT")
        val title = intent.getStringExtra("TITLE")
        val icon = intent.getIntExtra("ICON", R.mipmap.ic_launcher)
        val id = intent.getIntExtra("ID", 0)

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context, id, i,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

        // Your Azan sound in res/raw
        val azanSound = ("android.resource://${context.packageName}/${R.raw.elmola}").toUri()

        sendNotification(context, icon, title, content, azanSound, pendingIntent)
    }

    private fun sendNotification(
        context: Context,
        iconId: Int,
        title: String?,
        content: String?,
        sound: Uri,
        pendingIntent: PendingIntent
    ) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 1️⃣ Create channel first (with sound)
        createNotificationChannel(manager, sound)

        // 2️⃣ Build notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(iconId)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, iconId))
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColorized(true)

        // 3️⃣ For pre-O devices, set sound on builder
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setSound(sound)
        }

        manager.notify(0, builder.build())
    }

    private fun createNotificationChannel(manager: NotificationManager, sound: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val existingChannel = manager.getNotificationChannel(CHANNEL_ID)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            // Delete if sound is different
            if (existingChannel != null && existingChannel.sound != sound) {
                manager.deleteNotificationChannel(CHANNEL_ID)
            }

            // Create new channel if it doesn't exist
            if (manager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    setSound(sound, audioAttributes)
                    enableVibration(true)
                }
                manager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "AZAN_CHANNEL"
        private const val CHANNEL_NAME = "Azan Channel"
    }
}
