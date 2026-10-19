package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("name")
    val cityName: String,

    @SerializedName("sys")
    val system: SystemDto,

    @SerializedName("main")
    val main: MainWeatherDto,

    @SerializedName("weather")
    val weather: List<WeatherConditionDto>,

    @SerializedName("wind")
    val wind: WindDto
)

data class SystemDto(
    @SerializedName("country")
    val country: String
)

data class MainWeatherDto(
    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val minTemperature: Double,

    @SerializedName("temp_max")
    val maxTemperature: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Int
)

data class WeatherConditionDto(
    @SerializedName("main")
    val main: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val icon: String
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double
)