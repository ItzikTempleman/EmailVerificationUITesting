package com.itzik.user_with_testing.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SearchScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeNavGraph(
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope
) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = BottomBar.Home.route
    ) {
        composable(route = BottomBar.Home.route) {
           SearchScreen(
               navController = navHostController,
               userViewModel = userViewModel,
               coroutineScope = coroutineScope
           )
        }
        composable(route = BottomBar.Profile.route) {
            ProfileScreen(
                navHostController = navHostController,
                userViewModel = userViewModel,
                coroutineScope = coroutineScope
            )
        }
    }
}

