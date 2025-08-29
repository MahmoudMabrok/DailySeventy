package com.elsharif.dailyseventy.presentaion.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elsharif.dailyseventy.presentaion.colorselection.ColorPickerDialog
import com.elsharif.dailyseventy.presentaion.components.DashboardScreenTopBar
import com.elsharif.dailyseventy.presentaion.thirdofthenight.NightThirdDialog
import com.elsharif.dailyseventy.ui.theme.ThemeViewModel
import com.elsharif.dailyseventy.util.Screen

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController,themeViewModel: ThemeViewModel) {

    var showNightThirdDialog by remember { mutableStateOf(false) }
    var showColorDialog by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            DashboardScreenTopBar(Screen.Settings.route,navController)
        }
    ) { padding ->

        LazyColumn( // ✅ instead of Column
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SettingsItem("إعدادات اللغة", Icons.Default.Language) {}
            }
            item {
                SettingsItem("إعدادات حساب الوقت", Icons.Default.AccessTime) {
                    showNightThirdDialog = true
                }
                if (showNightThirdDialog) {
                    NightThirdDialog(
                        onDismiss = { showNightThirdDialog = false }
                    )
                }
            }
            item {
                SettingsItem("سمات البرنامج", Icons.Default.ColorLens) {
                 //   navController.navigate(Screen.ColorPicker.route)

                    showColorDialog = true
                }
                if (showColorDialog) {
                    ColorPickerDialog(
                        onDismiss = { showColorDialog = false },
                        onColorSelected = { selectedColor ->
                            themeViewModel.updateColor(selectedColor)
                            showColorDialog = false
                        }
                    )
                }
            }
            item {
                SettingsItem("الإعدادات العامة للأذان", Icons.Default.Notifications) {}
            }
            item {
                SettingsItem("إعدادات صلاة الجمعة", Icons.Default.Settings) {}
            }
            item {
                SettingsItem("إعدادات الويدجت", Icons.Default.Apps) {}
            }
            item {
                SettingsItem("إعدادات متتبع الصلوات والصيام", Icons.Default.Settings) {}
            }
            item {
                SettingsItem("إعدادات الشروق والنوافل", Icons.Default.Settings) {}
            }
            item {
                SettingsItem("إعدادات الأذكار", Icons.Default.Notifications) {}
            }
            item {
                SettingsItem("ربط الساعات الذكية", Icons.Default.Watch) {}
            }
            item {
                SettingsItem("عن البرنامج", Icons.Default.Info) {}
            }
        }
    }
}

@Composable
fun SettingsItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(12.dp))
                Text(title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimary)
            }
            Icon(Icons.Default.Settings, contentDescription = "Arrow")
        }
    }
}
