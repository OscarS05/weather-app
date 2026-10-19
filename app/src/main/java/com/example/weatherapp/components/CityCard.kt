package com.example.weatherapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.weatherapp.ui.theme.LightBlue
import com.example.weatherapp.ui.theme.LightGray

@Composable
fun CityCard(
    city: String,
    description: String,
    temperature: Int,
    minTemperature: Int,
    maxTemperature: Int,
    isCurrentLocation: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentLocation) {
                LightBlue
            } else {
                Color.White
            }
        ),
        border = BorderStroke(
            width = 1.dp,
            color = LightGray
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = city,
                        color = DarkBlue,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    color = Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Image(
                painter = painterResource(R.drawable.cloudy_weather),
                contentDescription = description,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$temperature°",
                    color = DarkBlue,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = "$maxTemperature°/$minTemperature°",
                    color = Gray,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}