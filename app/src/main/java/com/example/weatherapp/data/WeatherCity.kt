package com.example.weatherapp.data

data class WeatherCity(
    val name: String,
    val description: String,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int
)