package com.github.adriianh.countryguesser.country.domain.usecase

abstract class BaseUseCase<Params, ReturnType> {

    suspend operator fun invoke(params: Params): Result<ReturnType> {
        return try {
            execute(params).let {
                Result.success(it)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: Params): ReturnType
}