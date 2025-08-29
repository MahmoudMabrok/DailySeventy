// app/src/main/java/com/elsharif/dailyseventy/domain/nightthird/NightThirdNotifier.kt
package com.elsharif.dailyseventy.domain.nightthird

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.elsharif.dailyseventy.R
import androidx.core.net.toUri

object NightThirdNotifier {
    private const val CHANNEL_ID = "night_third_channel"
    private const val CHANNEL_NAME = "تنبيهات أثلاث الليل"

    @SuppressLint("ServiceCast")
    fun notify(context: Context, title: String, message: String) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val soundUri: Uri =
            "android.resource://${context.packageName}/${R.raw.thirdnightsound}".toUri()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val ch = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                setSound(
                    soundUri,
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                enableVibration(true)
                description = "إشعار يعلن بدء أحد أثلاث الليل"
            }
            nm.createNotificationChannel(ch)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(soundUri) // لأنظمة أقل من O
            .build()

        nm.notify(System.currentTimeMillis().toInt(), notification)
    }
}
