package com.github.adriianh.countryguesser.country.data.repository

import androidx.lifecycle.LiveData
import com.github.adriianh.countryguesser.country.data.local.UserDao
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getUser(id: Int): LiveData<User> {
        return userDao.getUserById(id)
    }

    override fun getUser(name: String): LiveData<User> {
        return userDao.getUserByName(name)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}