package com.elsharif.dailyseventy.presentaion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elsharif.dailyseventy.domain.model.Zakker
import com.elsharif.dailyseventy.ui.theme.RadiusContainer


@Composable
fun CountCard(
     modifier: Modifier=Modifier,
     zekkr:Zakker,
     count:Int,
     onClick:()->Unit
) {



    Box(
        modifier = modifier.padding(
            bottom = 16.dp,
            start = 8.dp,
            end = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(RadiusContainer.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxSize()
                    .padding(6.dp)
            ) {

            Text(text = zekkr.content, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text =zekkr.description, style = MaterialTheme.typography.bodySmall.copy(fontSize = 30.sp))

            IconButton(
                onClick = {
                 onClick()
                }
            ) {
            CircularProgressIndicator(
                progress = count.toFloat() /(zekkr.count).toFloat() ,
                color = Color(0xffEF2679),
                modifier = Modifier.size(100.dp),
                trackColor = Color(0xFFFFFFFF),
                strokeWidth = 4.dp
            )
             }
            }

        }

    }
}