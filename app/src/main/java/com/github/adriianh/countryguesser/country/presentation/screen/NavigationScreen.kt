package com.github.adriianh.countryguesser.country.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.adriianh.countryguesser.country.presentation.screen.auth.UserLogin
import com.github.adriianh.countryguesser.country.presentation.screen.auth.UserRegister
import com.github.adriianh.countryguesser.country.presentation.viewmodel.AuthViewModel

@Composable
fun NavigationScreen(navController: NavController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val hasUsers by viewModel.hasUsers.collectAsState()
    val isLoggedIn by viewModel.hasSelectedAccount.collectAsState()

    if (!hasUsers) {
        UserRegister()
        return
    }

    if (!isLoggedIn) {
        UserLogin()
        return
    }

    HomeScreen(navController)
}