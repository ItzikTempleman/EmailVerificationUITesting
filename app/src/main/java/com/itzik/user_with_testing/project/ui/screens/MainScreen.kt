package com.itzik.user_with_testing.project.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.navigation.MainScreenNavGraph
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope
)
{
    Scaffold(
        bottomBar = {
            NavBarScreen(navController = navController)
        }
    ) {
        MainScreenNavGraph(
            userViewModel = userViewModel,
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}