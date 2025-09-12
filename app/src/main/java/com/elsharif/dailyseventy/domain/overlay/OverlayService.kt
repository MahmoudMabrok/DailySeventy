/*
package com.elsharif.dailyseventy.domain.overlay

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.domain.data.sharedpreferences.OverlayPrefs
import com.elsharif.dailyseventy.presentation.home.view.remembrances
import com.elsharif.dailyseventy.presentation.overlay.OverlayContent

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private var composeView: ComposeView? = null
    private val handler = Handler(Looper.getMainLooper())

    private val intervalMs = 5 * 60 * 1000L  // Every 5 minutes
    private val showDurationMs = 10 * 1000L  // Show for 10 seconds each time

    private val showRunnable = object : Runnable {
        override fun run() {
            if (!OverlayPrefs.isEnabled(applicationContext) || !Settings.canDrawOverlays(applicationContext)) {
                stopSelf()
                return
            }
            showOverlayOnce()
            handler.postDelayed(this, intervalMs)
        }
    }

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        startForegroundIfNeeded()
        handler.post(showRunnable) // Start immediately
    }

    @SuppressLint("ForegroundServiceType")
    private fun startForegroundIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "overlay_service_channel"
            val channelName = "Overlay Service"
            val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if (nm.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
                nm.createNotificationChannel(channel)
            }
            val notification: Notification = androidx.core.app.NotificationCompat.Builder(this, channelId)
                .setContentTitle("التذكير العائم مفعل")
                .setContentText("النافذة العائمة تعمل في الخلفية")
                .setSmallIcon(R.drawable.doaa)
                .setOngoing(true)
                .build()
            startForeground(1001, notification)
        }
    }

    private fun showOverlayOnce() {
        if (!Settings.canDrawOverlays(this)) return

        removeOverlayIfExists()

        composeView = ComposeView(applicationContext).apply {
            // Set composition strategy to handle lifecycle properly
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)

            setContent {
                OverlayContent(remembrances)
            }
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                @Suppress("DEPRECATION")
                WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.x = 0
        params.y = 300 // Move down a bit

        try {
            windowManager.addView(composeView, params)
        } catch (e: Exception) {
            composeView = null
            return
        }

        handler.postDelayed({ removeOverlayIfExists() }, showDurationMs)
    }

    private fun removeOverlayIfExists() {
        composeView?.let {
            try { windowManager.removeView(it) } catch (_: Exception) {}
            composeView = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        removeOverlayIfExists()
        try { stopForeground(true) } catch (_: Exception) {}
    }

    override fun onBind(intent: Intent?): IBinder? = null
}*/
