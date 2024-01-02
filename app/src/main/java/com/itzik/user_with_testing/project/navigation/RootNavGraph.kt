package com.itzik.user_with_testing.project.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope




@Composable
fun RootNavigationGraph(
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        splashNavGraph(
            navController = navController,
            userViewModel = userViewModel,
            coroutineScope = coroutineScope
        )
        authNavGraph(
            navController = navController,
            userViewModel = userViewModel,
            coroutineScope = coroutineScope
        )
        composable(route = Graph.HOME) {
            HomeScreen(
                coroutineScope = coroutineScope,
                userViewModel = userViewModel,
                navController = navController
            )
        }
    }
}