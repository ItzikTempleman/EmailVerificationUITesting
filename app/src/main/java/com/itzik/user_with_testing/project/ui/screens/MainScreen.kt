package com.itzik.user_with_testing.project.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.navigation.BottomBar
import com.itzik.user_with_testing.project.navigation.MainScreenNavGraph
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope)
{
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        MainScreenNavGraph(
            userViewModel = userViewModel,
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}



@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBar.SearchFlights,
        BottomBar.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                BottomNavigationItem(
                    label = {
                        Text(text = screen.title)
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "Navigation Icon"
                        )
                    },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

