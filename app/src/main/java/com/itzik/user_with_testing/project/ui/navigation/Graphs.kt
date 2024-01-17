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


sealed class ScreenContainer(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
) {
    data object Splash : ScreenContainer(route = "splash")
    data object Login : ScreenContainer(route = "login")
    data object Registration : ScreenContainer(route = "registration")
    data object Search : ScreenContainer(route = "search", title = "Search", icon = Icons.Default.Flight)
    data object Profile : ScreenContainer(route = "profile", title = "Profile", icon = Icons.Default.Person)

    data object Details : ScreenContainer(route = "details")

}

