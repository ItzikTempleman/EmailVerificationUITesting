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
fun MainScreenNavGraph(navController: NavHostController, userViewModel: UserViewModel, coroutineScope: CoroutineScope) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBar.SearchFlights.route
    ) {
        composable(route = BottomBar.SearchFlights.route) {
           SearchScreen(
               navController = navController,
               userViewModel = userViewModel,
               coroutineScope = coroutineScope
           )
        }
        composable(route = BottomBar.Profile.route) {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel,
                coroutineScope = coroutineScope
            )
        }
    }
}

