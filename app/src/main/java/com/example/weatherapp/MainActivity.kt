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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue),
        topBar = {
            AppTopBar(
                onAddCity = { navController.navigate(Screen.Home.route) },
            )
        },
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}