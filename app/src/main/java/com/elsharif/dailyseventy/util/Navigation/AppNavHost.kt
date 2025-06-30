package com.elsharif.dailyseventy.util.Navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.presentaion.Qibla.QiblaScreen
import com.elsharif.dailyseventy.presentaion.hijriCalendar.HijriCalendar
import com.elsharif.dailyseventy.presentaion.home.CategoryScreen
import com.elsharif.dailyseventy.presentaion.prayertimes.PrayerTimesPage
import com.elsharif.dailyseventy.presentaion.zekr.ZekkrScreen
import com.elsharif.dailyseventy.util.Screen
import java.time.chrono.HijrahDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController,context: Context) {

    NavHost(navController, startDestination = stringResource(R.string.ZEKR)) {
        composable(Screen.Home.route) {
            CategoryScreen(navController)
        }
        composable("zekkr_screen/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ZekkrScreen(navController, category)
        }
        composable(Screen.PrayerTimes.route) {
            PrayerTimesPage()
        }
        composable(Screen.Hijri.route) {
            var hijrahDate by remember { mutableStateOf(HijrahDate.now()) }

            HijriCalendar(hijrahDate) {
                hijrahDate = it
            }
        }
        composable(Screen.Qible.route) {
          //
            QiblaScreen()

        }

    }
}
