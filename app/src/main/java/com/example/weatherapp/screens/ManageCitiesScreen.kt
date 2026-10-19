package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.components.CityCard
import com.example.weatherapp.components.CitySearchBar
import com.example.weatherapp.data.WeatherCity
import com.example.weatherapp.ui.theme.DarkBlue
import com.example.weatherapp.ui.theme.Gray

@Composable
fun ManageCitiesScreen(
    onBack: () -> Unit
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    val addedCities = listOf(
        WeatherCity(
            name = "Bogotá",
            description = "Cloudy",
            temperature = 26,
            minTemperature = 20,
            maxTemperature = 27
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FC))
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "Go back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Manage cities",
            color = DarkBlue,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        CitySearchBar(
            query = query,
            onQueryChange = { query = it }
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Current location",
            color = Gray,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        CityCard(
            city = "Ibagué",
            description = "Cloudy",
            temperature = 26,
            minTemperature = 20,
            maxTemperature = 27,
            isCurrentLocation = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Added locations",
            color = Gray,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        addedCities.forEach { city ->
            CityCard(
                city = city.name,
                description = city.description,
                temperature = city.temperature,
                minTemperature = city.minTemperature,
                maxTemperature = city.maxTemperature,
                isCurrentLocation = false
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}