package com.elsharif.dailyseventy.presentaion.zekr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.elsharif.dailyseventy.presentaion.components.CountCard
import com.elsharif.dailyseventy.presentation.zekr.ZekkrViewModel

@Composable
fun ZekkrScreen(
    navController:NavController,
    category:String
) {

    val viewModel:ZekkrViewModel = hiltViewModel()
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
                    count = count,  // Display count from ViewModel
                    onClick = { viewModel.onEvent(ZekkrEvent.IncreaseCount(zekr.count))}
                ) // Display each item
            }


        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar () {

    CenterAlignedTopAppBar(
        title = {
            Text(text = "Mind Crafted", style = MaterialTheme.typography.headlineMedium)
        }
    )

}
