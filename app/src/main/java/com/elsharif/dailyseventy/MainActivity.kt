package com.elsharif.dailyseventy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.elsharif.dailyseventy.presentaion.home.AppNavHost
import com.elsharif.dailyseventy.presentaion.zekr.ZekkrScreen
import com.elsharif.dailyseventy.ui.theme.DailySeventyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailySeventyTheme {

                UnifiedNavigationScaffold()
            }
        }
    }
}
