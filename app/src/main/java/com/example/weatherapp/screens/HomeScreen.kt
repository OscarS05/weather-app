package com.example.weatherapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.components.CurrentWeatherCard
import com.example.weatherapp.components.WeatherDetails
import com.example.weatherapp.data.WeatherDetail
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.remote.RetrofitInstance

data class HomeUiState(
    val isLoading: Boolean = true,
    val city: String = "",
    val temperature: Int = 0,
    val description: String = "",
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Double = 0.0,
    val pressure: Int = 0,
    val uvIndex: Double = 0.0,
    val feelsLike: Int = 0,
    val error: String? = null
)

class HomeViewModel(
    private val repository: WeatherRepository,
    private val apiKey: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            try {
                val city = repository.getInitialCity()
                val response = repository.getCurrentWeather(apiKey)
                Log.d("Response API", "$response")

                _uiState.value = HomeUiState(
                    isLoading = false,
                    city = response.cityName,
                    temperature = response.main.temperature.toInt(),
                    description = response.weather.firstOrNull()?.description.orEmpty(),
                    minTemperature = response.main.minTemperature.toInt(),
                    maxTemperature = response.main.maxTemperature.toInt(),
                    humidity = response.main.humidity,
                    windSpeed = response.wind.speed,
                    pressure = response.main.pressure,
                    feelsLike = response.main.feelsLike.toInt()
                )
            } catch (exception: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Could not load weather"
                )
            }
        }
    }
}

class HomeViewModelFactory(
    private val repository: WeatherRepository,
    private val apiKey: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                repository = repository,
                apiKey = apiKey
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}

@Composable
fun HomeRoute(
    onAddCity: () -> Unit,
) {
    val context = LocalContext.current

    val repository = remember {
        WeatherRepository(
            context = context.applicationContext,
            weatherApi = RetrofitInstance.weatherApi
        )
    }

    val factory = remember {
        HomeViewModelFactory(
            repository = repository,
            apiKey = BuildConfig.OPENWEATHER_API_KEY
        )
    }

    val viewModel: HomeViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = uiState.error ?: "Unknown error"
                )

                Button(
                    onClick = viewModel::loadWeather
                ) {
                    Text("Retry")
                }
            }
        }

        else -> {
            HomeScreen(
                onAddCity = onAddCity,
                weather = uiState
            )
        }
    }
}

@Composable
fun HomeScreen(
    onAddCity: () -> Unit,
    weather: HomeUiState
) {
    val details = listOf(
        WeatherDetail("UV Index", weather.uvIndex.toString()),
        WeatherDetail("Humidity", "${weather.humidity}%"),
        WeatherDetail("Wind", "${weather.windSpeed} m/s"),
        WeatherDetail("Pressure", "${weather.pressure} hPa"),
        WeatherDetail("Feels like", "${weather.feelsLike}°")
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
                    city = weather.city,
                    temperature = weather.temperature,
                    description = weather.description,
                    minTemperature = weather.minTemperature,
                    maxTemperature = weather.maxTemperature
                )
            }

            items(details) { detail ->
                WeatherDetails(detail = detail)
            }
        }
    }
}