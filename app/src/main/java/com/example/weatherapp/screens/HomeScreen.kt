package com.example.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.components.AppTopBar
import com.example.weatherapp.components.CurrentWeatherCard
import com.example.weatherapp.components.WeatherDetails
import com.example.weatherapp.models.WeatherDetail
import com.example.weatherapp.ui.theme.SkyBlue

@Composable
fun HomeScreen(
    onAddCity: () -> Unit,
) {
    val details = listOf(
        WeatherDetail("UV", "Weak"),
        WeatherDetail("UV", "Weak"),
        WeatherDetail("UV", "Weak"),
        WeatherDetail("UV", "Weak"),
        WeatherDetail("UV", "Weak"),
        WeatherDetail("UV", "Weak")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                CurrentWeatherCard(
                    city = "Ibagué",
                    temperature = 26,
                    description = "Cloudy",
                    minTemperature = 20,
                    maxTemperature = 27
                )
            }

            items(details) { detail ->
                WeatherDetails(detail = detail)
            }
        }
    }
}