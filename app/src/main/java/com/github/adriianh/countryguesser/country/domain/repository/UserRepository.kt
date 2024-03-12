package com.github.adriianh.countryguesser.country.domain.repository

import androidx.lifecycle.LiveData
import com.github.adriianh.countryguesser.country.domain.model.User

interface UserRepository {
    fun getUsers(): LiveData<List<User>>

    fun getUser(id: Int): LiveData<User>

    fun getUser(name: String): LiveData<User>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)
}