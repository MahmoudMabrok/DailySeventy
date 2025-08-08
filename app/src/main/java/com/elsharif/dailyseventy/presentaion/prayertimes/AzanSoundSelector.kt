package com.elsharif.dailyseventy.presentaion.prayertimes

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.presentaion.prayertimes.model.AzanSound
import androidx.core.content.edit

@Composable
fun AzanSoundSelector(
    context: Context,
    onSoundSelected: () -> Unit
) {
    val sounds = listOf(
        AzanSound("علي الملا", R.raw.elmola),
        AzanSound("أبو العينين", R.raw.aboelenin),
        AzanSound("عبدالباسط عبدالصمد", R.raw.abdelbasset),
        AzanSound("مشاري راشد", R.raw.mosharyfajr)
    )

    val selectedSoundResId = remember {
        mutableIntStateOf(loadSelectedSound(context))
    }

    LazyColumn {
        items(sounds) { sound ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedSoundResId.intValue = sound.resId
                        saveSelectedSound(context, sound.resId)
                        onSoundSelected()
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = sound.name,
                    color = if (sound.resId == selectedSoundResId.intValue) Color.Green else Color.Black
                )
            }
        }
    }
}

fun saveSelectedSound(context: Context, soundResId: Int) {
    val prefs = context.getSharedPreferences("azan_prefs", Context.MODE_PRIVATE)
    prefs.edit { putInt("selected_sound_res_id", soundResId) }
}

fun loadSelectedSound(context: Context): Int {
    val prefs = context.getSharedPreferences("azan_prefs", Context.MODE_PRIVATE)
    // If nothing is saved, return elmola as default
    return prefs.getInt("selected_sound_res_id", R.raw.elmola)
}
