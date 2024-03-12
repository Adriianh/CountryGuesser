package com.github.adriianh.countryguesser.country.domain.usecase.user

import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<User, User>() {

    override suspend fun execute(params: User): User {
        userRepository.insertUser(params)

        return params
    }
}