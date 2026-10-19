package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.components.AppTopBar
import com.example.weatherapp.navigation.AppNavigation
import com.example.weatherapp.navigation.Screen
import com.example.weatherapp.ui.theme.SkyBlue
import com.example.weatherapp.ui.theme.WeatherAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            WeatherAPPTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isManageCitiesScreen = currentRoute == Screen.ManageCitiesScreen.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue),
        topBar = {
            if (!isManageCitiesScreen) {
                AppTopBar(
                    onAddCity = { navController.navigate(Screen.ManageCitiesScreen.route) },
                )
            }
        },
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}