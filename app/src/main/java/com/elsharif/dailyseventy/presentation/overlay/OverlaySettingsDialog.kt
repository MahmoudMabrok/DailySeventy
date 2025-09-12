/*
package com.elsharif.dailyseventy.presentation.overlay

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elsharif.dailyseventy.domain.data.sharedpreferences.OverlayPrefs
import com.elsharif.dailyseventy.domain.overlay.OverlayService

@Composable
fun OverlaySettingsDialog(
    context: Context,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    var enabled by remember { mutableStateOf(OverlayPrefs.isEnabled(context)) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("إعدادات النافذة العائمة") },
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("تفعيل التذكير")
                    Spacer(Modifier.width(8.dp))
                    Switch(
                        checked = enabled,
                        onCheckedChange = {
                            enabled = it
                            OverlayPrefs.setEnabled(context, it)

                            val intent = Intent(context, OverlayService::class.java)
                            if (it) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                                    context.startForegroundService(intent)
                                else
                                    context.startService(intent)
                            } else {
                                context.stopService(intent)
                            }
                        }
                    )
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) { Text("إغلاق") }
            }
        )
    }
}
*/
