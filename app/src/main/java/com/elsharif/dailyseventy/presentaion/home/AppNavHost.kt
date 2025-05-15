package com.elsharif.dailyseventy.presentaion.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elsharif.dailyseventy.presentaion.zekr.ZekkrScreen
import com.elsharif.dailyseventy.util.Screen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController, startDestination = "الرئيسية") {
        composable(Screen.Home.route) {
            CategoryScreen(navController)
        }
        composable("zekkr_screen/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ZekkrScreen(navController, category)
        }
        composable(Screen.Morning.route) {
            CategoryScreen(navController)
        }
        composable(Screen.Details.route) {
            CategoryScreen(navController)
        }

    }
}
