package com.github.cwramirezg.misrecetas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.cwramirezg.misrecetas.home.ui.detail.DetailScreen
import com.github.cwramirezg.misrecetas.home.ui.home.HomeScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    logout: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(NavigationRoute.Home.route) {
            HomeScreen(
                onClickReceta = {
                    id -> navHostController.navigate("${NavigationRoute.Detail.route}?id=$id")
                }
            )
        }
        composable(
            "${NavigationRoute.Detail.route}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {
            DetailScreen(
                onClickBack = { navHostController.popBackStack() },
                onClickMap = {}
            )
        }
    }
}