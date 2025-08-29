// app/src/main/java/com/elsharif/dailyseventy/domain/nightthird/NightThirdScheduler.kt
package com.elsharif.dailyseventy.domain.thirdnight

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.elsharif.dailyseventy.domain.data.shardprefernces.NightThird
import com.elsharif.dailyseventy.domain.nightthird.NightThirdReceiver
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun scheduleNightThirdNotifications(
    context: Context,
    maghrib: LocalTime,
    fajr: LocalTime,
    selection: Set<NightThird>
) {
    cancelNightThirdNotifications(context) // نعيد الجدولة من الصفر

    val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // الليلة الحالية: من مغرب اليوم إلى فجر الغد (إن كان الفجر بعد منتصف الليل)
    val today = LocalDate.now()
    val maghribDateTime = LocalDateTime.of(today, maghrib)
    val fajrDateTime = if (fajr.isAfter(maghrib)) {
        // فجر بعد المغرب في نفس اليوم (نادر جدًا)، نتعامل معه كأنه فجر اليوم التالي إن رغبت
        LocalDateTime.of(today.plusDays(1), fajr)
    } else {
        // فجر بعد منتصف الليل (غالب الشائع)
        LocalDateTime.of(today.plusDays(1), fajr)
    }

    val nightDuration = Duration.between(maghribDateTime, fajrDateTime) // مدة الليل
    val thirdDuration = nightDuration.dividedBy(3)

    // بدايات الأثلاث:
    val firstStart = maghribDateTime                      // بداية الثلث الأول = عند المغرب
    val secondStart = maghribDateTime.plus(thirdDuration) // بداية الثلث الثاني
    val thirdStart = maghribDateTime.plus(thirdDuration.multipliedBy(2)) // بداية الثالث

    val nowMillis = System.currentTimeMillis()

    fun setExact(time: LocalDateTime, requestCode: Int, label: String) {
        val triggerAt = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        if (triggerAt <= nowMillis) return // لا نضبط موعد مر عليه الوقت
        val intent = Intent(context, NightThirdReceiver::class.java).apply {
            putExtra("third_name", label)
        }
        val pi = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pi)
        } else {
            am.setExact(AlarmManager.RTC_WAKEUP, triggerAt, pi)
        }
    }

    if (selection.contains(NightThird.FIRST))  setExact(firstStart, 2101, "الثلث الأول")
    if (selection.contains(NightThird.SECOND)) setExact(secondStart, 2102, "الثلث الثاني")
    if (selection.contains(NightThird.THIRD))  setExact(thirdStart, 2103, "الثلث الثالث")
}

fun cancelNightThirdNotifications(context: Context) {
    val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    listOf(2101, 2102, 2103).forEach { rc ->
        val pi = PendingIntent.getBroadcast(
            context, rc, Intent(context, NightThirdReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        am.cancel(pi)
    }
}
