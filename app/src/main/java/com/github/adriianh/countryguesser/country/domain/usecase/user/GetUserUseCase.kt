package com.github.adriianh.countryguesser.country.domain.usecase.user

import androidx.lifecycle.LiveData
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Int, LiveData<User>>() {

    override suspend fun execute(params: Int): LiveData<User> {
        return userRepository.getUser(params)
    }
}