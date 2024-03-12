package com.github.adriianh.countryguesser.country.domain.model

data class NetworkError(
    val error: ApiError,
    val t: Throwable? = null
)

enum class ApiError(val message: String) {
    NETWORK_ERROR("Network error"),
    UNKNOWN_ERROR("Unknown error"),
    UNKNOWN_RESPONSE("Unknown response")
}