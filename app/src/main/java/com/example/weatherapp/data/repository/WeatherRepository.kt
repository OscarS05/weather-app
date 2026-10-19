package com.example.weatherapp.data.repository

import android.content.Context
import com.example.weatherapp.data.local.CityEntity
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.dto.CurrentWeatherDto

class WeatherRepository(
    context: Context,
    private val weatherApi: WeatherApi
) {
    private val cityDao =
        WeatherDatabase.getInstance(context).cityDao()

    suspend fun getInitialCity(): CityEntity {
        val existingCity = cityDao.getCurrentCity()

        if (existingCity != null) {
            return existingCity
        }

        val initialCity = CityEntity(
            name = "Ibagué",
            country = "CO",
            latitude = 4.4389,
            longitude = -75.2322,
            isCurrentLocation = true
        )

        cityDao.insert(initialCity)

        return cityDao.getCurrentCity()
            ?: error("Could not create the initial city")
    }

    suspend fun getCurrentWeather(
        apiKey: String
    ): CurrentWeatherDto {
        val city = getInitialCity()

        return weatherApi.getCurrentWeather(
            latitude = city.latitude,
            longitude = city.longitude,
            apiKey = apiKey
        )
    }
}