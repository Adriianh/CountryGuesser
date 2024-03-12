package com.github.adriianh.countryguesser.country.presentation.viewmodel

import com.github.adriianh.countryguesser.country.domain.model.User

data class AuthViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false,
    val users: List<User> = emptyList(),
    val selectedAccount: User? = null
)