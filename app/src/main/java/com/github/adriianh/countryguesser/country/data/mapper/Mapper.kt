package com.github.adriianh.countryguesser.country.data.mapper

import com.github.adriianh.countryguesser.country.domain.model.ApiError
import com.github.adriianh.countryguesser.country.domain.model.NetworkError
import com.github.adriianh.countryguesser.country.domain.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NETWORK_ERROR
        is HttpException -> ApiError.UNKNOWN_RESPONSE
        else -> ApiError.UNKNOWN_ERROR
    }

    return NetworkError(error, this)
}

fun User.toJson(): String {
    return Gson().toJson(this)
}

fun String.toUser(): User {
    val type = object : TypeToken<User>() {}.type
    return Gson().fromJson(this, type)
}