package com.github.adriianh.countryguesser.country.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.adriianh.countryguesser.country.presentation.screen.auth.UserLogin
import com.github.adriianh.countryguesser.country.presentation.screen.auth.UserRegister
import com.github.adriianh.countryguesser.country.presentation.screen.game.GuessCountryGame
import com.github.adriianh.countryguesser.country.presentation.util.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.NavigationScreen.route ) {
        composable(route = Screen.NavigationScreen.route) {
            NavigationScreen(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            UserLogin()
        }
        composable(route = Screen.RegisterScreen.route) {
            UserRegister()
        }
        composable(route = Screen.RandomCountryScreen.route) {
            CountryScreen()
        }
        composable(route = Screen.GuessCountryGame.route) {
            GuessCountryGame()
        }
    }
}