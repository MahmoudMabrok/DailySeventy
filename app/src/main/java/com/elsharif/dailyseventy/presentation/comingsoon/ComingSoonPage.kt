package com.elsharif.dailyseventy.presentation.comingsoon

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elsharif.dailyseventy.R
import com.elsharif.dailyseventy.presentation.components.DashboardScreenTopBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComingSoonPage(navController: NavController) {
    Scaffold(
        topBar =
            {
                DashboardScreenTopBar(
                    navController = navController
                )
            },
        modifier = Modifier.fillMaxSize()
    ) {

        ComingSoonPageViews()
    }
}

@Composable
private fun ComingSoonPageViews() {

    var iconScale by remember { mutableFloatStateOf(1.0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "Inf")
    val bounceAnimation by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Bounce Animation"
    )

    val alphaAnimationState by animateFloatAsState(
        targetValue = 1.0f,
        animationSpec = keyframes {
            durationMillis = 2000
            1000 to 0.0f // Start at 0 opacity for 1 second
            2000 to 1.0f // Reach full opacity at 2 seconds
        }, label = "AlphaAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .scale(bounceAnimation)
                .alpha(alphaAnimationState)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.doaa),
                contentDescription = "App Icon",
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.is_coming_soon),
                color = MaterialTheme.colorScheme.background,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 180.dp),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComingSoonPagePreview() {
    ComingSoonPageViews()
}