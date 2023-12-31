package com.itzik.user_with_testing.project.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SearchFlightScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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
                    coroutineScope = coroutineScope,
                    userViewModel = userViewModel,
                    navHostController = navHostController,
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
                    modifier = Modifier,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
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
            startDestination = HomeGraph.HomeAndSearchPage.route,
            route = HOME_GRAPH
        ) {
            composable(
                route = HomeGraph.HomeAndSearchPage.route,
                enterTransition = null,
                exitTransition = null
            ) {
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val items = listOf(HomeGraph.HomeAndSearchPage, HomeGraph.ProfilePage)
                            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    label = {
                                        Text(text = screen.route)
                                    },
                                    icon = {},
                                    onClick = {
                                        navHostController.navigate(screen.route) {
                                            popUpTo(navHostController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navHostController,
                        route = HomeGraph.HomeAndSearchPage.route,
                        startDestination = HomeGraph.HomeAndSearchPage.route
                    ) {
                        composable(route = HomeGraph.HomeAndSearchPage.route) {
                            SearchFlightScreen(
                                coroutineScope = coroutineScope,
                                navHostController = navHostController,
                                modifier = Modifier,
                                userViewModel = userViewModel
                            )
                        }
                        composable(route = HomeGraph.ProfilePage.route) {
                            ProfileScreen(
                                coroutineScope = coroutineScope,
                                navHostController = navHostController,
                                modifier = Modifier,
                                userViewModel = userViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}










const val SPLASH_GRAPH = "splashGraph"
const val LOGIN_GRAPH = "loginGraph"
const val HOME_GRAPH = "homeGraph"


sealed class SplashGraph(
    val route: String
) {
    data object SplashPage : SplashGraph(route = "splashPage")
}


sealed class LoginGraph(
    val route: String
) {
    data object LoginPage : LoginGraph(route = "loginPage")
    data object CreateAccountPage : LoginGraph(route = "createAccountPage")
}


sealed class HomeGraph(
    val route: String, val icon: ImageVector, val name: String
) {
    data object HomeAndSearchPage : HomeGraph(route = "homeAndSearch", icon = Icons.Default.Home, name = "Home and search")

    data object ProfilePage : HomeGraph(route = "profile", icon = Icons.Default.Info, name = "Profile")
}