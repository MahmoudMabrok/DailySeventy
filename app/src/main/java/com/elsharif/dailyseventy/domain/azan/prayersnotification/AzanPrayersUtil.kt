package com.elsharif.dailyseventy.domain.azan.prayersnotification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


object AzanPrayersUtil {

    private const val TAG = "AzanPrayersUtil"
    fun registerPrayers(context: Context) {



        WorkManager.getInstance(context.applicationContext).cancelAllWork()
        val registerRequest =
            PeriodicWorkRequest.Builder(RegisterPrayerTimesWorker::class.java, 1, TimeUnit.DAYS)
                .addTag("REGISTER_PRAYERS")
                .build()
        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                "REGISTER_PRAYERS",
                ExistingPeriodicWorkPolicy.UPDATE,
                registerRequest
            )
    }
}