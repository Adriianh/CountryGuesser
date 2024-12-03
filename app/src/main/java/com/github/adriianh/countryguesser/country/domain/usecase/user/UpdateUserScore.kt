package com.github.adriianh.countryguesser.country.domain.usecase.user

import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class UpdateUserScore @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Pair<User, Int>, Unit>() {

    override suspend fun execute(params: Pair<User, Int>) {
        userRepository.updateUserScore(params.first.id, params.second)
    }
}