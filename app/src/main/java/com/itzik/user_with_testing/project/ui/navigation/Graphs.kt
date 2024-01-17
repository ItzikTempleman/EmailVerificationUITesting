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


sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
) {
    data object Splash : Screen(route = "splash")
    data object Login : Screen(route = "login")
    data object Registration : Screen(route = "signUp")
    data object Search : Screen(route = "search", title = "Search", icon = Icons.Default.Flight)
    data object Profile : Screen(route = "profile", title = "Profile", icon = Icons.Default.Person)
    data object Details : Screen(route = "details")

}

