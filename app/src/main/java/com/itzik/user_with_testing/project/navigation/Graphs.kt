package com.itzik.user_with_testing.project.navigation


sealed class SplashGraph(
    val route: String
) {
    data object SplashPage : SplashGraph(route = "splashPage")
}


sealed class LoginGraph(
    val route: String
) {
    data object LoginPage : LoginGraph(route = "loginPage")
    data object CreateAccountPage:LoginGraph(route = "createAccountPage")
}


sealed class HomeGraph(
    val route: String
) {
    data object SearchFlightPage : HomeGraph(route = "searchFlightPage")
    data object ProfilePage : HomeGraph(route = "profilePage")
}