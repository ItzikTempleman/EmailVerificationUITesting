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
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigationGraph(
    userViewModel: UserViewModel,
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
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            route = Graph.LOGIN,
            startDestination = AuthScreen.Login.route
        ) {
            composable(route = AuthScreen.Login.route) {
                LoginScreen(
                    navController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
            composable(route = AuthScreen.SignUp.route) {
                CreateAccountScreen(
                    navController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        composable(route = BottomBar.Home.route) {
            MainScreen(
                userViewModel = userViewModel,
                coroutineScope = coroutineScope
            )
        }
        
    }
}