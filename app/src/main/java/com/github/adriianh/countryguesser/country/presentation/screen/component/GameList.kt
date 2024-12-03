package com.github.adriianh.countryguesser.country.presentation.screen.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.adriianh.countryguesser.R
import com.github.adriianh.countryguesser.country.presentation.util.Game

@Composable
fun GameList(navController: NavController) {
    LazyRow {
        items(games.size) { index ->
            GameCard(
                index = index,
                onClick = {
                    navController.navigate(games[index].route)
                }
            )
        }
    }
}

val games = listOf(
    Game(
        route = "random_country",
        name = "RANDOM COUNTRY",
        image = R.drawable.logo,
        color = getGradient(Color(0xFF9BB2E5), Color(0xFF698CBF))
    ),
    Game(
        route = "guess_country_game",
        name = "GUESS THE COUNTRY",
        image = R.drawable.logo,
        color = getGradient(Color(0xFF9BB2E5), Color(0xFF698CBF))
    ),
    Game(
        route = "guess_capital",
        name = "GUESS THE CAPITAL",
        image = R.drawable.logo,
        color = getGradient(Color(0xFF9BB2E5), Color(0xFF698CBF))
    )
)

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush {
    return Brush.horizontalGradient(listOf(startColor, endColor))
}