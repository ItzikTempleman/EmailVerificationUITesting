package com.itzik.user_with_testing.project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

object Graph {
    const val SPLASH = "splashGraph"
    const val LOGIN = "loginGraph"
    const val BOTTOM_BAR = "bottomBarGraph"
    const val DETAILS = "detailsGraph"
}

sealed class SplashGraph(val route: String) {
    data object Splash : SplashGraph(route = "splash")
}

sealed class LoginGraph(val route: String) {
    data object Login : LoginGraph(route = "login")
    data object SignUp : LoginGraph(route = "signUp")
}

sealed class DetailsGraph(val route: String) {
    data object FlightInfo : DetailsGraph(route = "info")
}


sealed class BottomBarGraph(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object SearchFlights : BottomBarGraph(
        route = "search",
        title = "Search",
        icon = Icons.Default.Flight
    )

    data object Profile : BottomBarGraph(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

}


