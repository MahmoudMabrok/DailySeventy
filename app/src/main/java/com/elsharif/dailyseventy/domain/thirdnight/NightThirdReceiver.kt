package com.elsharif.dailyseventy.domain.thirdnight

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.elsharif.dailyseventy.domain.data.sharedpreferences.NightThird

class NightThirdWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val partName = inputData.getString("third_name") ?: return Result.failure()
        val part = NightThird.valueOf(partName) // رجع enum


        NightThirdNotifier.notify(
            context = applicationContext,
            title = "تنبيه",
            message = "بدأ ${part.arabic} من الليل 🌙"
        )

        return Result.success()
    }
}
