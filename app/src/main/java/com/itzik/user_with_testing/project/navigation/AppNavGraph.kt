package com.itzik.user_with_testing.project.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.MainScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Graph.SPLASH
    ) {
        navigation(
            route = Graph.SPLASH,
            startDestination = SplashGraph.Splash.route
        ) {
            composable(route = SplashGraph.Splash.route) {
                SplashScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }


        navigation(
            route = Graph.LOGIN,
            startDestination = LoginGraph.Login.route
        ) {
            composable(route = LoginGraph.Login.route) {
                LoginScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
            composable(route = LoginGraph.SignUp.route) {
                CreateAccountScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            route = Graph.LOGIN,
            startDestination = BottomBarGraph.SearchFlights.route
        ) {
            composable(route = BottomBarGraph.SearchFlights.route) {
                MainScreen(
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}