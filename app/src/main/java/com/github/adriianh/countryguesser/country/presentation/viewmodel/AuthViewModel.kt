package com.github.adriianh.countryguesser.country.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.adriianh.countryguesser.country.data.local.UserPreferences
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.usecase.user.DeleteUserUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.user.GetUserUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.user.GetUsersUseCase
import com.github.adriianh.countryguesser.country.domain.usecase.user.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthViewState())
    val state: StateFlow<AuthViewState> = _state.asStateFlow()

    val users = _state.map { it.users }.asLiveData()

    private val _hasUsers = MutableStateFlow(false)
    val hasUsers: StateFlow<Boolean> = _hasUsers.asStateFlow()

    private val _selectedAccount = MutableStateFlow<User?>(null)
    val selectedAccount: StateFlow<User?> = _selectedAccount.asStateFlow()

    private val _hasSelectedAccount = MutableStateFlow(false)
    val hasSelectedAccount: StateFlow<Boolean> = _hasSelectedAccount.asStateFlow()

    init {
        getUsers()
        val selectedUser = userPreferences.getSelectedUser()
        if (selectedUser != null) {
            setCurrentUser(selectedUser)
        }
    }

    private fun handleSuccessUser(user: User) {
        _state.update {
            it.copy(selectedAccount = user)
        }
    }

    private fun handleSuccessUsers(users: List<User>) {
        _state.update {
            it.copy(users = users)
        }
    }

    private fun handleError(error: Throwable) {
        _state.update {
            it.copy(
                error = error.message
            )
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun setCurrentUser(user: User) {
        viewModelScope.launch {
            handleLoading(true)

            _state.update {
                it.copy(selectedAccount = user)
            }
            _hasSelectedAccount.update { true }
            _selectedAccount.update { user }
            userPreferences.setSelectedUser(user)

            handleLoading(false)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            handleLoading(true)

            deleteUserUseCase(user)
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            handleLoading(true)

            insertUserUseCase(user)
                .onSuccess { setCurrentUser(user) }
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            handleLoading(true)

            getUserUseCase(id)
                .onSuccess { liveData ->
                    liveData.observeForever { user ->
                        handleSuccessUser(user)
                    }
                }
                .onFailure(::handleError)

            handleLoading(false)
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            handleLoading(true)

            getUsersUseCase(Unit)
                .onSuccess { liveData ->
                    liveData.observeForever { users ->
                        handleSuccessUsers(users)
                        _hasUsers.value = users.isNotEmpty()
                    }
                }
                .onFailure { error ->
                    handleError(error)
                }

            handleLoading(false)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearSelectedUser()
            _selectedAccount.update { null }
            _hasSelectedAccount.update { false }
        }
    }
}