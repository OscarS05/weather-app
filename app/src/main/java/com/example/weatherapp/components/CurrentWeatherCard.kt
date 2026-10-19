package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkBlue
import com.example.weatherapp.ui.theme.Gray

@Composable
fun CurrentWeatherCard(
    city: String,
    temperature: Int,
    description: String,
    minTemperature: Int,
    maxTemperature: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = city,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = DarkBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "$temperature°",
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Light,
                        color = DarkBlue,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkBlue
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "$maxTemperature° / $minTemperature°",
                            style = MaterialTheme.typography.bodySmall,
                            color = Gray
                        )
                    }
                }

                Image(
                    painter = painterResource(
                        id = R.drawable.cloudy_weather
                    ),
                    contentDescription = "Cloudy weather",
                    modifier = Modifier.size(125.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}