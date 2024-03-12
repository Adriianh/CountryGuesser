package com.github.adriianh.countryguesser.country.data.repository

import arrow.core.Either
import com.github.adriianh.countryguesser.country.data.mapper.toNetworkError
import com.github.adriianh.countryguesser.country.data.remote.CountryApi
import com.github.adriianh.countryguesser.country.domain.model.Country
import com.github.adriianh.countryguesser.country.domain.model.NetworkError
import com.github.adriianh.countryguesser.country.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi
) : CountryRepository {
    override suspend fun getCountries(): Either<NetworkError, List<Country>> {
        return Either.catch {
            countryApi.getCountries()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCountriesByRegion(region: String): Either<NetworkError, List<Country>> {
        return Either.catch {
            countryApi.getCountriesByRegion(region)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCountryByName(name: String): Either<NetworkError, Country> {
        return Either.catch {
            countryApi.getCountryByName(name)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCountryByCode(code: String): Either<NetworkError, Country> {
        return Either.catch {
            countryApi.getCountryByCode(code)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCountryByCapital(capital: String): Either<NetworkError, Country> {
        return Either.catch {
            countryApi.getCountryByCapital(capital)
        }.mapLeft { it.toNetworkError() }
    }
}