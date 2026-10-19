package com.example.weatherapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val DarkBlue = Color(0xFF252A40)
val Gray = Color(0xFF7B8197)
val LightGray = Color(0xFFE8EAF3)
val LightBlue = Color(0xFFE7EBFC)
val SkyBlue = Brush.linearGradient(
    colors = listOf(
        LightBlue,
        Color(0xFFF7F8FC),
        Color(0xFFF7F8FC),
    ),
    start = Offset(0f, 0f),
    end = Offset(0f, Float.POSITIVE_INFINITY )
)