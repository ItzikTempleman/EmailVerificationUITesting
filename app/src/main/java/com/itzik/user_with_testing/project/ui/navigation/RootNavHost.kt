package com.itzik.user_with_testing.project.ui.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itzik.user_with_testing.project.ui.navigation.Graph.AUTHENTICATION
import com.itzik.user_with_testing.project.ui.navigation.Graph.HOME
import com.itzik.user_with_testing.project.ui.navigation.Graph.ROOT
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.ui.screens.authentication.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.authentication.registration.RegistrationScreen
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    NavHost(
        startDestination = ROOT,
        navController = navController
    ) {
        navigation(
            startDestination = ScreenContainer.Splash.route,
            route = ROOT
        ) {
            composable(route = ScreenContainer.Splash.route) {
                SplashScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination = ScreenContainer.Login.route,
            route = AUTHENTICATION
        ) {
            composable(route = ScreenContainer.Login.route) {
                LoginScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }

            composable(route = ScreenContainer.Registration.route) {
                RegistrationScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination = ScreenContainer.Search.route,
            route = HOME
        ) {
            composable(route = ScreenContainer.Search.route) {
                HomeNavHost(
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}


