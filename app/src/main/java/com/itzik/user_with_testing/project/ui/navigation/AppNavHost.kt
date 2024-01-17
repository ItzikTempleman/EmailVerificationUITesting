package com.itzik.user_with_testing.project.ui.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import com.itzik.user_with_testing.project.ui.navigation.Graph.AUTHENTICATION
import com.itzik.user_with_testing.project.ui.navigation.Graph.DETAILS
import com.itzik.user_with_testing.project.ui.navigation.Graph.HOME
import com.itzik.user_with_testing.project.ui.navigation.Graph.ROOT
import com.itzik.user_with_testing.project.ui.screens.DetailsScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.ui.screens.authentication.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.authentication.registration.RegistrationScreen
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    NavHost(
        startDestination = ROOT,
        navController = navController
    ) {
        navigation(
            startDestination = ScreenHost.Splash.route,
            route = ROOT
        ) {
            composable(route = ScreenHost.Splash.route) {
                SplashScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination = ScreenHost.Login.route,
            route = AUTHENTICATION
        ) {
            composable(route = ScreenHost.Login.route) {
                LoginScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }

            composable(route = ScreenHost.Registration.route) {
                RegistrationScreen(
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination = ScreenHost.Search.route,
            route = HOME
        ) {
            composable(route = ScreenHost.Search.route) {
                HomeNavigation(
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination =ScreenHost.Details.route,
            route = DETAILS
        ) {
            composable(route = ScreenHost.Details.route) {
                val result =
                    navController.previousBackStackEntry?.savedStateHandle?.get<FlightInfoResponse>(
                        "flight_info"
                    )
                DetailsScreen(
                    result = result,
                    navController = navController,
                    appViewModel = appViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}