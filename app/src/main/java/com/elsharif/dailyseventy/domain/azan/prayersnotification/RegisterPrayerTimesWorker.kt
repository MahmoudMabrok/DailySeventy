package com.elsharif.dailyseventy.domain.azan.prayersnotification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.di.WorkerEntryPoint
import com.elsharif.dailyseventy.domain.AppPreferences
import com.elsharif.dailyseventy.domain.azan.prayertimes.Date
import com.example.core.usecase.GetPrayerTimesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class RegisterPrayerTimesWorker(private val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val preferences = AppPreferences(applicationContext)
            val location = preferences.currentLocation
            val method = preferences.method


             val prayerTimes: GetPrayerTimesUseCase by lazy {
                val appContext = applicationContext
                val entryPoint = EntryPointAccessors.fromApplication(appContext, WorkerEntryPoint::class.java)
                entryPoint.getPrayerTimesUseCase()
            }

            val prayerTimings = combine(location, method) { locationValue, methodValue ->
                prayerTimes(
                    locationValue.first,
                    locationValue.second,
                    LocalDate.now(),
                    methodValue
                ).single()
            }

            val prayerTimingsList = withContext(Dispatchers.IO) {
                prayerTimings.first()
            }

            prayerTimingsList.forEachIndexed { idx, prayerTiming ->
                val imgId = context.resources.getIdentifier(
                    prayerTiming.prayer.imageId, "drawable",
                    context.packageName
                )

                val nameId = context.resources.getIdentifier(
                    prayerTiming.prayer.name, "string",
                    context.packageName
                )

                val prayerTime = parseTime(prayerTiming.time)
                val prayerDate = prayerTiming.date

                val prayerName = if (nameId != 0) {
                    context.getString(nameId)
                } else {
                    prayerTiming.prayer.name // Fallback
                }


                val prayerTag = "${prayerTiming.prayer.name} $prayerTime"
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")
                val timeInMillis =
                    LocalDateTime
                        .from(formatter.parse("$prayerDate $prayerTime"))
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()
                Log.d(TAG, "Parsed time string: ${prayerDate} ${prayerTime}")

                Log.d(TAG, "doWork: $timeInMillis")

                if (timeInMillis > System.currentTimeMillis()) {
                    Log.d(TAG, "didn't pass: $prayerTag $timeInMillis")

                    setAlarm(
                        id = idx,
                        time = timeInMillis,
                        icon = imgId,
                        title = "${context.getString(R.string.prayer)} $prayerName " + context.getString(
                            R.string.prayer_is_now
                        ),
                        content = "${context.getString(R.string.prayer_is_now)} ${
                            context.getString(
                                R.string.prayer_is_now
                            )
                        }"


                    )

                }
            }


            Log.d(TAG, "doWork: returning")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "doWork: Error occurred: ${e.message}", e)
            Result.retry()
        }.also {
            Log.d(TAG, "doWork: $it")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseTime(inputTime: String): String {
        val timeStartIndex = inputTime.indexOf(':') - 2
        val timeEndIndex = inputTime.indexOf('(') - 1
        val extractedTime = inputTime.substring(timeStartIndex, timeEndIndex)

        val inputFormat = DateTimeFormatter.ofPattern("HH:mm")
        val time = LocalTime.parse(extractedTime, inputFormat)


        val outputFormat = DateTimeFormatter.ofPattern("hh:mm a")
        return outputFormat.format(time)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun setAlarm(id: Int, time: Long, icon: Int, title: String, content: String) {
        runCatching {
            val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AzanAlarmReceiver::class.java).apply {
                putExtra("CONTENT", content)
                putExtra("TITLE", title)
                putExtra("ICON", icon)
                putExtra("ID", id)
            }


            val pi: PendingIntent = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            Log.d(TAG, "setAlarm: id=$id, time=$time, icon=$icon, title=$title, content=$content")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi)
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pi)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d(
                    TAG,
                    "registerPrayers:NEXT ${
                        ContextCompat.getSystemService(
                            context, AlarmManager::class.java
                        )?.nextAlarmClock?.triggerTime
                    } CAN ${alarmManager.canScheduleExactAlarms()}"
                )
            }
        }.onFailure {
            Log.d(TAG, "setAlarm: $it")
        }
    }

    companion object {
        private const val TAG = "RegisterPrayerTimes"
    }

    private fun Context.cancelAlarm(requestCode: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, requestCode,
            Intent(this, AzanAlarmReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager?.cancel(pendingIntent)
    }
}
