package com.github.adriianh.countryguesser.country.domain.usecase.user

import androidx.lifecycle.LiveData
import com.github.adriianh.countryguesser.country.domain.model.User
import com.github.adriianh.countryguesser.country.domain.repository.UserRepository
import com.github.adriianh.countryguesser.country.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, LiveData<List<User>>>() {

    override suspend fun execute(params: Unit): LiveData<List<User>> {
        return userRepository.getUsers()
    }
}