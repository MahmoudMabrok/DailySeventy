// app/src/main/java/com/elsharif/dailyseventy/domain/nightthird/NightThirdReceiver.kt
package com.elsharif.dailyseventy.domain.nightthird

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NightThirdReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val part = intent?.getStringExtra("third_name") ?: return
        context ?: return
        NightThirdNotifier.notify(
            context = context,
            title = "تنبيه",
            message = "بدأ $part من الليل 🌙"
        )
    }
}
