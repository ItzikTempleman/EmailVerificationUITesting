package com.itzik.user_with_testing.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SearchScreen
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainScreenNavGraph(navController: NavHostController, appViewModel: AppViewModel, coroutineScope: CoroutineScope, modifier: Modifier) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM_BAR,
        startDestination = BottomBarGraph.SearchFlights.route
    ) {
        composable(route = BottomBarGraph.SearchFlights.route) {
           SearchScreen(
               modifier=modifier,
               navController = navController,
               appViewModel = appViewModel,
               coroutineScope = coroutineScope
           )
        }
        composable(route = BottomBarGraph.Profile.route) {
            ProfileScreen(
                modifier=modifier,
                navController = navController,
                appViewModel = appViewModel,
                coroutineScope = coroutineScope
            )
        }
    }
}

