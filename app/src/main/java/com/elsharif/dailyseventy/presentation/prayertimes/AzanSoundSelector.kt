package com.elsharif.dailyseventy.presentation.prayertimes

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.presentation.prayertimes.model.AzanSound
import com.elsharif.dailyseventy.domain.data.sharedpreferences.AzanSoundPrefs
import com.elsharif.dailyseventy.domain.azan.prayersnotification.updateAzanChannel

@Composable
fun AzanSoundSelectorDialog(
    context: Context,
    onDismiss: () -> Unit,
    onSoundSelected: () -> Unit
) {
    val sounds = listOf(
        AzanSound("علي الملا", R.raw.elmola),
        AzanSound("أبو العينين", R.raw.aboelenin),
        AzanSound("عبدالباسط عبدالصمد", R.raw.abdelbasset),
        AzanSound("مشاري راشد", R.raw.mosharyfajr)
    )

    val selectedSoundResId = remember {
        mutableIntStateOf(AzanSoundPrefs.loadSelectedSound(context))
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "اختر صوت الأذان",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                sounds.forEach { sound ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = sound.name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (sound.resId == selectedSoundResId.intValue)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                        RadioButton(
                            selected = sound.resId == selectedSoundResId.intValue,
                            onClick = {
                                selectedSoundResId.intValue = sound.resId
                                AzanSoundPrefs.saveSelectedSound(context, sound.resId)

                                // تحديث القناة فورًا
                                updateAzanChannel(context)

                                onSoundSelected()
                                onDismiss()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    )
}
