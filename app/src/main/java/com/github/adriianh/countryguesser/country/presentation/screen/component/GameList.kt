package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.util.Game

@Preview
@Composable
fun GameList() {
    LazyRow {
        items(games.size) { index ->
            GameCard(
                index = index,
                onClick = {}
            )
        }
    }
}

val games = listOf(
    Game(
        "Random Country",
        R.drawable.logo,
        getGradient(Color(0xFF00BFA5), Color(0xFF00B8D4))
    ),
    Game(
        "Flag Quiz",
        R.drawable.logo,
        getGradient(Color(0xFF00BFA5), Color(0xFF00B8D4))
    ),
    Game(
        "Capital Quiz",
        R.drawable.logo,
        getGradient(Color(0xFF00BFA5), Color(0xFF00B8D4))
    )
)

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush {
    return Brush.horizontalGradient(listOf(startColor, endColor))
}