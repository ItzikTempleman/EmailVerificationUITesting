package com.itzik.user_with_testing.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

import com.itzik.user_with_testing.project.ui.screens.FirstRegistrationScreen
import com.itzik.user_with_testing.project.ui.screens.HomeScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.SecondRegistrationScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

const val SPLASH_GRAPH = "splashGraph"
const val LOGIN_GRAPH = "loginGraph"
const val HOME_GRAPH = "homeGraph"

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope,
) {
    NavHost(
        navController = navHostController,
        startDestination = SPLASH_GRAPH
    ) {
        navigation(
            startDestination = SplashGraph.SplashPage.route,
            route = SPLASH_GRAPH
        ) {
            composable(
                route = SplashGraph.SplashPage.route
            ) {
                SplashScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
        }

        navigation(
            startDestination = LoginGraph.LoginPage.route,
            route = LOGIN_GRAPH
        ) {
            composable(
                route = LoginGraph.LoginPage.route
            ) {
                LoginScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
            composable(
                route = LoginGraph.FirstRegistrationPage.route
            ) {
                FirstRegistrationScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
            composable(
                route = LoginGraph.SecondRegistrationPage.route
            ) {
                SecondRegistrationScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
        }

        navigation(
            startDestination = HomeGraph.HomePage.route,
            route = HOME_GRAPH
        ) {
            composable(
                route = HomeGraph.HomePage.route
            ) {
                HomeScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
        }
    }
}


