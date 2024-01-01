package com.itzik.user_with_testing.project.navigation

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.itzik.user_with_testing.project.ui.screens.CreateAccountScreen
import com.itzik.user_with_testing.project.ui.screens.HomeScreen
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SplashScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint(
    "SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter",
    "ComposableNavGraphInComposeScope"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
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
                route = LoginGraph.LoginPage.route
            ) {
                LoginScreen(
                    navHostController = navHostController,
                    modifier = Modifier,
                    userViewModel = userViewModel,
                    coroutineScope = coroutineScope
                )
            }
            composable(
                route = LoginGraph.CreateAccountPage.route
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
            startDestination = BottomNavGraph.HomePage.route,
            route = HOME_GRAPH
        ) {
            composable(
                route = BottomNavGraph.HomePage.route

            ) {
                val screens = listOf(BottomNavGraph.HomePage, BottomNavGraph.ProfilePage)

                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    label = {
                                        Text(text = screen.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = null
                                        )
                                    },
                                    selected = currentDestination?.hierarchy?.any {
                                        it.route == screen.route
                                    } == true,
                                    unselectedContentColor = LocalContentColor.current.copy(
                                        alpha = ContentAlpha.disabled
                                    ),
                                    onClick = {
                                        navHostController.navigate(screen.route) {
                                            popUpTo(navHostController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) {
                    navigation(
                        route = HOME_GRAPH,
                        startDestination = BottomNavGraph.HomePage.route,
                    ) {
                        composable(BottomNavGraph.HomePage.route) {
                            HomeScreen(
                                coroutineScope = coroutineScope,
                                navHostController = navHostController,
                                modifier = Modifier,
                                userViewModel = userViewModel
                            )
                        }
                        composable(BottomNavGraph.ProfilePage.route) {
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


sealed class BottomNavGraph(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object HomePage : BottomNavGraph(
        route = "homePage",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object ProfilePage : BottomNavGraph(
        route = "profilePage",
        title = "Profile",
        icon = Icons.Default.Person
    )

}