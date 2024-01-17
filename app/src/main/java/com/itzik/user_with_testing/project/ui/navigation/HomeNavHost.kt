package com.itzik.user_with_testing.project.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.itzik.user_with_testing.project.ui.navigation.Graph.HOME
import com.itzik.user_with_testing.project.ui.screens.ProfileScreen
import com.itzik.user_with_testing.project.ui.screens.SearchScreen
import com.itzik.user_with_testing.project.utils.Constants
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeNavHost(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    Scaffold(
        bottomBar = {
            NavBarScreen(navController = navController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = HOME
        ) {
            navigation(
                route = HOME,
                startDestination = ScreenContainer.Search.route
            ) {

                composable(route = ScreenContainer.Search.route) {
                    SearchScreen(
                        modifier = Modifier,
                        appViewModel = appViewModel,
                        coroutineScope = coroutineScope,
                        navController = navController
                    )
                }

                composable(route = ScreenContainer.Profile.route) {
                    ProfileScreen(
                        modifier = Modifier,
                        navController = navController,
                        appViewModel = appViewModel,
                        coroutineScope = coroutineScope
                    )
                }
            }
        }
    }
}

@Composable
fun NavBarScreen(navController: NavHostController) {
    val screens = listOf(
        ScreenContainer.Search,
        ScreenContainer.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Row(
        modifier = Modifier
            .height(50.dp)
            .background(Constants.Light_Blue)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: ScreenContainer,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    val contentColor = Constants.Dark_Blue
    val background = Constants.Light_Blue
    Box(
        modifier = Modifier
            .clip(CircleShape)

            .background(background)
            .clickable {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            screen.icon?.let {
                Icon(
                    imageVector = it, contentDescription = null,
                    tint = contentColor
                )
            }

            AnimatedVisibility(visible = selected) {
                screen.title?.let {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = it,
                        color = contentColor
                    )
                }
            }
        }
    }
}