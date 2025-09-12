package com.elsharif.dailyseventy.util.Navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elsharif.dailyseventy.MainActivity
import com.elsharif.dailyseventy.presentation.Qibla.QiblaPage
import com.elsharif.dailyseventy.presentation.hijriCalendar.HijriCalendar
import com.elsharif.dailyseventy.presentation.azkarcategories.CategoryScreen
import com.elsharif.dailyseventy.presentation.home.view.HomePage
import com.elsharif.dailyseventy.presentation.prayertimes.PrayerTimeViewModel
import com.elsharif.dailyseventy.presentation.prayertimes.PrayerTimesPage
import com.elsharif.dailyseventy.presentation.settings.SettingsScreen
import com.elsharif.dailyseventy.presentation.tasbeeh.CustomZikrSebhaPage
import com.elsharif.dailyseventy.presentation.tasbeeh.CustomizableSebhaPage
import com.elsharif.dailyseventy.presentation.tasbeeh.ImageSebhaPage
import com.elsharif.dailyseventy.presentation.tasbeeh.TasbeehLandingPage
import com.elsharif.dailyseventy.presentation.tasbeeh.TasbeehViewModel
import com.elsharif.dailyseventy.presentation.tasbeeh.ZikrListSebhaPage
import com.elsharif.dailyseventy.presentation.zekr.ZekkrScreen
import com.elsharif.dailyseventy.ui.theme.ThemeViewModel
import com.elsharif.dailyseventy.util.Screen
import java.time.chrono.HijrahDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController,context: Context,themeViewModel: ThemeViewModel,prayerTimeViewModel: PrayerTimeViewModel) {

    val startCategory = (context as? MainActivity)?.intent?.getStringExtra("category") ?: ""

    NavHost(
        navController,
        startDestination = if (startCategory.isNotEmpty()) "zekkr_screen/$startCategory" else Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomePage(navController)
        }
        composable("zekkr_screen/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ZekkrScreen(navController, category)
        }
        composable(Screen.PrayerTimes.route) {
            PrayerTimesPage()

        }
        composable(Screen.Azkar.route) {
            CategoryScreen(navController)

        }
        composable(Screen.Hijri.route) {
            var hijrahDate by remember { mutableStateOf(HijrahDate.now()) }

            HijriCalendar(  selectedDate = hijrahDate, navController = navController) {
                hijrahDate = it
            }
        }
        composable(Screen.Qible.route) {

           QiblaPage(navController)
        }
        composable(Screen.Settings.route) {

            SettingsScreen(navController,themeViewModel, context, prayerTimeViewModel)

        }
        composable(Screen.NightThirdRoute.route) {

          //  NightThirdScreen(navController)

        }
        composable(Screen.Tasbeeh.route) {

            TasbeehLandingPage(navController)

        }
        composable(Screen.ColorPicker.route) {


        }
        composable(Screen.TasbeehImages.route) {
            val viewModel: TasbeehViewModel = hiltViewModel()

            ImageSebhaPage(viewModel,navController)
        }
        composable(Screen.TasbeehList.route) {
            val viewModel: TasbeehViewModel = hiltViewModel()

            ZikrListSebhaPage(viewModel,navController)

        }
        composable(Screen.TasbeehCustom.route) {
            val viewModel: TasbeehViewModel = hiltViewModel()

            CustomZikrSebhaPage(viewModel,navController)
        }



    }
}
