package com.github.adriianh.countryguesser.country.presentation.util

import androidx.compose.ui.graphics.Brush

data class Game(
    val route: String,
    val name: String,
    val image: Int,
    val color: Brush
)