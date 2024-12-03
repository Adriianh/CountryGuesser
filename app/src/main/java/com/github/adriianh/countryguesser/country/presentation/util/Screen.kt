package com.github.adriianh.countryguesser.country.presentation.util

sealed class Screen(val route: String) {
    data object NavigationScreen : Screen("navigation")
    data object HomeScreen : Screen("home")
    data object LoginScreen : Screen("login")
    data object RegisterScreen : Screen("register")
    data object RandomCountryScreen : Screen("random_country")
    data object GuessCountryGame : Screen("guess_country_game")
}