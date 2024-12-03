package com.github.adriianh.countryguesser.country.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.adriianh.countryguesser.country.data.local.UserDao
import com.github.adriianh.countryguesser.country.data.local.UserPreferences
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.usecase.user.GetUserUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.user.UpdateUserScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDao: UserDao,
    private val userPreferences: UserPreferences,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserScore: UpdateUserScore
) : ViewModel() {
    private val _state = MutableStateFlow(AuthViewState())
    val state: StateFlow<AuthViewState> = _state.asStateFlow()

    private val _score = MutableStateFlow(userPreferences.getScore())
    val score: StateFlow<Int> = _score.asStateFlow()

    init {
        viewModelScope.launch {
            val user = userPreferences.getSelectedUser()

            if (user != null) {
                val score = userDao.getScore(user.id)
                if (score != null) {
                    _score.value = score
                }
            }
        }
    }

    private fun handleSuccessUser(user: User) {
        _state.update {
            it.copy(selectedAccount = user)
        }
    }

    fun getUserScore(user: User) {
        viewModelScope.launch {
            getUserUseCase(user.id)
                .onSuccess { liveData ->
                    liveData.observeForever { user ->
                        handleSuccessUser(user)
                    }
                }
        }
    }

    fun updateUserScore(newScore: Int) {
        viewModelScope.launch {
            updateUserScore(Pair(userPreferences.getSelectedUser()!!, newScore))
        }
    }
}