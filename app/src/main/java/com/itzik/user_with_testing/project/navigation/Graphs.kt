package com.itzik.user_with_testing.project.navigation


sealed class SplashGraph(
    val route: String
) {
    object SplashPage : SplashGraph(route = "splashPage")
}


sealed class LoginGraph(
    val route: String
) {
    object LoginPage : LoginGraph(route = "loginPage")
    object CreateAccountPage:LoginGraph(route = "createAccountPage")
}


sealed class HomeGraph(
    val route: String
) {
    object HomePage : HomeGraph(route = "homePage")
}