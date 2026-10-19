package com.example.weatherapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onAddCity: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "")
        },
        actions = {
            IconButton(onClick = onAddCity) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add city",
                    tint = DarkBlue
                )
            }

            Icon(
                imageVector = Icons.Default.BarChart,
                contentDescription = "Manage cities",
                tint = DarkBlue
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}