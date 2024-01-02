package com.itzik.user_with_testing.project.navigation

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.HomeScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope


@SuppressLint(
    "SuspiciousIndentation",
    "UnusedMaterial3ScaffoldPaddingParameter",
    "ComposableNavGraphInComposeScope"
)
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AppGraph(
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
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
                    coroutineScope = coroutineScope,
                    userViewModel = userViewModel,
                    navHostController = navController
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
                    navHostController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }

            composable(
                route = LoginGraph.CreateAccountPage.route
            ) {
                CreateAccountScreen(
                    navHostController = navController,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }

        navigation(
            startDestination = BottomBarScreen.HomePage.route,
            route = HOME_GRAPH
        ){
            composable( route = BottomBarScreen.HomePage.route) {
                Scaffold(
                    bottomBar = {
                        val bottomNavigationItems =
                            listOf(BottomBarScreen.HomePage, BottomBarScreen.ProfilePage)
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            bottomNavigationItems.forEach { item ->
                                BottomNavigationItem(
                                    icon = { Icon(item.icon, contentDescription = null) },
                                    label = { Text(text = item.title) },
                                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HOME_GRAPH
                    ) {
                        composable(BottomBarScreen.HomePage.route) {
                            HomeScreen(
                                coroutineScope = coroutineScope,
                                navHostController = navController,
                                userViewModel = userViewModel
                            )
                        }
                        composable(BottomBarScreen.ProfilePage.route) {
                            ProfileScreen(
                                coroutineScope = coroutineScope,
                                navHostController = navController,
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
    val route: String,
) {
    data object SplashPage : SplashGraph(route = "splashPage")
}


sealed class LoginGraph(
    val route: String,
) {
    data object LoginPage : LoginGraph(route = "loginPage")
    data object CreateAccountPage : LoginGraph(route = "createAccountPage")
}


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object HomePage : BottomBarScreen(
        route = "homePage",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object ProfilePage : BottomBarScreen(
        route = "profilePage",
        title = "Profile",
        icon = Icons.Default.Person
    )
}