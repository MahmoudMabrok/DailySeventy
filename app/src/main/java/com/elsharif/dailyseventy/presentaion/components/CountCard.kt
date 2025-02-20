package com.elsharif.dailyseventy.presentaion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CountCard(
    modifier: Modifier=Modifier,
) {


    var count :Int =0
    var content :String ="أستغفر الله"
    var description :String=""
    ElevatedCard(
        modifier = Modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = content, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text =description, style = MaterialTheme.typography.bodySmall.copy(fontSize = 30.sp))

            IconButton(
                onClick = {
                    count--
                }
            ) {
            CircularProgressIndicator(
                progress = count.toFloat() /(count+100).toFloat() ,
                color = Color(0xffEF2679),
                modifier = Modifier.size(150.dp),
                trackColor = Color(0xFFFFFFFF),
                strokeWidth = 4.dp
            )
             }
        }

    }
}