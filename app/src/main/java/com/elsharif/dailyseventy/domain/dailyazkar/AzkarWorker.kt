package com.elsharif.dailyseventy.domain.dailyazkar



import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.elsharif.dailyseventy.R

class AzkarWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val type = inputData.getString("type") ?: return Result.failure()

        val (title, message, category) = when (type) {
            "morning" -> listOf("أذكار الصباح", "وقت أذكار الصباح 🌞", "أذكار الصباح")
            "evening" -> listOf("أذكار المساء", "وقت أذكار المساء 🌙", "أذكار المساء")
            "night" -> listOf("أذكار النوم", "وقت أذكار النوم 🛌", "أذكار النوم")
            "sunrise" -> listOf("وقت الشروق🌞", "🌅 وقت أذكار الاستيقاظ", "أذكار الاستيقاظ")
            else -> return Result.failure()
        }

        NotificationHelperAzkar.showNotification(
            context = appContext,
            title = title,
            message = message,
            category = category,
        )

        return Result.success()
    }
}

