package com.elsharif.dailyseventy.domain.friday

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FridayReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val type = inputData.getString("type") ?: return Result.failure()

        when (type) {
            "kahf" -> {
                NotificationHelper.showNotification(
                    applicationContext,
                    "Friday Reminder",
                    "Don’t forget to read Surah Al-Kahf after Dhuhr.",
                    "kahf"
                )
            }
            "asr" -> {
                NotificationHelper.showNotification(
                    applicationContext,
                    "Friday Reminder",
                    "Make extra duaa after Asr on Friday.",
                    "asr"
                )
            }
        }

        return Result.success()
    }
}
