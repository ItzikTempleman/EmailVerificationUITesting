package com.itzik.user_with_testing.project.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


object Graph {
    const val ROOT = "ROOT"
    const val AUTHENTICATION = "AUTHENTICATION"
    const val HOME = "HOME"
    const val DETAILS = "DETAILS"
}


sealed class ScreenHost(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
) {
    data object Splash : ScreenHost(route = "splash")
    data object Login : ScreenHost(route = "login")
    data object Registration : ScreenHost(route = "registration")
    data object Search : ScreenHost(route = "search", title = "Search", icon = Icons.Default.Flight)
    data object Profile : ScreenHost(route = "profile", title = "Profile", icon = Icons.Default.Person)

    data object Details : ScreenHost(route = "details")

}

