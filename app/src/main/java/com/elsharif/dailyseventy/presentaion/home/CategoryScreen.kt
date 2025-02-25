package com.elsharif.dailyseventy.presentaion.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elsharif.dailyseventy.ui.theme.SmallRadius
import com.elsharif.dailyseventy.ui.theme.ubuntuFontFamily
import com.elsharif.dailyseventy.util.header

@Composable
fun CategoryScreen(navController: NavController) {

    val categories = listOf("أذكار الصباح", "أذكار المساء", "أذكار بعد السلام من الصلاة المفروضة",
        "تسابيح" ,"أذكار النوم" ,"أذكار الاستيقاظ" ,"أدعية قرآنية" ,"أدعية الأنبياء")

    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
        ) {

            val listState = rememberLazyGridState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                contentPadding = PaddingValues(top = SmallRadius.dp),
            ) {
                header {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    vertical = 16.dp,
                                    horizontal = 22.dp
                                ),
                            textAlign = TextAlign.Center,
                            text = "",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily =ubuntuFontFamily ,
                            fontSize = 20.sp
                        )
                    }

                }
                items(categories.size){ category ->
                    Button(
                        onClick = {
                            navController.navigate("zekkr_screen/${categories[category]}") // ✅ Navigate with category
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = categories[category],
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = ubuntuFontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                            )
                    }
                }


            }

        }

/*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categories.forEach { category ->
                Button(
                    onClick = {
                        navController.navigate("zekkr_screen/$category") // ✅ Navigate with category
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = category, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }*/

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar () {

    CenterAlignedTopAppBar(
        title = {
            Text(text = "سبعون مرة", style = MaterialTheme.typography.headlineMedium)
        }
    )


}
