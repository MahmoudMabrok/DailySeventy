package com.elsharif.dailyseventy.domain.zekr

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

object ZekrWorkUtil {

    fun scheduleRepeatingZekr(
        context: Context,
        title: String,
        content: String,
        iconResId: Int
    ) {
        val data = workDataOf(
            "TITLE" to title,
            "CONTENT" to content,
            "ICON" to iconResId,
            "ID" to 1001
        )

        val workRequest = PeriodicWorkRequestBuilder<ZekrWorker>(
            15, TimeUnit.MINUTES   // ⏰ التكرار كل 15 دقيقة (أقل شيء مسموح في WorkManager)
        )
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "zekr_reminder",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    fun cancelZekr(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork("zekr_reminder")
    }
}
