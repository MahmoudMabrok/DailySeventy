/*
package com.elsharif.dailyseventy.domain.overlay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.elsharif.dailyseventy.domain.data.sharedpreferences.OverlayPrefs

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return

        if ((action == Intent.ACTION_BOOT_COMPLETED || action == Intent.ACTION_USER_PRESENT)
            && OverlayPrefs.isEnabled(context)
            && Settings.canDrawOverlays(context)
        ) {
            val svcIntent = Intent(context, OverlayService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(svcIntent)
            } else {
                context.startService(svcIntent)
            }
        }
    }
}
*/
