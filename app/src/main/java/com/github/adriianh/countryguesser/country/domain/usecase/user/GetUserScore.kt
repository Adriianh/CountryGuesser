package com.github.adriianh.countryguesser.country.domain.usecase.user

import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetUserScore @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<User, Int>() {

    override suspend fun execute(params: User): Int {
        return userRepository.getUser(params.id).value?.score ?: 0
    }
}