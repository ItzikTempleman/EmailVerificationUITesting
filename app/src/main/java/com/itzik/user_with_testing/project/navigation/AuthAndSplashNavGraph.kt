package com.itzik.user_with_testing.project.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope,
) {
    navigation(
        route = Graph.SPLASH,
        startDestination = SplashGraph.Splash.route
    ) {
        composable(route = SplashGraph.Splash.route) {
            SplashScreen(
                navHostController = navController,
                userViewModel = userViewModel,
                coroutineScope = coroutineScope
            )
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    fun NavGraphBuilder.authNavGraph(
        navController: NavHostController,
        userViewModel: UserViewModel,
        coroutineScope: CoroutineScope,
    ) {

        navigation(
            route = Graph.AUTHENTICATION,
            startDestination = AuthScreen.Login.route
        ) {
            composable(route = AuthScreen.Login.route) {
                LoginScreen(
                    navHostController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
            composable(route = AuthScreen.SignUp.route) {
                CreateAccountScreen(
                    navHostController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }
    }