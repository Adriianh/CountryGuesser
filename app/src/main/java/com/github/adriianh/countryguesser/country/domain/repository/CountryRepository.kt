package com.github.adriianh.countryguesser.country.domain.repository

import arrow.core.Either
import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.model.NetworkError

interface CountryRepository {
    suspend fun getCountries(): Either<NetworkError, List<Country>>

    suspend fun getCountriesByRegion(region: String): Either<NetworkError, List<Country>>

    suspend fun getCountryByName(name: String): Either<NetworkError, Country>

    suspend fun getCountryByCapital(capital: String): Either<NetworkError, Country>

    suspend fun getCountryByCode(code: String): Either<NetworkError, Country>
}