/*
package com.elsharif.dailyseventy.presentation.overlay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OverlayContent(messages: List<String>) {
    var currentMessage by remember { mutableStateOf(messages.random()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000L)
            currentMessage = messages.random()
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = currentMessage,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp
        )
    }
}
*/
