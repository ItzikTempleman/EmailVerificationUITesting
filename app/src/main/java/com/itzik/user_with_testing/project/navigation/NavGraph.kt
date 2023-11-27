package com.itzik.user_with_testing.project.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.HomeScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

val Dark_Green = Color(0xFF007d74)
val Turquoise = Color(0xFF30D5C8)
val Light_Orange= Color(0xFFFFA500)
val Light_Green= Color(0xFF65A769)
val Light_Purple= Color(0xFFB782DD)
val Light_Blue= Color(0xFF58A8FF)

const val SPLASH_GRAPH = "splashGraph"
const val LOGIN_GRAPH = "loginGraph"
const val HOME_GRAPH = "homeGraph"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope
) {
    NavHost(
        navController = navHostController,
        startDestination = SPLASH_GRAPH
    ) {
        navigation(
            startDestination = SplashGraph.SplashPage.route,
            route = SPLASH_GRAPH,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(
                route = SplashGraph.SplashPage.route,
                enterTransition = null,
                exitTransition = null
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
                route = LoginGraph.LoginPage.route,
                enterTransition = null,
                exitTransition = null
            ) {
                LoginScreen(
                    navHostController = navHostController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope,
                    modifier = Modifier
                )
            }
            composable(
                route = LoginGraph.CreateAccountPage.route,
                enterTransition = null,
                exitTransition = null
            ) {
                CreateAccountScreen(
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
                route = HomeGraph.HomePage.route,
                enterTransition = null,
                exitTransition = null
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


