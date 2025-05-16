package com.elsharif.dailyseventy.presentaion.zekr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.elsharif.dailyseventy.presentaion.components.CountCard

@Composable
fun ZekkrScreen(
    navController: NavController,
    category:String
) {

    /*
    * عايز أضيف إمكانية الشير للنص
    * عايز أغير لون التيكست التاني
    * عايز أغير باك جراوند الصفجة الرئيسية
    *
    * */


    val viewModel: ZekkrViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val count by viewModel.count.collectAsStateWithLifecycle()



    LaunchedEffect(category) {
        viewModel.onEvent(ZekkrEvent.SelectCategory(category)) // ✅ Fetch azkaar for category
    }


    Scaffold(
        //snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {DashboardScreenTopBar()}
    ) {paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(state.azkaar.size) { index ->
                val zekr = state.azkaar[index]
                CountCard(
                    zekkr = zekr,
                )
            }


        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar () {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "سبعون مرة",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White // Optional: change text color
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF294878) // Example: dark golden brown
        )
    )

}
