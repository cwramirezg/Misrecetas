package com.github.cwramirezg.misrecetas.navigation

sealed class NavigationRoute(
    val route: String
) {
    object Home : NavigationRoute("home")
    object Detail : NavigationRoute("detail")
}