package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.screens.HomeScreen
import com.example.weatherapp.screens.ManageCitiesScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object ManageCitiesScreen : Screen("manage_cities_screen")
}

@Composable()
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onAddCity = {
                    navController.navigate(Screen.ManageCitiesScreen.route)
                }
            )
        }

        composable(route = Screen.ManageCitiesScreen.route) {
            ManageCitiesScreen(
                onBack = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
    }
}