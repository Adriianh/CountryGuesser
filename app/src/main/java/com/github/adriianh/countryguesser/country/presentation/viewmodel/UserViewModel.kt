package com.github.adriianh.countryguesser.country.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.adriianh.countryguesser.country.data.local.UserDao
import com.github.adriianh.countryguesser.country.data.local.UserPreferences
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
    ) : ViewModel() {
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

    fun updateScore(newScore: Int) {
        viewModelScope.launch {
            val user = userPreferences.getSelectedUser()
            if (user != null) {
                user.score = newScore

                userDao.updateUser(user)
                _score.update {
                    newScore
                }
            }
        }
    }
}