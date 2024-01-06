package com.itzik.user_with_testing.project.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.navigation.MainScreenNavGraph
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope
)
{
    Scaffold(
        bottomBar = {
            NavBarScreen(navController = navController)
        }
    ) {
        MainScreenNavGraph(
            appViewModel = appViewModel,
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}