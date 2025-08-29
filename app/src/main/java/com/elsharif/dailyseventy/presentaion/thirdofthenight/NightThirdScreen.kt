/*
package com.elsharif.dailyseventy.presentaion.thirdofthenight

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.elsharif.dailyseventy.domain.data.shardprefernces.NightThird
import com.elsharif.dailyseventy.domain.data.shardprefernces.NightThirdPrefs
import com.elsharif.dailyseventy.presentaion.prayertimes.PrayerTimeViewModel
import com.elsharif.dailyseventy.domain.thirdnight.cancelNightThirdNotifications
import com.elsharif.dailyseventy.domain.thirdnight.scheduleNightThirdNotifications
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NightThirdScreen(
    navController: NavController,
    viewModel: PrayerTimeViewModel = hiltViewModel()
) {
    val context = navController.context

    var nightThirdText by remember { mutableStateOf("...") }
    var maghrib by remember { mutableStateOf<LocalTime?>(null) }
    var fajr by remember { mutableStateOf<LocalTime?>(null) }

    var enabled by remember { mutableStateOf(NightThirdPrefs.isEnabled(context)) }
    var selection by remember { mutableStateOf(
        NightThirdPrefs.getSelection(context).ifEmpty {
            setOf(NightThird.THIRD) // افتراضيًا الثلث الأخير لو لا يوجد إعدادات محفوظة
        }
    )}

    // اجلب الأوقات واحسب الثلث الحالي
    LaunchedEffect(Unit) {
        viewModel.prayerTimesFlow.collect { prayers ->
            val mag = prayers.firstOrNull { it.name.contains("Maghrib", true) }?.time
                ?.let { LocalTime.parse(it, DateTimeFormatter.ofPattern("hh:mm a")) }
            val fjr = prayers.firstOrNull { it.name.contains("Fajr", true) }?.time
                ?.let { LocalTime.parse(it, DateTimeFormatter.ofPattern("hh:mm a")) }

            if (mag != null && fjr != null) {
                maghrib = mag
                fajr = fjr
                nightThirdText = getNightThird(mag, fjr)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "الثلث الحالي من الليل", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(text = nightThirdText, style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(24.dp))

        // تفعيل/تعطيل التذكير
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "تفعيل التذكير")
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = enabled,
                onCheckedChange = { isOn ->
                    enabled = isOn
                    NightThirdPrefs.saveEnabled(context, isOn)
                    if (!isOn) cancelNightThirdNotifications(context)
                    // لو شغّله الآن لن نضبط إلا عند الحفظ (زر الحفظ بالأسفل)
                }
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("اختر أي أثلاث تريد تلقي إشعار لها:")
        Spacer(Modifier.height(8.dp))

        fun toggle(third: NightThird) {
            selection = selection.toMutableSet().also {
                if (it.contains(third)) it.remove(third) else it.add(third)
            }
        }

        listOf(
            NightThird.FIRST to "الثلث الأول",
            NightThird.SECOND to "الثلث الثاني",
            NightThird.THIRD to "الثلث الثالث"
        ).forEach { (t, label) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = selection.contains(t),
                        onValueChange = { toggle(t) }
                    )
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = selection.contains(t), onCheckedChange = null)
                Spacer(Modifier.width(8.dp))
                Text(label)
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            NightThirdPrefs.saveSelection(context, selection)
            if (enabled && maghrib != null && fajr != null) {
                scheduleNightThirdNotifications(
                    context = context,
                    maghrib = maghrib!!,
                    fajr = fajr!!,
                    selection = selection
                )
            }
        }) {
            Text("حفظ وتفعيل")
        }
    }
}

*/
/** نفس دالتك السابقة لحساب الثلث الحالي *//*

@RequiresApi(Build.VERSION_CODES.O)
fun getNightThird(maghrib: LocalTime, fajr: LocalTime): String {
    val now = LocalTime.now()
    val nightMinutes = if (fajr.isBefore(maghrib)) {
        ChronoUnit.MINUTES.between(maghrib, LocalTime.MAX) + 1 +
                ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, fajr)
    } else {
        ChronoUnit.MINUTES.between(maghrib, fajr)
    }
    val third = nightMinutes / 3
    val sinceStart = if (now.isBefore(maghrib)) {
        ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, now) +
                ChronoUnit.MINUTES.between(maghrib, LocalTime.MAX) + 1
    } else {
        ChronoUnit.MINUTES.between(maghrib, now)
    }
    return when {
        sinceStart <= third -> "الثلث الأول من الليل"
        sinceStart <= third * 2 -> "الثلث الثاني من الليل"
        else -> "الثلث الثالث من الليل"
    }
}
*/
