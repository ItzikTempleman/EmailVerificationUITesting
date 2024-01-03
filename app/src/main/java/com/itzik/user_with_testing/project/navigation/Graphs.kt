package com.itzik.user_with_testing.project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

object Graph {
    const val SPLASH = "splash_graph"
    const val LOGIN = "login_graph"
    const val HOME = "home_graph"
}

sealed class SplashGraph(val route: String) {
    data object Splash : SplashGraph(route = "SPLASH")
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
}


sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBar(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    data object Profile : BottomBar(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )
}


