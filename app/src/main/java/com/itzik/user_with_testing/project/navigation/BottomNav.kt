package com.itzik.user_with_testing.project.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun BottomNav(
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    modifier: Modifier,
    userViewModel: UserViewModel,
) {
    val items = listOf(
        HomeGraph.SearchFlightPage,
        HomeGraph.ProfilePage
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        label = {
                            Text(text = screen.route)
                        },
                         icon = {
                            when (screen) {
                                HomeGraph.SearchFlightPage -> Icons.Default.Flight
                                HomeGraph.ProfilePage -> Icons.Default.Info
                            }

                        },
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
    ){

    }
}
